package com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.bill;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.integration.api.rest.ebs.bill.EbsCustomerBillAPI;

@FeignClient(value = "${feign.client.moneyfeed.integration.name}")
public interface IntergrationEbsSyncBillFeignClient extends EbsCustomerBillAPI{

}
