package com.newhope.openapi.api.rest.product;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/12/24 14:51
 */
@Api(value = "商品中心", description = "品牌中心REST API", protocols = "http")
public interface BrandAPI {

    @ApiOperation(value = "查询品牌信息", notes = "查询品牌信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "saleCateId", value = "目录id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "saleCateLevel", value = "目录层级", dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/pc/brand/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<BrandListResult> queryBrandList(BrandQueryReq brandQueryReq);

}
