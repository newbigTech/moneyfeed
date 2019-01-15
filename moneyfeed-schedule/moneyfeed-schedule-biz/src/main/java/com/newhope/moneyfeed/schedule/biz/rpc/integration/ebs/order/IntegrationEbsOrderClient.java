package com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.order;

import com.newhope.moneyfeed.integration.api.rest.ebs.order.EbsToMoneyfeedOrderAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by yuyanlin on 2018/11/21
 */
@FeignClient(value = "${feign.client.moneyfeed.integration.name}")
public interface IntegrationEbsOrderClient extends EbsToMoneyfeedOrderAPI {
}
