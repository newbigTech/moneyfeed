package com.newhope.moneyfeed.web.sms;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.sms.SmsVerdictDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.rest.sms.SmsAPI;
import com.newhope.moneyfeed.api.service.sms.SmsService;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 短信服务controller
 *
 * @author: zhangyanyan
 * @date: 2018/11/19
 */
@RestController
public class SmsController extends AbstractController implements SmsAPI {
    @Autowired
    SmsService smsService;

    @Override
    public BaseResponse<DtoResult> sendSms(@Valid @RequestBody SmsSendDtoReq dtoReq) {
        DtoResult result = smsService.sendSms(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> verdictSmsCode(@Valid @RequestBody SmsVerdictDtoReq dtoReq) {
        DtoResult result = smsService.verdictSmsCode(dtoReq);
        return buildJson(result);
    }
}
