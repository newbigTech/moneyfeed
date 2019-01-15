package com.newhope.moneyfeed.goods.api.rest;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.BrandListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:27
 */
@Api(value = "商品中心", description = "品牌中心REST API", protocols = "http")
public interface BrandAPI {

    @ApiOperation(value = "查询品牌信息", notes = "查询品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<BrandListDtoResult> queryBrandList(@RequestBody BrandQueryDtoReq dtoReq);

    @ApiOperation(value = "添加品牌信息", notes = "添加品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/add", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<DtoResult> addBrand(@RequestBody BrandUpdateDtoReq dtoReq);

    @ApiOperation(value = "修改品牌信息", notes = "修改品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand", method = RequestMethod.PUT, produces = {"application/json"})
    BaseResponse<DtoResult> updateBrand(@RequestBody BrandUpdateDtoReq dtoReq);

    @ApiOperation(value = "删除品牌信息", notes = "删除品牌信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "path", dataType = "Long")
    })
    @RequestMapping(value = "/pc/brand/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    BaseResponse<DtoResult> deleteBrand(@PathVariable("id") Long id);


    @ApiOperation(value = "查询商品品牌信息", notes = "查询商品品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/product/brand/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<BrandListDtoResult> queryProductBrandList(@RequestBody BrandQueryDtoReq dtoReq);

    @ApiOperation(value = "查询品牌信息", notes = "查询品牌信息", protocols = "http")
    @RequestMapping(value = "/pc/brand/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<BrandListDtoResult> queryBrandList();

}
