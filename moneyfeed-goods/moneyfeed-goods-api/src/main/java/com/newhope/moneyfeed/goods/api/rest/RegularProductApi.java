package com.newhope.moneyfeed.goods.api.rest;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品中心", description = "商品中心REST API", protocols = "http")
public interface RegularProductApi {

    @ApiOperation(value = "查询常购清单商品列表", notes = "查询常购清单商品列表", protocols = "http")
    @RequestMapping(value = "/pc/regular/product/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<ProductSkuListDtoResult> queryRegularProductList(@RequestBody RegularProductDtoReq dtoReq);


    @ApiOperation(value = "清洗常购清单商品列表", notes = "清洗常购清单商品列表", protocols = "http")
    @RequestMapping(value = "/pc/clean/regular/product/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<DtoResult> cleanRegularProduct();

}