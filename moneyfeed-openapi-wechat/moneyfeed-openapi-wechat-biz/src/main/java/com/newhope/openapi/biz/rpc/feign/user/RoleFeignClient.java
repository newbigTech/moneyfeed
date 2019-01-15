package com.newhope.openapi.biz.rpc.feign.user;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.platform.PmRoleAPI;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface RoleFeignClient extends PmRoleAPI {
}
