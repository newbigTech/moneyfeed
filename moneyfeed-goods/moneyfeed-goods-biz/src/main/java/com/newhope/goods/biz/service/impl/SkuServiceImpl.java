package com.newhope.goods.biz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.SkuService;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.goods.api.bean.AttributeParmsModel;
import com.newhope.moneyfeed.goods.api.bean.AttributesModel;
import com.newhope.moneyfeed.goods.api.bean.BaseModel;
import com.newhope.moneyfeed.goods.api.bean.ProductSkuModel;
import com.newhope.moneyfeed.goods.dal.dao.AttributeParmsDao;
import com.newhope.moneyfeed.goods.dal.dao.AttributesDao;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.ProductDao;
import com.newhope.moneyfeed.goods.dal.dao.ProductSkuDao;
import com.newhope.moneyfeed.goods.dal.dao.SkuAttributeParmDao;
import com.newhope.moneyfeed.goods.dal.dao.ebs.EbsMaterialDao;

@Service
public class SkuServiceImpl extends BaseService<ProductSkuModel> implements SkuService {

	@Autowired
	private AttributesDao attDao;

	@Autowired
	private EbsMaterialDao ebsMaterialDao;

	@Autowired
	private AttributeParmsDao attributeParmsDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductSkuDao productSkuDao;

	@Autowired
	private SkuAttributeParmDao skuAttributeParmDao;

	private Map<String, Long> shopMap = new HashMap<String, Long>();

	/**
	 * 初始化重量属性
	 */
	public void weightAttributeInit() {

		boolean existFlag = false;
		List<AttributesModel> attList = attDao.select(null);
		for (AttributesModel att : attList) {
			if (att.getName().equals("重量")) {
				existFlag = true;
			}
		}

		if (!existFlag) {
			AttributesModel att = new AttributesModel();
			buildCommon(att);

			att.setName("重量");
			List<AttributesModel> insertList = new ArrayList<AttributesModel>();
			insertList.add(att);
			attDao.insert(insertList);
		}
	}

	/**
	 * 初始化重量属性值
	 * 
	 * @param fromDate
	 * @return
	 */
	public boolean weightAttributeParmInit(Date fromDate) {
		AttributesModel selAttribute = new AttributesModel();
		selAttribute.setName("重量");
		List<AttributesModel> attList = attDao.select(selAttribute);
		if (attList.size() == 0) {
			return false;
		}

		AttributeParmsModel selAttParm = new AttributeParmsModel();
		selAttParm.setAttributeId(attList.get(0).getId());
		List<AttributeParmsModel> attParmList = attributeParmsDao.select(selAttParm);
		List<String> attParmValueList = ebsMaterialDao.selectWeightSukList(fromDate);

		for (AttributeParmsModel attParm : attParmList) {
			attParmValueList.remove(attParm.getParmValue());
		}
		if (attParmValueList.size() > 0) {
			List<AttributeParmsModel> insertAttParmList = new ArrayList<AttributeParmsModel>();
			for (String attParmValue : attParmValueList) {
				AttributeParmsModel attParm = new AttributeParmsModel();
				buildCommon(attParm);
				attParm.setParmValue(attParmValue);
				attParm.setAttributeId(selAttParm.getAttributeId());
				insertAttParmList.add(attParm);
			}
			if (insertAttParmList.size() > 0) {
				attributeParmsDao.insert(insertAttParmList);
			}
		}
		return true;

	}

	private Long getShopIdByProductCode(String productCode) {
		Long shopId = shopMap.get(productCode);
		if (shopId == null) {
			shopId = productSkuDao.selectShopIdByProductCode(productCode);
			shopMap.put(productCode, shopId);
		}
		return shopId;
	}

	@Transactional
	public boolean allSkuInit(Date fromDate) {
		List<Map> productCodeList = productDao.selectNoSkuProductCodeList(fromDate);
		List<ProductSkuModel> insertSkuList = new ArrayList<ProductSkuModel>();
		for (Map m : productCodeList) {
			ProductSkuModel sku = new ProductSkuModel();
			buildCommon(sku);
			sku.setProductCode(m.get("productCode").toString());
			sku.setPrice((BigDecimal) m.get("price"));
			sku.setQuantity(0);
			sku.setShopId(getShopIdByProductCode(m.get("productCode").toString()));
			insertSkuList.add(sku);
		}
		if (insertSkuList.size() > 0) {
			productSkuDao.insert(insertSkuList);
		}

		weightAttributeInit();
		boolean flag = weightAttributeParmInit(fromDate);
		if (!flag) {
			throw new BizException("初始化sku重量属性值出错");
		}
		skuAttributeParmDao.delete(null);
		skuAttributeParmDao.insertAllWeightList(fromDate);

		productSkuDao.updateProductSkuPrice();
		return true;
	}

	private void buildCommon(BaseModel base) {
		base.setCreator(1l);
		base.setGmtCreated(new Date());
		base.setModifier(1l);
		base.setGmtModified(new Date());
		base.setVersion(0l);
	}

	public boolean initSkuByProductIdList(List<Long> productIdList) {
		boolean result = false;

		return result;
	}

	@Override
	protected BaseDao<ProductSkuModel> getDao() {
		return productSkuDao;
	}

}
