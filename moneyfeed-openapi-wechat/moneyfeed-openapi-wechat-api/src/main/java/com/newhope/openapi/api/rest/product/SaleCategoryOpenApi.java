package com.newhope.openapi.api.rest.product;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.product.SaleCategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.SaleCategoryListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心open-api", protocols = "http")
public interface SaleCategoryOpenApi {

    @ApiOperation(value = "查询前台目录信息", notes = "查询前台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/salecategory/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<SaleCategoryListResult> querySaleCategory(@RequestBody SaleCategoryQueryReq dtoReq);


    @ApiOperation(value = "查询客户关联前台目录信息", notes = "查询前台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/customer/salecategory/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<SaleCategoryListResult> queryCustomerSaleCategory(@RequestBody SaleCategoryQueryReq dtoReq);
}
