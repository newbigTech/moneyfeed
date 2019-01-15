package com.newhope.openapi.biz.rpc.feign.order;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.order.api.rest.OrderRuleAPI;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 19:09
 */
@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderRuleFeignClient extends OrderRuleAPI {
}
