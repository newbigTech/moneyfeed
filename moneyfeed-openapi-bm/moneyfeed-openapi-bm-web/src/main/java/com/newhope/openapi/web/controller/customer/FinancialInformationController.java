package com.newhope.openapi.web.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.openapi.api.rest.customer.FinancialInformationOpenAPI;
import com.newhope.openapi.api.vo.request.customer.FinancialBillQueryReq;
import com.newhope.openapi.api.vo.request.customer.FinancialPaymentFlowQueryReq;
import com.newhope.openapi.api.vo.request.customer.MonthBillOverviewReq;
import com.newhope.openapi.api.vo.response.customer.FinancialBillDetailListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialBillOverviewResult;
import com.newhope.openapi.api.vo.response.customer.FinancialMonthBillOverviewListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialPaymentFlowListResult;
import com.newhope.openapi.biz.service.customer.FinancialInformationService;

@RestController
public class FinancialInformationController extends AbstractController implements FinancialInformationOpenAPI{
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	private FinancialInformationService financialInformationService;

	@Override
	public BaseResponse<FinancialBillOverviewResult> billOverview(FinancialBillQueryReq financialBillQueryReq,
			HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}
		return buildJson(financialInformationService.getBillOverview(financialBillQueryReq));
	}

	@Override
	public BaseResponse<FinancialBillDetailListResult> billDetail(FinancialBillQueryReq financialBillQueryReq,
			HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}
		return buildJson(financialInformationService.getBillDetail(financialBillQueryReq));
	}
	
	@Override
	public BaseResponse<FinancialMonthBillOverviewListResult> monthBillOverview(
			MonthBillOverviewReq mzonthBillOverviewReq, HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}
		return buildJson(financialInformationService.getMonthBillOverview(mzonthBillOverviewReq));
	}

	@Override
	public void exportBillDetail(FinancialBillQueryReq financialBillQueryReq, HttpServletResponse response,
			HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}
		financialInformationService.exportBillDetail(financialBillQueryReq, response);
	}

	@Override
	public BaseResponse<FinancialPaymentFlowListResult> paymentFlow(FinancialPaymentFlowQueryReq queryReq) {
		return buildJson(financialInformationService.paymentFlow(queryReq));
	}

	@Override
	public void exportPaymentFlow(FinancialPaymentFlowQueryReq queryReq, HttpServletResponse response) {
		financialInformationService.exportPaymentFlow(queryReq, response);
	}


}
