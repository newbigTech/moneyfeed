package com.newhope.moneyfeed.schedule.biz.rpc.payment;

import com.newhope.moneyfeed.pay.api.rest.pay.PaymentScheduleApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 *
 * 支付模块client
 * @author bena.peng
 * @date 2018/12/19 14:12
 */

@FeignClient(value = "${feign.client.moneyfeed-pay.name}")
public interface PaymentScheduleFeignClient extends PaymentScheduleApi {
}
