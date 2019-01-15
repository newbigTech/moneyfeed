package com.newhope.moneyfeed.api.rest.sms;

import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.sms.SmsVerdictDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 短信服务API
 *
 * @author: zhangyanyan
 * @date: 2018/11/19
 */
@Api(value = "SmsAPI", description = "短信API", protocols = "http")
public interface SmsAPI {

    @ApiOperation(value = "发送短信", notes = "发送短信", protocols = "http")
    @RequestMapping(value = "/base/sms/send", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> sendSms(@Valid @RequestBody SmsSendDtoReq dtoReq);

    @ApiOperation(value = "验证短信验证码", notes = "验证短信验证码", protocols = "http")
    @RequestMapping(value = "/base/sms/code/verdict", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> verdictSmsCode(@Valid @RequestBody SmsVerdictDtoReq dtoReq);

}

