package com.newhope.feedback.biz.rpc.feign.wechat;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.api.rest.wechat.WechatMsgAPI;

@FeignClient(value = "${feign.client.moneyfeed-api.name}")
public interface WechatMsgFeignClient extends WechatMsgAPI {

}
