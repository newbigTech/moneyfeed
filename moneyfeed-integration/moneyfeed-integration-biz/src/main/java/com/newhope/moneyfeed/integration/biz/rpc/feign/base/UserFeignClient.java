package com.newhope.moneyfeed.integration.biz.rpc.feign.base;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.user.api.rest.client.EbsCustomerAPI;

@FeignClient(value = "${feign.client.moneyfeed.user.name}")
public interface UserFeignClient extends EbsCustomerAPI{

}
