package com.newhope.openapi.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.user.FinancialInformationOpenAPI;
import com.newhope.openapi.api.vo.response.user.FinancialBalanceResult;
import com.newhope.openapi.api.vo.response.user.FinancialBillDetailListResult;
import com.newhope.openapi.api.vo.response.user.FinancialBillOverviewResult;
import com.newhope.openapi.api.vo.response.user.FinancialMonthBillOverviewListResult;
import com.newhope.openapi.api.vo.response.user.FinancialPaymentDetailResult;
import com.newhope.openapi.api.vo.response.user.FinancialPaymentFlowListResult;
import com.newhope.openapi.biz.service.user.FinancialInformationService;

@RestController
public class FinancialInformationController extends AbstractController implements FinancialInformationOpenAPI {
    @Autowired
    private FinancialInformationService service;

    @Override
    public BaseResponse<FinancialBalanceResult> balance() {
        return buildJson(service.getBalance());
    }

    @Override
    public BaseResponse<FinancialBillOverviewResult> billOverview(@RequestParam("year") Integer year,
                                                                  @RequestParam("month") Integer month) {
        return buildJson(service.getBillOverview(year, month));
    }

    @Override
    public BaseResponse<FinancialBillDetailListResult> billDetail(@RequestParam("page") Integer page,
                                                                  @RequestParam("pageSize") Integer pageSize,
                                                                  @RequestParam("year") Integer year,
                                                                  @RequestParam("month") Integer month) {

        return buildJson(service.getBillDetail(page, pageSize, year, month));
    }

    @Override
    public BaseResponse<FinancialPaymentFlowListResult> paymentFlow(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month,
            @RequestParam(value = "paymentType", required = false) String paymentType,
            @RequestParam(value = "bizType", required = false) String bizType) {
        return buildJson(service.getPaymentFlow(page, pageSize, year, month, bizType));
    }

    @Override
    public BaseResponse<FinancialPaymentDetailResult> paymentFlowDetail(@RequestParam String orderCode) {
        return null;
    }

	@Override
	public BaseResponse<FinancialMonthBillOverviewListResult> monthBillOverview() {
		return buildJson(service.getMonthBillOverview());
	}

}
