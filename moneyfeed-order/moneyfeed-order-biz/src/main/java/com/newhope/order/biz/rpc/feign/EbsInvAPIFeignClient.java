package com.newhope.order.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.integration.api.rest.ebs.inv.EbsInvAPI;

@FeignClient(value = "${feign.client.moneyfeed-integration.name}")
public interface EbsInvAPIFeignClient extends EbsInvAPI{

}
