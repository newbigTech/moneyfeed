package com.newhope.openapi.biz.rpc.feign.pay;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.pay.api.rest.pay.PayOrderInfoAPI;

@FeignClient(value = "${feign.client.moneyfeed-pay.name}")
public interface PayOrderFeignClient extends PayOrderInfoAPI{

}
