package com.newhope.openapi.biz.rpc.feign.product;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.goods.api.rest.SaleCategoryApi;

@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface SaleCategoryFeignClient extends SaleCategoryApi {
}
