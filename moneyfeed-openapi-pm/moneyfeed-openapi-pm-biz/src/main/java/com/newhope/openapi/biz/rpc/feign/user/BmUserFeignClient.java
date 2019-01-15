package com.newhope.openapi.biz.rpc.feign.user;

import com.newhope.moneyfeed.user.api.rest.businessmanage.BmUserAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface BmUserFeignClient extends BmUserAPI {

}
