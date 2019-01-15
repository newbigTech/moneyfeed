package com.newhope.order.biz.rpc.feign.uc;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.client.ClientUserAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface ClientUserFeignClient extends ClientUserAPI {
}
