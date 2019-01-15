package com.newhope.openapi.biz.rpc.feign.user;


import com.newhope.moneyfeed.integration.api.rest.ebs.balance.EbsCustomerBalanceAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-integration.name}")
public interface EbsCustomerBalanceFeignClient extends EbsCustomerBalanceAPI {
}
