package com.newhope.openapi.api.rest.customer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.customer.FinancialBillQueryReq;
import com.newhope.openapi.api.vo.request.customer.FinancialPaymentFlowQueryReq;
import com.newhope.openapi.api.vo.request.customer.MonthBillOverviewReq;
import com.newhope.openapi.api.vo.response.customer.FinancialBillDetailListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialBillOverviewResult;
import com.newhope.openapi.api.vo.response.customer.FinancialMonthBillOverviewListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialPaymentFlowListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "账户管理-财务信息", tags = "FinancialInformationOpenAPI", protocols = "http")
@Validated
public interface FinancialInformationOpenAPI {


    @ApiOperation(value = "账户管理-财务信息-我的账单-概览", notes = "账户管理-财务信息-我的账单-概览")
    @RequestMapping(value = "/customer/finance/bill/overview", method = RequestMethod.GET)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = true, dataType = "String"),
    		@ApiImplicitParam(name = "customerNum", value = "客户编号", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "year", value = "年", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "月", required = true, paramType = "query", dataType = "int")
    })
    BaseResponse<FinancialBillOverviewResult> billOverview(@Valid FinancialBillQueryReq financialBillQueryReq, 
    		HttpServletRequest request);
    
    @ApiOperation(value = "账户管理-财务信息-我的账单-详细信息", notes = "账户管理-财务信息-我的账单-详细信息")
    @RequestMapping(value = "/customer/finance/bill/detail", method = RequestMethod.GET)
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = true, dataType = "String"),
			@ApiImplicitParam(name = "customerNum", value = "客户编号", paramType = "query", required = true, dataType = "String"),
	        @ApiImplicitParam(name = "year", value = "年", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "月", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前?页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int")
    })
    BaseResponse<FinancialBillDetailListResult> billDetail(@Valid FinancialBillQueryReq financialBillQueryReq,
    		HttpServletRequest request);
    
    @ApiOperation(value = "账户管理-月度对账单", notes = "账户管理-月度对账单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "ebsCustomerNum", value = "客户编码", paramType = "query", required = true, dataType = "String"),
        @ApiImplicitParam(name = "year", value = "年", paramType = "query", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "month", value = "月", paramType = "query", required = false, dataType = "Integer")
	})
    @RequestMapping(value = "/customer/bill/overview/month", method = RequestMethod.GET)
    BaseResponse<FinancialMonthBillOverviewListResult> monthBillOverview(@Valid MonthBillOverviewReq mzonthBillOverviewReq,
    		HttpServletRequest request);

    @ApiOperation(value = "导出账单详情", notes = "导出账单详情", protocols = "http")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "customerNum", value = "客户编号", paramType = "query", required = true, dataType = "String"),
        @ApiImplicitParam(name = "year", value = "年", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "month", value = "月", required = true, paramType = "query", dataType = "int")
    })
	@RequestMapping(value = "/customer/finance/bill/export", method = RequestMethod.GET, produces = {"application/json"})
	public void exportBillDetail(@Valid FinancialBillQueryReq financialBillQueryReq, HttpServletResponse response,
			HttpServletRequest request);
	

    @ApiOperation(value = "账户管理-财务信息-线上支付流水", notes = "账户管理-财务信息-线上支付流水")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "customerName", value = "客户名称", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "startDate", value = "开始日期", paramType = "query", required = true, dataType = "String"),
        @ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orderNo", value = "单据号", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "payOrderNo", value = "支付单号", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "bizType", value = "支付方式", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "当前?页", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int")
})
    @RequestMapping(value = "/customer/finance/payment/flow", method = RequestMethod.GET)
    BaseResponse<FinancialPaymentFlowListResult> paymentFlow(FinancialPaymentFlowQueryReq queryReq);

    @ApiOperation(value = "导出线上支付流水", notes = "导出线上支付流水", protocols = "http")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "customerName", value = "客户名称", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "startDate", value = "开始日期", paramType = "query", required = true, dataType = "String"),
        @ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orderNo", value = "单据号", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "payOrderNo", value = "支付单号", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "bizType", value = "支付方式", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "当前?页", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int")
    })
	@RequestMapping(value = "/customer/finance/payment/flow/export", method = RequestMethod.GET, produces = {"application/json"})
	public void exportPaymentFlow(FinancialPaymentFlowQueryReq queryReq, HttpServletResponse response);
	
}
