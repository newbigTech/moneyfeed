package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.rpc.feign.ebs.EbsSyncDataFeignClient;
import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.ProductService;
import com.newhope.goods.biz.service.SalecateProductService;
import com.newhope.goods.biz.service.SkuService;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.util.ReflectUtil;
import com.newhope.moneyfeed.goods.api.bean.CompanyModel;
import com.newhope.moneyfeed.goods.api.bean.ProductModel;
import com.newhope.moneyfeed.goods.api.bean.SalecateProductModel;
import com.newhope.moneyfeed.goods.api.bean.SalecategoryModel;
import com.newhope.moneyfeed.goods.api.bean.ebs.EbsMaterialModel;
import com.newhope.moneyfeed.goods.api.constant.CommonConstant;
import com.newhope.moneyfeed.goods.api.dto.request.*;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.goods.api.enums.EbsMaterialStatusEnums;
import com.newhope.moneyfeed.goods.api.enums.SyncEBSItemStatusEnums;
import com.newhope.moneyfeed.goods.api.exbean.ProductSkuExModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.CompanyDao;
import com.newhope.moneyfeed.goods.dal.dao.ProductDao;
import com.newhope.moneyfeed.goods.dal.dao.ProductSkuDao;
import com.newhope.moneyfeed.goods.dal.dao.ebs.EbsMaterialDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeedMapper-goods
 * @date : 2018/11/17 09:57
 */
