package com.newhope.moneyfeed.goods.api.rest;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心REST API", protocols = "http")
public interface CategoryApi {

    @ApiOperation(value = "查询后台目录信息", notes = "查询后台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/category/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<CategoryListDtoResult> queryCategory(@RequestBody CategoryQueryDtoReq dtoReq);

    @ApiOperation(value = "初始化后台目录信息", notes = "初始化后台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/category/init", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<DtoResult> initCategory();


}
