package com.newhope.openapi.api.rest.user;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.response.user.FinancialBalanceResult;
import com.newhope.openapi.api.vo.response.user.FinancialBillDetailListResult;
import com.newhope.openapi.api.vo.response.user.FinancialBillOverviewResult;
import com.newhope.openapi.api.vo.response.user.FinancialMonthBillOverviewListResult;
import com.newhope.openapi.api.vo.response.user.FinancialPaymentDetailResult;
import com.newhope.openapi.api.vo.response.user.FinancialPaymentFlowListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "账户管理-财务信息", tags = "FinancialInformationOpenAPI", protocols = "http")
@RequestMapping("/user/finance")
public interface FinancialInformationOpenAPI {

    @ApiOperation(value = "账户管理-财务信息-我的余额", notes = "账户管理-财务信息-我的余额")
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    BaseResponse<FinancialBalanceResult> balance();

    @ApiOperation(value = "账户管理-财务信息-我的账单-概览", notes = "账户管理-财务信息-我的账单-概览")
    @RequestMapping(value = "/bill/overview", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "月", required = true, paramType = "query", dataType = "int")
    })
    BaseResponse<FinancialBillOverviewResult> billOverview(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    );

    
    
    @ApiOperation(value = "账户管理-财务信息-我的账单-详细信息", notes = "账户管理-财务信息-我的账单-详细信息")
    @RequestMapping(value = "/bill/detail", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前?页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "year", value = "年", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "月", required = true, paramType = "query", dataType = "int")
    })
    BaseResponse<FinancialBillDetailListResult> billDetail(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    );

    @ApiOperation(value = "账户管理-财务信息-线上支付流水", notes = "账户管理-财务信息-线上支付流水")
    @RequestMapping(value = "/payment/flow", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前?页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "year", value = "年", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "月", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bizType", value = "支付类型",
                    allowableValues = "online,offline", paramType = "query", dataType = "String")
    })
    BaseResponse<FinancialPaymentFlowListResult> paymentFlow(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month,
            @RequestParam(value = "paymentType", required = false) String paymentType,
            @RequestParam(value = "bizType", required = false) String bizType
    );

    @ApiOperation(value = "账户管理-财务信息-线上支付流水详情", notes = "账户管理-财务信息-线上支付流水详情")
    @RequestMapping(value = "/payment/flow/detail", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单号", required = true, paramType = "query", dataType = "String")
    })
    BaseResponse<FinancialPaymentDetailResult> paymentFlowDetail(@RequestParam String orderCode);


    @ApiOperation(value = "账户管理-月度对账单", notes = "账户管理-月度对账单")
    @RequestMapping(value = "/bill/overview/month", method = RequestMethod.GET)
    BaseResponse<FinancialMonthBillOverviewListResult> monthBillOverview(
    );
}
