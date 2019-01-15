package com.newhope.moneyfeed.pay.web.controller;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.rest.pay.PaymentScheduleApi;
import com.newhope.pay.biz.service.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 调度任务 rest api
 * @author bena.peng
 * @date 2018/12/19 14:22
 */

@RestController
public class PaymentScheduleController extends AbstractController implements PaymentScheduleApi {

    @Autowired
    PaymentScheduleService service;

    @Override
    public BaseResponse<DtoResult> syncThirdPaymentBill() {
        DtoResult dtoResult = service.syncThirdPaymentBill();
        return super.buildJson(dtoResult);
    }

	@Override
	public BaseResponse<DtoResult> closeTimeoutPayOrder() {
		DtoResult dtoResult = service.closeTimeoutPayOrder();
		return buildJson(dtoResult);
	}


}
