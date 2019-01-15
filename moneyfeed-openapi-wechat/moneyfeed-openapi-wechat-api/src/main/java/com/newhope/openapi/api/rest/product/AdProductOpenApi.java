package com.newhope.openapi.api.rest.product;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.product.AdProductQueryReq;
import com.newhope.openapi.api.vo.response.product.AdProductListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Api(value = "商品中心", description = "商品中心open-api", protocols = "http")
public interface AdProductOpenApi {

    @ApiOperation(value = "查询广告聚合商品", notes = "查询广告聚合商品", protocols = "http")
    @RequestMapping(value = "/pc/agg/adproduct/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<AdProductListResult> queryAdProduct(AdProductQueryReq adProductQueryReq);

}
