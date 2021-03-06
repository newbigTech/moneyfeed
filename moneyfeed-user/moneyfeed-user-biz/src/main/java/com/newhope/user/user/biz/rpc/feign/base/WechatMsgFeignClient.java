package com.newhope.user.user.biz.rpc.feign.base;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.api.rest.wechat.WechatMsgAPI;

@FeignClient(value = "${feign.client.moneyfeed.name}")
public interface WechatMsgFeignClient extends WechatMsgAPI {
}
