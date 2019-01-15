package com.newhope.openapi.web.controller.product;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.CategoryOpenApi;
import com.newhope.openapi.api.vo.request.product.CategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.CategoryListResult;
import com.newhope.openapi.biz.service.product.CatetoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryOpenContorller extends AbstractController implements CategoryOpenApi {
    @Autowired
    CatetoryService catetoryService;


    @Override
    public BaseResponse<CategoryListResult> queryCategory(CategoryQueryReq dtoReq) {
        return buildJson(catetoryService.queryCategory(dtoReq));
    }
}
