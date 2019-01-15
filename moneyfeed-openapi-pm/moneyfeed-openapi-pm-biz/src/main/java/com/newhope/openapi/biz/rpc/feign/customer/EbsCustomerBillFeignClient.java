package com.newhope.openapi.biz.rpc.feign.customer;

import com.newhope.moneyfeed.integration.api.rest.ebs.bill.EbsCustomerBillAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "${feign.client.moneyfeed-integration.name}")
public interface EbsCustomerBillFeignClient extends EbsCustomerBillAPI {
}
