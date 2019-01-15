package com.newhope.pay.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

/**
 * 支付模块调度任务 service
 * @author bena.peng
 * @date 2018/12/19 14:25
 */


public interface PaymentScheduleService {

    /**
     * 与第三方支付系统进行对账
     * @return
     */
    DtoResult syncThirdPaymentBill();
    
    DtoResult closeTimeoutPayOrder();
}
