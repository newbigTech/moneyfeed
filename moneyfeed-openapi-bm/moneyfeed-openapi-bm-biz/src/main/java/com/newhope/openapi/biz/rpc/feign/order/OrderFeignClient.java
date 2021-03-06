package com.newhope.openapi.biz.rpc.feign.order;

import com.newhope.moneyfeed.order.api.rest.OrderAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Create by yyq on 2018/11/19
 */
@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderFeignClient extends OrderAPI {

}
