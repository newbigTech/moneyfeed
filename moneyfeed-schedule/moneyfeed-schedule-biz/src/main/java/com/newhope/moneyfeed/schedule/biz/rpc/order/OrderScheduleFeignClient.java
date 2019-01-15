package com.newhope.moneyfeed.schedule.biz.rpc.order;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.order.api.rest.OrderScheduleAPI;

@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderScheduleFeignClient extends OrderScheduleAPI {
}
