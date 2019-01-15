package com.newhope.openapi.api.rest.product;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.request.product.BrandUpdateReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : tom
 * @project: moneyfeed-goods
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

    @ApiOperation(value = "添加品牌信息", notes = "添加品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/add", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<Result> addBrand(@RequestBody BrandUpdateReq req);

    @ApiOperation(value = "修改品牌信息", notes = "修改品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand", method = RequestMethod.PUT, produces = {"application/json"})
    BaseResponse<Result> updateBrand(@RequestBody BrandUpdateReq req);

    @ApiOperation(value = "删除品牌信息", notes = "删除品牌信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "path", dataType = "Long")
    })
    @RequestMapping(value = "/pc/brand/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    BaseResponse<Result> deleteBrand(@PathVariable("id") Long id);


    @ApiOperation(value = "导出查询品牌信息", notes = "导出查询品牌信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandName", value = "商品名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "brandId", value = "品牌id", dataType = "Long", paramType = "query"), })
    @RequestMapping(value = "/pc/brand/export", method = RequestMethod.GET)
    BaseResponse<Result> brandExport(BrandQueryReq req, HttpServletResponse response);

    @ApiOperation(value = "查询平台品牌信息", notes = "查询平台品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/all", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<BrandListResult> queryBrandList();

}
