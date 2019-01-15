package com.newhope.openapi.biz.rpc.feign.user;

import com.newhope.moneyfeed.user.api.rest.client.ClientUserAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/30 17:25
 */
@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface ClientUserFeginClient extends ClientUserAPI {
}
