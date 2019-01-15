package com.newhope.pay.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.order.api.rest.OrderAPI;

@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderFeignClient extends OrderAPI {
}
