package com.newhope.openapi.api.rest.user;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.user.UserShopApplyReq;
import com.newhope.openapi.api.vo.response.user.ShopApplyCheckResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(description = "意向客户申请接口", tags = "UserShopApplyOpenAPI", protocols = "http")
@RequestMapping("/user/apply")
public interface UserShopApplyOpenAPI {
    @ApiOperation(value = "意向客户申请接口", notes = "意向客户申请接口")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "UserShopApplyReq")
    })
    BaseResponse<Result> create(@Valid @RequestBody UserShopApplyReq request);

    @ApiOperation(value = "查询当前登录用户是否已申请过意向", notes = "查询当前登录用户是否已申请过意向")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    BaseResponse<ShopApplyCheckResult> check();
}
