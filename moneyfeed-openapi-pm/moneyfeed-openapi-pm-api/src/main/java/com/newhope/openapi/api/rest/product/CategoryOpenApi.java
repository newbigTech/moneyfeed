package com.newhope.openapi.api.rest.product;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.category.CategoryQueryReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "商品中心", description = "商品中心REST API", protocols = "http")
public interface CategoryOpenApi {

    @ApiOperation(value = "查询后台目录信息", notes = "查询后台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/category/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<Result> queryCategory(@RequestBody CategoryQueryReq dtoReq);


    @ApiOperation(value = "查询后台目录信息", notes = "查询后台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/category/init", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<Result> initCategory();

}
