package com.newhope.moneyfeed.schedule.biz.rpc.mq;

import com.newhope.moneyfeed.api.rest.mq.MqRetryAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by liming on 2018/12/19.
 */
@FeignClient(value = "${feign.client.moneyfeed.name}")
public interface MqReTryClient extends MqRetryAPI {

}