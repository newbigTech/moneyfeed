package com.newhope.openapi.api.rest.user;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "CommonOpenAPI", description = "公用的REST API", protocols = "http")
public interface CommonOpenAPI {
    @ApiOperation(value = "短信验证码验证", notes = "短信验证码验证", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/sms/code/validate", method = RequestMethod.GET)
    BaseResponse<Result> validateSMSCode(@RequestParam(value = "phone") String phone, @RequestParam(value = "code") String code);

    @ApiOperation(value = "方便测试的登录接口", notes = "方便测试的登录接口", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "后台配置的key", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/login/test", method = RequestMethod.GET)
    BaseResponse<Result> loginForTest(@RequestParam("key") String key, @RequestParam("userId") Long userId);
}
