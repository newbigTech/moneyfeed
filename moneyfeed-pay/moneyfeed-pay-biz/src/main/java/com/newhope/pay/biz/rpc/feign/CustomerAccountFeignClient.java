package com.newhope.pay.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.client.CustomerAccountAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface CustomerAccountFeignClient extends CustomerAccountAPI {
}
