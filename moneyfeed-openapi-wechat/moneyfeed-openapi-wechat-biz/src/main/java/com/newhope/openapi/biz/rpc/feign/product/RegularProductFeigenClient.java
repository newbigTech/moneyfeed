package com.newhope.openapi.biz.rpc.feign.product;

import com.newhope.moneyfeed.goods.api.rest.RegularProductApi;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface RegularProductFeigenClient extends RegularProductApi {
}
