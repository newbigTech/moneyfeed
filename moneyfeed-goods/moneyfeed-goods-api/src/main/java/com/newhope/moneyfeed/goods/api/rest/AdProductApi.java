package com.newhope.moneyfeed.goods.api.rest;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.AdProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心REST API", protocols = "http")
public interface AdProductApi {

    @ApiOperation(value = "查询广告商品列表", notes = "查询广告商品列表", protocols = "http")
    @RequestMapping(value = "/pc/adproduct/list", method = RequestMethod.POST,consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<AdProductListDtoResult> queryAdProduct(@RequestBody AdProductQueryDtoReq dtoReq);


    @ApiOperation(value = "查询广告商品列表", notes = "查询广告商品列表", protocols = "http")
    @RequestMapping(value = "/pc/adproduct/category/list", method = RequestMethod.POST,consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<AdCategoryListDtoResult> queryAdProductCategory(@RequestBody AdProductQueryDtoReq dtoReq);
}
