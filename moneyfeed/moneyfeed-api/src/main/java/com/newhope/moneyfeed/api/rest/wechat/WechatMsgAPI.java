package com.newhope.moneyfeed.api.rest.wechat;

import com.newhope.moneyfeed.api.dto.request.wechat.WechatDtoReq;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.response.wechat.WechatDtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liming on 2018/11/21.
 */
@Api(value="WechatMsg", description="微信消息API", protocols="http")
@RequestMapping(value = "/base")
public interface WechatMsgAPI {

    @ApiOperation(value="发送微信消息", notes="发送微信消息", protocols="http")
    @RequestMapping(value = { "/wechat/msg/send"}, method = RequestMethod.POST)
    BaseResponse<DtoResult> sendWechatMsg(@RequestBody WechatMsgDtoReq wechatMsgDtoReq);

}