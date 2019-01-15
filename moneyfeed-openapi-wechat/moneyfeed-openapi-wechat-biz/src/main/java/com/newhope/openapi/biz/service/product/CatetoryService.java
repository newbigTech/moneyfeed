package com.newhope.openapi.biz.service.product;


import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;

import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryListDtoResult;
import com.newhope.openapi.api.vo.request.product.CategoryQueryReq;

import com.newhope.openapi.api.vo.response.product.CategoryListResult;
import com.newhope.openapi.api.vo.response.product.CategoryResult;

import com.newhope.openapi.biz.rpc.feign.product.CategoryFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatetoryService {

    @Autowired
    private CategoryFeignClient categoryFeignClient;


    public CategoryListResult queryCategory(CategoryQueryReq queryReq){

        CategoryListResult categoryListResult = new CategoryListResult();

        CategoryQueryDtoReq categoryQueryDtoReq = new CategoryQueryDtoReq();
        BeanUtils.copyProperties(queryReq,categoryQueryDtoReq);
        BaseResponse<CategoryListDtoResult> feignResult = categoryFeignClient.queryCategory(categoryQueryDtoReq);

        categoryListResult.setCode(feignResult.getCode());
        categoryListResult.setMsg(feignResult.getMsg());
        if (!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) || null == feignResult.getData()) {
            return categoryListResult;
        }

        for (CategoryDtoResult categoryDtoResult : feignResult.getData().getCategoryDtoResultList()) {
            CategoryResult categoryResult = new CategoryResult();
            BeanUtils.copyProperties(categoryDtoResult, categoryResult);
            categoryListResult.getCategoryResultList().add(categoryResult);
        }

        return categoryListResult;
    }
}
