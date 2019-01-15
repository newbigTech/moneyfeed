package com.newhope.moneyfeed.schedule.biz.rpc.pc;


import com.newhope.moneyfeed.goods.api.rest.RegularProductApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface RegularProductFeignClient extends RegularProductApi {

}