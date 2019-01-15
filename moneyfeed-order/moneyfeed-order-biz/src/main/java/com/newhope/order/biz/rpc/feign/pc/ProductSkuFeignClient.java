package com.newhope.order.biz.rpc.feign.pc;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.goods.api.rest.ProductSkuAPI;

@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface ProductSkuFeignClient extends ProductSkuAPI{

}
