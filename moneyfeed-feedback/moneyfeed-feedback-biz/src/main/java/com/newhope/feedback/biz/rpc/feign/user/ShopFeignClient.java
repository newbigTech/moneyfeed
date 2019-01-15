package com.newhope.feedback.biz.rpc.feign.user;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.client.ShopAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface ShopFeignClient extends ShopAPI {
	
}
