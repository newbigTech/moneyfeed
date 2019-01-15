package com.newhope.order.biz.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.integration.api.rest.ebs.balance.EbsCustomerBalanceAPI;

@FeignClient(value = "${feign.client.moneyfeed-integration.name}")
public interface EbsCustomerBalanceFeignClient extends EbsCustomerBalanceAPI{

}
