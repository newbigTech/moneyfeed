package com.newhope.openapi.biz.rpc.feign.user;


import com.newhope.moneyfeed.user.api.rest.client.UserShopApplyAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface UserShopApplyFeignClient extends UserShopApplyAPI {
}
