package com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.order;

import com.newhope.moneyfeed.integration.api.rest.ebs.order.MoneyfeedToEbsOrderAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by Dave Chen on 2018/11/27.
 */
@FeignClient(value = "${feign.client.moneyfeed.integration.name}")
public interface IntegrtionMoneyFeedToEbsClient extends MoneyfeedToEbsOrderAPI {
}
