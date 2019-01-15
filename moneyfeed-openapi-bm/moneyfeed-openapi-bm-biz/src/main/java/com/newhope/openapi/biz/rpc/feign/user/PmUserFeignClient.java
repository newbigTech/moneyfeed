package com.newhope.openapi.biz.rpc.feign.user;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.platform.PmUserAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface PmUserFeignClient extends PmUserAPI {

}