@Service
public class ProductServiceImpl extends BaseService<ProductModel> implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private EbsMaterialDao ebsMaterialDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SalecateProductService salecateProductService;
	@Autowired
	private SalecategoryServiceImpl salecategoryService;
	@Autowired
	private ProductSkuDao productSkuDao;
	@Autowired
	private SkuService skuSer;
	@Autowired
	private EbsSyncDataFeignClient ebsSyncDataFeignClient;
	@Autowired
	private CacheData cacheData;

	@Override
	protected BaseDao<ProductModel> getDao() {
		return productDao;
	}

	/**
	 * 初始化商品信息
	 *
	 * @param orgId
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public DtoResult syncProduct(String orgId) {
		DtoResult result = new DtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		Long pageSize = 50L;
		String oneCat = "15";// 15是饲料产品
		// 全量更新
		if (CommonConstant.ALL_ORG.equals(orgId)) {
			orgId = null;
		}
		Long count = ebsMaterialDao.queryByEbsMaterialStatusCount(oneCat, orgId);
		Long page = (count / pageSize + ((count % pageSize != 0) ? 1 : 0));

		for (int i = 0; i < page; i++) {
			Long start = i * pageSize;
			List<EbsMaterialModel> ebsMaterialModels = ebsMaterialDao.queryEbsMaterialList(start, pageSize, oneCat,
					orgId);
			syncProduct(ebsMaterialModels);
		}
		return result;
	}

	/**
	 * 同步公司所属商品
	 *
	 * @param dtoReq
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public DtoResult productCompany(ProductUpdateDtoReq dtoReq) {
		DtoResult result = new DtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		EbsMaterialModel ebsMaterialModel = new EbsMaterialModel();
		ebsMaterialModel.setCompanyShortCode(dtoReq.getCompanyShortCode());
		List<EbsMaterialModel> ebsMaterialModels = ebsMaterialDao.select(ebsMaterialModel);
		if (CollectionUtils.isEmpty(ebsMaterialModels)) {
			return result;
		}
		syncProduct(ebsMaterialModels);
		return result;
	}

	/**
	 * 同步指定商品
	 *
	 * @param dtoReq
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public DtoResult productSingle(ProductUpdateDtoReq dtoReq) {
		DtoResult result = new DtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		if (StringUtils.isEmpty(dtoReq.getSuppliesCode())) {
			return result;
		}
		EbsMaterialModel ebsMaterialModel = new EbsMaterialModel();
		ebsMaterialModel.setItemNumber(dtoReq.getSuppliesCode());
		List<EbsMaterialModel> ebsMaterialModels = ebsMaterialDao.select(ebsMaterialModel);
		if (CollectionUtils.isEmpty(ebsMaterialModels)) {
			return result;
		}
		syncProduct(ebsMaterialModels);
		return result;
	}

	/**
	 * 同步商品
	 *
	 * @param ebsMaterialModels
	 */
	private void syncProduct(List<EbsMaterialModel> ebsMaterialModels) {
		List<ProductModel> addProduct = new ArrayList<>();
		List<ProductModel> updateProduct = new ArrayList<>();
		for (EbsMaterialModel ebsMaterialModel : ebsMaterialModels) {

			CompanyModel companyModel = new CompanyModel();
			companyModel.setCompanyShortCode(ebsMaterialModel.getCompanyShortCode());
			Long companyId = null;
			List<CompanyModel> companyModels = companyDao.select(companyModel);
			if (CollectionUtils.isEmpty(companyModels)) {
				List<CompanyModel> companyModelList = new ArrayList<>();
				companyModel.setCompanyName(ebsMaterialModel.getCompanyName());
				companyModel.setOrganizationName(ebsMaterialModel.getOrganizationName());
				companyModel.setOrganizationCode(ebsMaterialModel.getOrganizationCode());
				companyModel.setOrgId(ebsMaterialModel.getOrgId());
				companyModelList.add(companyModel);
				companyDao.insert(companyModelList);
				companyId = companyModel.getId();
			} else {
				companyId = companyModels.get(0).getId();
			}

			ProductModel productModel = new ProductModel();
			productModel.setSuppliesCode(ebsMaterialModel.getItemNumber());
			productModel.setCompanyId(companyId);
			List<ProductModel> productModelList = productDao.select(productModel);

			ProductModel model = mergeAddProduct(ebsMaterialModel, companyId);
			if (CollectionUtils.isEmpty(productModelList)) {
				addProduct.add(model);
				continue;
			}
			model.setId(productModelList.get(0).getId());
			updateProduct.add(model);
		}
		// 批量添加
		if (CollectionUtils.isNotEmpty(addProduct)) {
			productDao.insert(addProduct);
		}
		// 批量更新
		if (CollectionUtils.isNotEmpty(updateProduct)) {
			productDao.batchUpdate(updateProduct);
		}
		// 更新前台目录和商品关系
		List<ProductModel> productAllList = new ArrayList<>();
		productAllList.addAll(addProduct);
		productAllList.addAll(updateProduct);

		List<SalecateProductModel> salecateProductModelList = new ArrayList<>();
		for (ProductModel productModel : productAllList) {
			SalecateProductQueryDtoReq dtoReq = new SalecateProductQueryDtoReq();
			dtoReq.setCateId(productModel.getCateCode());
			List<SalecategoryModel> salecategoryModels = salecategoryService.queryAggregationSalecategory(dtoReq);
			if (CollectionUtils.isEmpty(salecategoryModels)) {
				continue;
			}
			SalecategoryModel salecategoryModel = salecategoryModels.get(0);
			SalecateProductModel salecateProductModel = new SalecateProductModel();
			salecateProductModel.setProductCode(productModel.getProductCode());

			mergeSalecategory(salecategoryModel, salecateProductModel, salecategoryModel.getSaleCateLevel());
			salecateProductModel.setSalecateLevel(salecategoryModel.getSaleCateLevel());
			salecateProductModel.setSalecateId(salecategoryModel.getSaleCateId());

			String parentSaleCateId = salecategoryModel.getParentSaleCateId();
			for (int i = salecategoryModel.getSaleCateLevel() - 1; i > 0; i--) {
				SalecategoryModel salecategory = new SalecategoryModel();
				salecategory.setSaleCateId(parentSaleCateId);
				List<SalecategoryModel> salecategoryModelList = salecategoryService.query(salecategory);
				if (CollectionUtils.isEmpty(salecategoryModelList)) {
					continue;
				}
				SalecategoryModel salecategoryM = salecategoryModelList.get(0);
				parentSaleCateId = salecategoryM.getParentSaleCateId();
				mergeSalecategory(salecategoryM, salecateProductModel, i);
			}
			salecateProductModelList.add(salecateProductModel);
		}

		salecateProductService.syncSalecateProduct(salecateProductModelList);
	}

	private void mergeSalecategory(SalecategoryModel salecategoryModel, SalecateProductModel salecateProductModel,
			Integer saleCateLevel) {
		switch (saleCateLevel) {
		case 1:
			salecateProductModel.setOneSalecateId(salecategoryModel.getSaleCateId());
			salecateProductModel.setOneSalecateName(salecategoryModel.getSaleCaleName());
			break;
		case 2:
			salecateProductModel.setTwoSalecateId(salecategoryModel.getSaleCateId());
			salecateProductModel.setTwoSalecateName(salecategoryModel.getSaleCaleName());
			break;
		case 3:
			salecateProductModel.setThreeSalecateId(salecategoryModel.getSaleCateId());
			salecateProductModel.setThreeSalecateName(salecategoryModel.getSaleCaleName());
			break;
		case 4:
			salecateProductModel.setFourSalecateId(salecategoryModel.getSaleCateId());
			salecateProductModel.setFourSalecateName(salecategoryModel.getSaleCaleName());
			break;
		}
	}

	private ProductModel mergeAddProduct(EbsMaterialModel ebsMaterialModel, Long companyId) {
		ProductModel product = new ProductModel();
		product.setStatus(ebsMaterialModel.getStatus());
		product.setProductName(ebsMaterialModel.getItemDesc());
		product.setSuppliesCode(ebsMaterialModel.getItemNumber());
		product.setSuppliesId(ebsMaterialModel.getItemId());
		product.setSuppliesDescribe(ebsMaterialModel.getItemDesc());
		product.setSecondaryUom(ebsMaterialModel.getSecondaryUom());
		product.setPrimaryUom(ebsMaterialModel.getPrimaryUom());
		product.setStatus(ebsMaterialModel.getStatus());
		product.setCompanyId(companyId);
		product.setGmtModified(new Date());

		product.setOneCateDesc(ebsMaterialModel.getOneCatDes());
		product.setTwoCateDesc(ebsMaterialModel.getTwoCatDes());
		product.setThreeCateDesc(ebsMaterialModel.getThreeCatDes());
		product.setFourCateDesc(ebsMaterialModel.getFourCatDes());

		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(ebsMaterialModel.getOneCat())) {
			sb.append(ebsMaterialModel.getOneCat());
			product.setOneCateId(ebsMaterialModel.getOneCat());
		}

		if (StringUtils.isNotEmpty(ebsMaterialModel.getTwoCat())) {
			sb.append(ebsMaterialModel.getTwoCat());
			product.setTwoCateId(sb.toString());
		}

		if (StringUtils.isNotEmpty(ebsMaterialModel.getThreeCat())) {
			sb.append(ebsMaterialModel.getThreeCat());
			product.setThreeCateId(sb.toString());
		}

		if (StringUtils.isNotEmpty(ebsMaterialModel.getFourCat())) {
			sb.append(ebsMaterialModel.getFourCat());
			product.setFourCateId(sb.toString());
		}

		product.setProductCode("O" + companyId + "P" + ebsMaterialModel.getItemNumber());
		product.setOrgId(ebsMaterialModel.getOrgId());
		product.setCateCode(sb.toString());

		return product;
	}

	/**
	 * 查询商品信息
	 *
	 * @param dtoReq
	 * @return
	 */
	public ProductSkuListDtoResult getProduct(ProductQueryDtoReq dtoReq) {
		ProductSkuListDtoResult productSkuListDtoResult = new ProductSkuListDtoResult();
		productSkuListDtoResult.setCode(ResultCode.SUCCESS.getCode());
		productSkuListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());

		dtoReq.setStatus(EbsMaterialStatusEnums.Active.getValue());
