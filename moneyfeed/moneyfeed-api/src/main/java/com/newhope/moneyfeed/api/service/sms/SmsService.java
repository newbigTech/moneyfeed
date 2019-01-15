package com.newhope.moneyfeed.api.service.sms;

import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.sms.SmsVerdictDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;

/**
 * 短信服务接口
 *
 * @author: zhangyanyan
 * @date: 2018/11/20
 */
public interface SmsService {

    /**
     * 发送短信
     *
     * @param dtoReq
     * @return
     */
    DtoResult sendSms(SmsSendDtoReq dtoReq);

    /**
     * 验证短信验证码
     *
     * @param dtoReq
     * @return
     */
    DtoResult verdictSmsCode(SmsVerdictDtoReq dtoReq);
}
