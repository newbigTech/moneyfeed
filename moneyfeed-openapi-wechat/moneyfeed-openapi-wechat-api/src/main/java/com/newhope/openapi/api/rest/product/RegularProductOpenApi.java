package com.newhope.openapi.api.rest.product;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.response.product.ProductSkuListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心open-api", protocols = "http")
public interface RegularProductOpenApi {

    @ApiOperation(value = "查询常购清单商品列表", notes = "查询常购清单商品列表", protocols = "http")
    @RequestMapping(value = "/pc/regular/product/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<ProductSkuListResult> queryRegularProductList();
}
