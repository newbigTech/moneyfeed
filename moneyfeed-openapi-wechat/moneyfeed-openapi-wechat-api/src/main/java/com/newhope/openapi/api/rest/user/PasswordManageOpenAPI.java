package com.newhope.openapi.api.rest.user;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.user.PasswordManageReq;
import com.newhope.openapi.api.vo.response.user.PasswordQueryResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(description = "账户管理-支付密码", tags = "PasswordManageOpenAPI", protocols = "http")
@RequestMapping("/user/password")
public interface PasswordManageOpenAPI {
    @ApiOperation(value = "账户管理-支付密码-创建支付密码", notes = "账户管理-支付密码-创建支付密码")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "PasswordManageReq")
    })
    BaseResponse<Result> create(@Valid @RequestBody PasswordManageReq request);

    @ApiOperation(value = "账户管理-支付密码-修改支付密码", notes = "账户管理-支付密码-修改支付密码")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "PasswordManageReq")
    })
    BaseResponse<Result> update(@Valid @RequestBody PasswordManageReq request);

    @ApiOperation(value = "账户管理-支付密码-查询是否有支付密码，以及对应密码管理的手机号", notes = "账户管理-支付密码-查询是否有支付密码，以及对应密码管理的手机号")
    @RequestMapping(method = RequestMethod.GET)
    BaseResponse<PasswordQueryResult> query();
}
