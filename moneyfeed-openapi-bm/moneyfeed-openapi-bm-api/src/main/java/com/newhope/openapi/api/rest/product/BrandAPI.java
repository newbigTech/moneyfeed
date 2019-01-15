package com.newhope.openapi.api.rest.product;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : tom
 * @project: moneyfeed-openapi-bm
 * @date : 2018/12/19 14:27
 */
@Api(value = "商品中心", description = "品牌中心REST API", protocols = "http")
public interface BrandAPI {

    @ApiOperation(value = "查询品牌信息", notes = "查询品牌信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandName", value = "商品名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "brandId", value = "品牌id", dataType = "Long", paramType = "query"), })
    @RequestMapping(value = "/pc/brand/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<BrandListResult> queryBrandList(BrandQueryReq req);

    @ApiOperation(value = "导出查询品牌信息", notes = "导出查询品牌信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandName", value = "商品名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "brandId", value = "品牌id", dataType = "Long", paramType = "query"), })
    @RequestMapping(value = "/pc/brand/export", method = RequestMethod.GET)
    BaseResponse<Result> brandExport(BrandQueryReq req, HttpServletResponse response);

    @ApiOperation(value = "查询平台品牌信息", notes = "查询平台品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/all", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<BrandListResult> queryPmBrandList();

    @ApiOperation(value = "查询商家品牌信息", notes = "查询商家品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/shop", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<BrandListResult> queryBmBrandList();

}
