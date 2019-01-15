package com.newhope.openapi.biz.service.product;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.response.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryListDtoResult;
import com.newhope.openapi.api.vo.request.category.CategoryQueryReq;
import com.newhope.openapi.api.vo.request.category.SaleCategoryQueryReq;
import com.newhope.openapi.api.vo.response.category.CategoryResult;
import com.newhope.openapi.api.vo.response.product.SaleCategoryListResult;
import com.newhope.openapi.api.vo.response.product.SaleCategoryResult;
import com.newhope.openapi.biz.rpc.feign.product.CategoryFeignClient;
import com.newhope.openapi.biz.rpc.feign.product.SaleCategoryFeignClient;

@Service
public class CategoryService {

	@Autowired
	CategoryFeignClient categoryFeignClient;

	public CategoryResult queryCategory(CategoryQueryReq dtoReq) {
		CategoryResult result = new CategoryResult();

		CategoryQueryDtoReq queryDto = new CategoryQueryDtoReq();
		BeanUtils.copyProperties(dtoReq, queryDto);
		BaseResponse<CategoryListDtoResult> feignResp = categoryFeignClient.queryCategory(queryDto);

		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());

		if (!feignResp.isSuccess() || null == feignResp.getData()
				|| CollectionUtils.isEmpty(feignResp.getData().getCategoryDtoResultList())) {
			return result;
		}

		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		result.setCategoryDtoResultList(feignResp.getData().getCategoryDtoResultList());
		return result;
	}

	public Result initCategory() {
		Result result = new Result();
		BaseResponse<DtoResult> feignResp = categoryFeignClient.initCategory();
		if (!feignResp.isSuccess()) {
			return result;
		}
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		return result;
	}

	@Autowired
	SaleCategoryFeignClient saleCategoryFeignClient;

	public SaleCategoryListResult querySaleCategory(SaleCategoryQueryReq queryReq) {
		SaleCategoryListResult saleCategoryListResult = new SaleCategoryListResult();

		SaleCategoryQueryDtoReq saleCategoryQueryDtoReq = new SaleCategoryQueryDtoReq();
		BeanUtils.copyProperties(queryReq, saleCategoryQueryDtoReq);
		BaseResponse<SaleCategoryListDtoResult> feignResult = saleCategoryFeignClient
				.querySaleCategory(saleCategoryQueryDtoReq);

		saleCategoryListResult.setCode(feignResult.getCode());
		saleCategoryListResult.setMsg(feignResult.getMsg());
		if (!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) || null == feignResult.getData()) {
			return saleCategoryListResult;
		}

		for (SaleCategoryDtoResult saleCategoryDtoResult : feignResult.getData().getSaleCategoryDtoResultList()) {
			SaleCategoryResult saleCategoryResult = new SaleCategoryResult();
			BeanUtils.copyProperties(saleCategoryDtoResult, saleCategoryResult);
			saleCategoryListResult.getSaleCategoryResultList().add(saleCategoryResult);
		}

		return saleCategoryListResult;
	}

}
