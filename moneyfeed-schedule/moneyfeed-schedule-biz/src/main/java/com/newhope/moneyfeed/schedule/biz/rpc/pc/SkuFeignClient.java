package com.newhope.moneyfeed.schedule.biz.rpc.pc;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface SkuFeignClient extends com.newhope.moneyfeed.goods.api.rest.SkuApi {

}
