package com.newhope.pay.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyPaymentDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyRechargeDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;

/**
 * 通知中台 service
 * @author bena.peng
 * @date 2018/12/18 10:43
 */


public interface PayIntegrationService {

    DtoResult notifyOrderPaymentByBankcard(NotifyPaymentDtoReq dtoReq);

    DtoResult notifyCustomerRecharge(NotifyRechargeDtoReq dtoReq);
    
    /**  
    * @Title: reciveNotifyEbsPay  
    * @Description: 接收EBS返回的处理订单充值和支付结果  
    */
    DtoResult reciveNotifyEbsPay(ReciveNotifyEbsPayDtoReq dtoReq);
}
