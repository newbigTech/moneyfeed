package com.newhope.moneyfeed.api.service.wechat;

import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;

/**
 * Created by liming on 2018/11/21.
 */
public interface WechatMsgSendService {
    DtoResult sendMsg(WechatMsgDtoReq wechatMsgDtoReq);
}