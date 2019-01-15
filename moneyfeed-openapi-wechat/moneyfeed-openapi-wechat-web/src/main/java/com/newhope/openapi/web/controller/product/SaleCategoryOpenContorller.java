package com.newhope.openapi.web.controller.product;


import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.SaleCategoryOpenApi;
import com.newhope.openapi.api.vo.request.product.SaleCategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.SaleCategoryListResult;
import com.newhope.openapi.biz.service.product.SaleCatetoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleCategoryOpenContorller extends AbstractController implements SaleCategoryOpenApi {

    @Autowired
    SaleCatetoryService saleCatetoryService;

    @Override
    public BaseResponse<SaleCategoryListResult> querySaleCategory(SaleCategoryQueryReq dtoReq) {
        return buildJson(saleCatetoryService.querySaleCategory(dtoReq));
    };

    @Override
    public BaseResponse<SaleCategoryListResult> queryCustomerSaleCategory(SaleCategoryQueryReq dtoReq) {
        return buildJson(saleCatetoryService.queryCustomerSaleCategory(dtoReq));
    };
}
