package com.newhope.pay.biz.rpc.feign;

import com.newhope.moneyfeed.order.api.rest.OrderFeedTransportAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-order.name}")
public interface OrderTransportFeignClient extends OrderFeedTransportAPI {
}
