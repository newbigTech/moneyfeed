package com.newhope.moneyfeed.web.wechat;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.rest.wechat.WechatMsgAPI;
import com.newhope.moneyfeed.api.service.wechat.WechatMsgSendService;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liming on 2018/11/21.
 */
@RestController
public class WechatMsgSendController extends AbstractController implements WechatMsgAPI{


    @Autowired
    private WechatMsgSendService wechatMsgSendService;

    @Override
    public BaseResponse<DtoResult> sendWechatMsg(@RequestBody WechatMsgDtoReq wechatMsgDtoReq) {

        final DtoResult dtoResult = wechatMsgSendService.sendMsg(wechatMsgDtoReq);

        return buildJson(dtoResult);
    }
}