//		ReflectUtil.resetAttributeValue(dtoReq, false);
		List<ProductSkuExModel> productSkuExModels = null;
		if (!dtoReq.isVerify()) {
			if (dtoReq.getPage() == null || dtoReq.getPage() == 0) {
				dtoReq.setPage(1L);
			}
			if (dtoReq.getPageSize() == null || dtoReq.getPageSize() == 0) {
				dtoReq.setPageSize(Integer.MAX_VALUE);
			}
			Long count = productSkuDao.queryProductCount(dtoReq);
			productSkuListDtoResult.setPage(dtoReq.getPage());
			if (count <= 0) {
				productSkuListDtoResult.setTotalPage(0L);
				productSkuListDtoResult.setTotalCount(0L);
				return productSkuListDtoResult;
			}
			productSkuListDtoResult.setTotalCount(count);
			productSkuListDtoResult.setTotalPage(pages(count, dtoReq.getPageSize()));
			Long start = (dtoReq.getPage() - 1) * dtoReq.getPageSize();
			productSkuExModels = productSkuDao.queryProductList(dtoReq, start, dtoReq.getPageSize());
		} else {
			productSkuExModels = productSkuDao.queryProductList(dtoReq, null, null);
		}
		List<ProductSkuDtoResult> productSkuDtoResults = new ArrayList<>();
		for (ProductSkuExModel productSkuExModel : productSkuExModels) {
			ProductSkuDtoResult productSkuDtoResult = new ProductSkuDtoResult();
			BeanUtils.copyProperties(productSkuExModel, productSkuDtoResult);
			productSkuDtoResults.add(productSkuDtoResult);
		}
		productSkuListDtoResult.setProductSkuList(productSkuDtoResults);
		return productSkuListDtoResult;
	}

	/**
	 * 根据ebs条件查询商品信息
	 *
	 * @param dtoReq
	 * @return
	 */
	@Override
	public ProductSkuListDtoResult getEbsProduct(ProductEbsQueryDtoReq dtoReq) {

		ProductSkuListDtoResult productSkuListDtoResult = new ProductSkuListDtoResult();
		productSkuListDtoResult.setCode(ResultCode.SUCCESS.getCode());
		productSkuListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		dtoReq.setStatus(EbsMaterialStatusEnums.Active.getValue());
//		ReflectUtil.resetAttributeValue(dtoReq, false);
		List<ProductSkuExModel> productSkuExModels = productSkuDao.queryEbsProduct(dtoReq);
		List<ProductSkuDtoResult> productSkuDtoResults = new ArrayList<>();
		for (ProductSkuExModel productSkuExModel : productSkuExModels) {
			ProductSkuDtoResult productSkuDtoResult = new ProductSkuDtoResult();
			BeanUtils.copyProperties(productSkuExModel, productSkuDtoResult);
			productSkuDtoResults.add(productSkuDtoResult);
		}
		productSkuListDtoResult.setProductSkuList(productSkuDtoResults);
		return productSkuListDtoResult;
	}

	@Override
	@Async
	public void asyncProduct(String orgId) {
		String key = CommonConstant.SYNC_MATERIAL_KEY;
		if (StringUtils.isNotEmpty(orgId)) {
			key += orgId;
		} else {
			orgId = CommonConstant.ALL_ORG;
		}
		Object status = cacheData.getData(key);
		if (SyncEBSItemStatusEnums.RUNNING.getValue().equals(status)) {
			logger.error("同步中！");
			return;
		}

		BaseResponse<DtoResult> response = ebsSyncDataFeignClient.syncEBSMaterial(orgId);
		if (!ResultCode.SUCCESS.getCode().equals(response.getCode())) {
			logger.error("调用中台同步ebs物料失败");
			return;
		}
		try {
			cacheData.setData(key, SyncEBSItemStatusEnums.RUNNING.getValue());
			DtoResult productResult = syncProduct(orgId);
			if (!productResult.getCode().equals(ResultCode.SUCCESS.getCode())) {
				logger.error("初始化产品失败");
				return;
			}
			boolean flag = skuSer.allSkuInit(null);
			if (!flag) {
				logger.error("初始化sku失败");
			}
		} finally {
			cacheData.setData(key, SyncEBSItemStatusEnums.COMPLETE.getValue());
		}
	}

	@Override
	@Transactional
	public boolean updateBrand(BrandUpdateDtoReq dtoReq) {
		ProductModel oldModel = new ProductModel();
		ProductModel newModel = new ProductModel();
		oldModel.setBrandId(dtoReq.getBrandId());
		newModel.setBrandName(dtoReq.getBrandName());
		newModel.setBrandId(dtoReq.getNewBrandId());
		long count = productDao.update(oldModel, newModel);
		if (count <= 0) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean deleteBrand(BrandUpdateDtoReq dtoReq) {
		long count = productDao.deleteBrand(dtoReq);
		if (count <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateProduct(ProductDtoReq updateProductReq) {
		logger.info("productCode="+updateProductReq.getProductCode());
		if (StringUtils.isBlank(updateProductReq.getProductCode())) {
			return false;
		}
		ProductModel oldModel = new ProductModel();
		oldModel.setProductCode(updateProductReq.getProductCode());
		ProductModel newModel = new ProductModel();
		BeanUtils.copyProperties(updateProductReq, newModel);
		productDao.update(oldModel, newModel);
		return true;
	}
}
