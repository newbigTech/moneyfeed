package com.newhope.openapi.biz.rpc.feign.wechat;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.api.rest.user.UserAPI;

@FeignClient(value = "${feign.client.moneyfeed.name}")
public interface WechatUserFeignClient extends UserAPI {

}
