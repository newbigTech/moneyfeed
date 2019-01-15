package com.newhope.pay.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.order.api.rest.OrderIntegrationAPI;

@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderIntegrationFeignClient extends OrderIntegrationAPI {
}
