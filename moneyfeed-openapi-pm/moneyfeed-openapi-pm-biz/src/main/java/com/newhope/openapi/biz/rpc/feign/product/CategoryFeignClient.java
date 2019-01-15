package com.newhope.openapi.biz.rpc.feign.product;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.goods.api.rest.CategoryApi;

@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface CategoryFeignClient extends CategoryApi {

}
