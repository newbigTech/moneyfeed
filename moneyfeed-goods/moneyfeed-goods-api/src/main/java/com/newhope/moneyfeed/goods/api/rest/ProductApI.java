package com.newhope.moneyfeed.goods.api.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.ProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductEbsQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author : tom
 * @project: moneyfeedMapper-goods
 * @date : 2018/11/17 09:49
 */
@Api(value = "商品中心", description = "商品中心REST API", protocols = "http")
public interface ProductApI {

    @ApiOperation(value = "初始化商品数据", notes = "初始化商品数据", protocols = "http")
    @RequestMapping(value = "/pc/product/init", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> syncProduct();

    @ApiOperation(value = "同步公司所属商品", notes = "同步公司所属商品", protocols = "http")
    @RequestMapping(value = "/pc/product/company", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> productCompany(@RequestBody ProductUpdateDtoReq dtoReq);

    @ApiOperation(value = "同步指定商品", notes = "同步指定商品", protocols = "http")
    @RequestMapping(value = "/pc/product/single", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> productSingle(@RequestBody ProductUpdateDtoReq dtoReq);

    @ApiOperation(value = "查询商品信息", notes = "查询商品信息", protocols = "http")
    @RequestMapping(value = "/pc/product/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<ProductSkuListDtoResult> getProduct(@RequestBody ProductQueryDtoReq dtoReq);

    @ApiOperation(value = "根据ebs条件查询商品信息", notes = "根据ebs条件查询商品信息", protocols = "http")
    @RequestMapping(value = "/pc/product/ebs/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<ProductSkuListDtoResult> getEbsProduct(@RequestBody ProductEbsQueryDtoReq dtoReq);

    @ApiOperation(value = "根据productCode更新商品信息", notes = "根据productCode更新商品信息", protocols = "http")
    @RequestMapping(value = "/pc/product/update", method = RequestMethod.POST, produces = {"application/json"})   
    BaseResponse<?> updateProduct(ProductDtoReq updateReq);
}
