package com.newhope.openapi.biz.service.product;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.util.ExportMultipleSheetExcel;
import com.newhope.moneyfeed.goods.api.constant.CommonConstant;
import com.newhope.moneyfeed.goods.api.dto.request.ProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductSkusQueryDto;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.openapi.api.vo.request.product.ProductQueryReq;
import com.newhope.openapi.api.vo.request.product.ProductSkusQueryReq;
import com.newhope.openapi.api.vo.response.product.ProductSkuResult;
import com.newhope.openapi.api.vo.response.product.ProductSyncHeartbeatResult;
import com.newhope.openapi.biz.rpc.feign.product.ProductFeignClient;
import com.newhope.openapi.biz.rpc.feign.product.ProductSkuFeignClient;

@Service
public class ProductSkuService {

	private Logger logger = Logger.getLogger(ProductSkuService.class);
	@Autowired
	ProductSkuFeignClient productSkuFeignClient;
	@Autowired
	ProductFeignClient productFeignClient;
	@Autowired
	private CacheData cacheData;

	public ProductSkuResult getProductSku(ProductSkusQueryReq dtoReq) {
		ProductSkuResult result = new ProductSkuResult();

		ProductSkusQueryDto queryDto = new ProductSkusQueryDto();
		BeanUtils.copyProperties(dtoReq, queryDto);
		
		logger.info("dtoReq.getBrandIds()=="+dtoReq.getBrandIds());
		logger.info("queryDto.getBrandIds()=="+queryDto.getBrandIds());
		
		BaseResponse<ProductSkuListDtoResult> feignResp = productSkuFeignClient.getProductSku(queryDto);

		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());

		if (!feignResp.isSuccess() || null == feignResp.getData()
				|| CollectionUtils.isEmpty(feignResp.getData().getProductSkuList())) {
			return result;
		}

		result.setPage(dtoReq.getPage());
		result.setTotalCount(feignResp.getData().getTotalCount());
		result.setTotalPage(feignResp.getData().getTotalPage());
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		result.setProductSkuList(feignResp.getData().getProductSkuList());
		return result;
	}

	public ProductSkuResult getProduct(ProductSkusQueryReq productQueryReq, boolean verify) {
		ProductSkuResult result = new ProductSkuResult();
		if (StringUtils.isBlank(productQueryReq.getCustomerNum()) || productQueryReq.getShopId() == null) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("customerNum和shopId不能为空");
			return result;
		}
		ProductQueryDtoReq dtoReq = new ProductQueryDtoReq();
		BeanUtils.copyProperties(productQueryReq, dtoReq);
		dtoReq.setVerify(verify);
		BaseResponse<ProductSkuListDtoResult> feignResp = productFeignClient.getProduct(dtoReq);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());

		if (!ResultCode.SUCCESS.getCode().equals(feignResp.getCode()) || null == feignResp.getData()) {
			return result;
		}
		result.setPage(feignResp.getData().getPage());
		result.setTotalCount(feignResp.getData().getTotalCount());
		result.setTotalPage(feignResp.getData().getTotalPage());

		result.setProductSkuList(feignResp.getData().getProductSkuList());
		return result;
	}

	public Result productSkuExport(ProductSkusQueryReq dtoReq, HttpServletResponse response) {
		Result result = new Result();
		dtoReq.setPageSize(Integer.MAX_VALUE);
		ProductSkusQueryDto queryDto = new ProductSkusQueryDto();
		BeanUtils.copyProperties(dtoReq, queryDto);
		BaseResponse<ProductSkuListDtoResult> feignResp = productSkuFeignClient.getProductSku(queryDto);

		List<ProductSkuDtoResult> allTotal = feignResp.getData().getProductSkuList();

		List<ExcelProductSku> excelDataList = new ArrayList<ExcelProductSku>();
		for (ProductSkuDtoResult ps : allTotal) {
			ExcelProductSku excel = new ExcelProductSku();
			BeanUtils.copyProperties(ps, excel);
			excelDataList.add(excel);
		}

		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		map.put("商品列表", ExcelProductSku.class);
		Map exDataList = new LinkedHashMap();
		exDataList.put("商品列表", excelDataList);
		ExportMultipleSheetExcel exportMultipleSheetExcel = new ExportMultipleSheetExcel(map, exDataList);
		String fileName = "商品列表" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
		try {
			exportMultipleSheetExcel.write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return result;
	}

	public BaseResponse<?> initProductSku() {
		BaseResponse<?> feignResp = productSkuFeignClient.productSkuInit(CommonConstant.ALL_ORG);
		return feignResp;
	}

	public ProductSyncHeartbeatResult heartbeat() {
		ProductSyncHeartbeatResult result=new ProductSyncHeartbeatResult();
		Object status = cacheData.getData(CommonConstant.SYNC_MATERIAL_KEY);
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		result.setStatus(status);
		return result;
	}
	
	public BaseResponse<?> updateProductByCode(ProductQueryReq updateReq) {
		ProductDtoReq updateDtoReq=new ProductDtoReq();
		BeanUtils.copyProperties(updateReq, updateDtoReq);
		logger.info(updateReq.getProductCode()+" ==== "+updateReq.getProductCode());
		BaseResponse<?> feignResp = productFeignClient.updateProduct(updateDtoReq);
		return feignResp;
	}
}
