package com.newhope.openapi.api.rest.product;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.product.ProductQueryReq;
import com.newhope.openapi.api.vo.response.product.ProductSkuListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : tom
 * @project: moneyfeedMapper-goods
 * @date : 2018/11/17 09:49
 */
@Api(value = "商品中心", description = "商品中心open-api", protocols = "http")
public interface ProductOpenApi {

    @ApiOperation(value = "查询商品信息", notes = "查询商品信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productName", value = "商品名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "saleCateId", value = "目录id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "saleCateLevel", value = "目录层级", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "shopId", value = "店铺id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "brandIds", value = "品牌ids", dataType = "List", paramType = "query"),
    })
    @RequestMapping(value = "/pc/product/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<ProductSkuListResult> getProduct(ProductQueryReq productQueryReq);

    @ApiOperation(value = "查询商品sku列表", notes = "查询商品sku列表", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productSkuIds", value = "商品sku Ids", dataType = "List", paramType = "query"),
    })
    @RequestMapping(value = "/pc/product/sku/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<ProductSkuListResult> getProductSku(ProductQueryReq productQueryReq);


}
