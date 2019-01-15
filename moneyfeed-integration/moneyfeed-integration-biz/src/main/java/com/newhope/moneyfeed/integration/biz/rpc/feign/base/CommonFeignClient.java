package com.newhope.moneyfeed.integration.biz.rpc.feign.base;

import com.newhope.moneyfeed.api.rest.uclog.UserOperationAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by Dave Chen on 2018/12/3.
 */
@FeignClient(value = "${feign.client.moneyfeed.common.name}")
public interface CommonFeignClient extends UserOperationAPI {
}
