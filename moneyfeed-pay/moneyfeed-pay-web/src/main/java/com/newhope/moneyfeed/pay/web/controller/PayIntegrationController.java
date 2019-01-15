package com.newhope.moneyfeed.pay.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyPaymentDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyRechargeDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.moneyfeed.pay.api.rest.pay.PayIntegrationAPI;
import com.newhope.pay.biz.service.PayIntegrationService;

/**
 *
 * 支付模块与中台之间的 rest api
 * @author bena.peng
 * @date 2018/12/18 9:54
 */

@RestController
public class PayIntegrationController extends AbstractController implements PayIntegrationAPI {
    private static Logger logger = LoggerFactory.getLogger(PayIntegrationController.class);
    @Autowired
    PayIntegrationService service;

    @Override
    public BaseResponse<DtoResult> notifyOrderPaymentByBankcard(@RequestBody NotifyPaymentDtoReq reqBody) {
        DtoResult dtoResult = service.notifyOrderPaymentByBankcard(reqBody);
        return super.buildJson(dtoResult);
    }

    @Override
    public BaseResponse<DtoResult> notifyCustomerRecharge(@RequestBody NotifyRechargeDtoReq reqBody) {
        DtoResult dtoResult = service.notifyCustomerRecharge(reqBody);
        return super.buildJson(dtoResult);
    }

	@Override
	public BaseResponse<DtoResult> reciveNotifyEbsPay(@RequestBody ReciveNotifyEbsPayDtoReq dtoReq) {
		String s= "aaa";
		DtoResult dtoResult =  service.reciveNotifyEbsPay(dtoReq);
		return buildJson(dtoResult);
	}


}
