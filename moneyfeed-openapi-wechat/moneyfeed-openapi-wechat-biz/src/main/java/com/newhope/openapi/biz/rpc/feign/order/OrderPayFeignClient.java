package com.newhope.openapi.biz.rpc.feign.order;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.order.api.rest.OrderPayAPI;


@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderPayFeignClient extends OrderPayAPI{

}
