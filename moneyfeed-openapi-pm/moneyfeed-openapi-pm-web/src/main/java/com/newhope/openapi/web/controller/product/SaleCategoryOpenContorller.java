package com.newhope.openapi.web.controller.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.SaleCategoryOpenApi;
import com.newhope.openapi.api.vo.request.category.SaleCategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.SaleCategoryListResult;
import com.newhope.openapi.biz.service.product.CategoryService;

@RestController
public class SaleCategoryOpenContorller extends AbstractController implements SaleCategoryOpenApi {

    @Autowired
    CategoryService catetorySer;

    @Override
    public BaseResponse<SaleCategoryListResult> queryCategory(SaleCategoryQueryReq dtoReq) {
        return buildJson(catetorySer.querySaleCategory(dtoReq));
    };
}
