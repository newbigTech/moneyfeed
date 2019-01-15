package com.newhope.order.biz.rpc.feign.pay;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.pay.api.rest.pay.PayIntegrationAPI;

@FeignClient(value = "${feign.client.moneyfeed-pay.name}")
public interface PayIntegrationFeignClient extends PayIntegrationAPI {
}
