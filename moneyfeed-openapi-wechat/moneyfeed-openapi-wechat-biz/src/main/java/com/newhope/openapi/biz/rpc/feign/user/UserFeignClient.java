package com.newhope.openapi.biz.rpc.feign.user;

import com.newhope.moneyfeed.user.api.rest.businessmanage.BmUserAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Create by yyq on 2018/12/26
 */
@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface UserFeignClient extends BmUserAPI {

}