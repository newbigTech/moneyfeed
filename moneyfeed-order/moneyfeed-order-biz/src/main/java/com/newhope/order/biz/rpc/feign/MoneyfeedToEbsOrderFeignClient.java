package com.newhope.order.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.integration.api.rest.ebs.order.MoneyfeedToEbsOrderAPI;

@FeignClient(value = "${feign.client.moneyfeed-integration.name}")
public interface MoneyfeedToEbsOrderFeignClient extends MoneyfeedToEbsOrderAPI{

}
