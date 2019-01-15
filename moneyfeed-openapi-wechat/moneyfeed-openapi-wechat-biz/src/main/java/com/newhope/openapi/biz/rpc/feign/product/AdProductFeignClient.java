package com.newhope.openapi.biz.rpc.feign.product;

import com.newhope.moneyfeed.goods.api.rest.AdProductApi;
import org.springframework.cloud.netflix.feign.FeignClient;



@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface AdProductFeignClient extends AdProductApi {
}
