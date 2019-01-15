package com.newhope.goods.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.ProductSkuService;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.goods.api.bean.ProductSkuModel;
import com.newhope.moneyfeed.goods.api.dto.request.ProductSkusQueryDto;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.goods.api.exbean.ProductSkuExModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.ProductSkuDao;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:27
 */
@Service
public class ProductSkuServiceImpl extends BaseService<ProductSkuModel> implements ProductSkuService {

	private static final Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);
	
	@Autowired
	private ProductSkuDao productSkuDao;

	@Override
	protected BaseDao<ProductSkuModel> getDao() {
		return productSkuDao;
	}

	@Override
	public ProductSkuListDtoResult getProductSku(ProductSkusQueryDto dtoReq) {
		ProductSkuListDtoResult dtoReuslt = new ProductSkuListDtoResult();
		dtoReuslt.setCode(ResultCode.SUCCESS.getCode());
		dtoReuslt.setMsg(ResultCode.SUCCESS.getDesc());
//		if (CollectionUtils.isEmpty(dtoReq.getProductSkuIds())) {
//			return dtoReuslt;
//		}
		PageBounds page = new PageBounds(dtoReq.getPage().intValue(), dtoReq.getPageSize());
		
		if(!StringUtils.isBlank(dtoReq.getBrandIds())) {
			dtoReq.setBrandIdsArray( (Long[]) ConvertUtils.convert(dtoReq.getBrandIds().split(","), Long.class));
		}
		
		PageList<ProductSkuExModel> productSkuExModels = productSkuDao.queryProductSku(dtoReq, page);
		List<ProductSkuDtoResult> productSkuDtoResults = new ArrayList<>();
		for (ProductSkuExModel productSkuExModel : productSkuExModels) {
			ProductSkuDtoResult productSkuDtoResult = new ProductSkuDtoResult();
			BeanUtils.copyProperties(productSkuExModel, productSkuDtoResult);
			if (!CollectionUtils.isEmpty(productSkuExModel.getProductSkuAttributesExModels())) {
				productSkuDtoResult.setSpec(productSkuExModel.getProductSkuAttributesExModels().get(0).getParmValue()
						+ productSkuExModel.getPrimaryUom() + "/" + productSkuExModel.getSecondaryUom());
			}
			productSkuDtoResults.add(productSkuDtoResult);
		}
		dtoReuslt.setProductSkuList(productSkuDtoResults);
		if (productSkuExModels.getPaginator() != null) {
			dtoReuslt.setPage((long) productSkuExModels.getPaginator().getPage());
			dtoReuslt.setTotalCount((long) productSkuExModels.getPaginator().getTotalCount());
			dtoReuslt.setTotalPage((long) productSkuExModels.getPaginator().getTotalPages());
		}
		return dtoReuslt;
	}

}
