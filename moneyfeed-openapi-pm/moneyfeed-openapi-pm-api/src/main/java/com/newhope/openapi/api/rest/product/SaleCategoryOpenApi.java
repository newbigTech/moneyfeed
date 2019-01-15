package com.newhope.openapi.api.rest.product;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.category.SaleCategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.SaleCategoryListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "商品中心", description = "商品中心open-api", protocols = "http")
public interface SaleCategoryOpenApi {

    @ApiOperation(value = "查询后台目录信息", notes = "查询后台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/salecategory/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<SaleCategoryListResult> queryCategory(@RequestBody SaleCategoryQueryReq dtoReq);
}
