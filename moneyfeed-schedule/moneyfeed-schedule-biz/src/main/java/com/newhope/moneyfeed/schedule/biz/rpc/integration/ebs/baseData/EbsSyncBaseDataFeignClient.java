package com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.baseData;

import com.newhope.moneyfeed.integration.api.rest.ebs.EbsSyncDataAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by liming on 2018/7/11.
 */
@FeignClient(value = "${feign.client.moneyfeed.integration.name}")
public interface EbsSyncBaseDataFeignClient extends EbsSyncDataAPI {
}