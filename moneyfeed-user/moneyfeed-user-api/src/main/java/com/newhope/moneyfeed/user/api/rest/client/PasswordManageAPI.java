package com.newhope.moneyfeed.user.api.rest.client;


import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.PasswordManageDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.PayPasswordVerifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordQueryDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(description = "账户管理-支付密码", tags = "PasswordManageOpenAPI", protocols = "http")
@RequestMapping("/uc/user/password")
public interface PasswordManageAPI {
    @ApiOperation(value = "账户管理-支付密码-创建支付密码", notes = "账户管理-支付密码-创建支付密码")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "PasswordManageDtoReq")
    })
    BaseResponse<DtoResult> create(@Valid @RequestBody PasswordManageDtoReq request);

    @ApiOperation(value = "账户管理-支付密码-修改支付密码", notes = "账户管理-支付密码-修改支付密码")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "PasswordManageDtoReq")
    })
    BaseResponse<DtoResult> update(@Valid @RequestBody PasswordManageDtoReq request);

    @ApiOperation(value = "账户管理-支付密码-查询是否有支付密码，以及对应密码管理的手机号", notes = "账户管理-支付密码-查询是否有支付密码，以及对应密码管理的手机号")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", required = true, paramType = "query", dataType = "Long")
    })
    BaseResponse<PasswordQueryDtoResult> query(@RequestParam("userId") Long userId);

    @ApiOperation(value = "账户管理-校验支付密码-校验支付密码", notes = "账户管理-校验支付密码-校验支付密码")
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "PayPasswordVerifyDtoReq")
    })
    BaseResponse<DtoResult> verify(@Valid @RequestBody PayPasswordVerifyDtoReq request);
}