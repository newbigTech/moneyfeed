package com.newhope.openapi.biz.rpc.feign.customer;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.client.CustomerAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface CustomerFeignClient extends CustomerAPI {
	
}
