package com.newhope.order.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.api.rest.sms.SmsAPI;

@FeignClient(value = "${feign.client.moneyfeed.name}")
public interface SmsFeignClient extends SmsAPI {
}
