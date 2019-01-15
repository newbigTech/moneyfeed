package com.newhope.openapi.api.rest.product;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.product.CategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.CategoryListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心open-api", protocols = "http")
public interface CategoryOpenApi {

    @ApiOperation(value = "查询后台目录信息", notes = "查询后台目录信息", protocols = "http")
    @RequestMapping(value = "/pc/category/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<CategoryListResult> queryCategory(CategoryQueryReq dtoReq);
}
