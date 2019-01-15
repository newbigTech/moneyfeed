package com.newhope.moneyfeed.integration.biz.rpc.feign.order;

import com.newhope.moneyfeed.order.api.rest.OrderIntegrationAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by yuyanlin on 2018/11/21
 */
@FeignClient(value = "${feign.client.moneyfeed.order.name}")
public interface OrderFeignClient extends OrderIntegrationAPI {
}
