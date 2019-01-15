package com.newhope.moneyfeed.goods.web.controller;

import com.newhope.goods.biz.service.SalecategoryService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.SaleCategoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleCategoryController extends AbstractController implements SaleCategoryApi {

    @Autowired
    private SalecategoryService saleCategoryService;

    @Override
    public BaseResponse<SaleCategoryListDtoResult> querySaleCategory(@RequestBody SaleCategoryQueryDtoReq dtoReq) {
        SaleCategoryListDtoResult reuslt = saleCategoryService.querySaleCategory(dtoReq);
        return buildJson(reuslt);
    }

    @Override
    public BaseResponse<SaleCategoryListDtoResult> queryCustomerSaleCategory(@RequestBody SaleCategoryQueryDtoReq dtoReq) {
        SaleCategoryListDtoResult reuslt = saleCategoryService.queryCustomerSaleCategory(dtoReq);
        return buildJson(reuslt);
    }
}
