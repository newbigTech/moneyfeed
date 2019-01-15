package com.newhope.moneyfeed.goods.api.rest;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心REST API", protocols = "http")
public interface SaleCategoryApi {



    @ApiOperation(value = "查询销售分类", notes = "查询查询销售分类", protocols = "http")
    @RequestMapping(value = "/pc/salecategory/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<SaleCategoryListDtoResult> querySaleCategory(@RequestBody SaleCategoryQueryDtoReq dtoReq);

    @ApiOperation(value = "查询用户所属销售目录", notes = "查询用户所属销售目录", protocols = "http")
    @RequestMapping(value = "/pc/customer/salecategory/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<SaleCategoryListDtoResult> queryCustomerSaleCategory(SaleCategoryQueryDtoReq dtoReq);
}
