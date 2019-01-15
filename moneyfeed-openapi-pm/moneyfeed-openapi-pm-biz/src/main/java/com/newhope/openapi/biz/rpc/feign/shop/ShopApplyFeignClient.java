package com.newhope.openapi.biz.rpc.feign.shop;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.client.UserShopApplyAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface ShopApplyFeignClient extends UserShopApplyAPI {

}
