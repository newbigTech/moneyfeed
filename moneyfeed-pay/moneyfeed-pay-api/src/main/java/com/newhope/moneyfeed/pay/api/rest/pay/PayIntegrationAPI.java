package com.newhope.moneyfeed.pay.api.rest.pay;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyPaymentDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyRechargeDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 支付模块与中台之间的 rest api
 *
 * @author bena.peng
 * @date 2018/12/18 9:57
 */

@Api(value = "支付模块与中台之间的API", description = "支付模块与中台之间的API", protocols = "http")
public interface PayIntegrationAPI {

	@ApiOperation(value = "通知中台订单使用银行卡支付", notes = "通知中台订单使用银行卡支付", protocols = "http")
	@RequestMapping(value = "/pay/integration/bankcard", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	BaseResponse<DtoResult> notifyOrderPaymentByBankcard(@RequestBody NotifyPaymentDtoReq reqBody);

	@ApiOperation(value = "通知中台用户进行充值", notes = "通知中台用户进行充值", protocols = "http")
	@RequestMapping(value = "/pay/integration/recharge", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	BaseResponse<DtoResult> notifyCustomerRecharge(@RequestBody NotifyRechargeDtoReq reqBody);

	@ApiOperation(value = "接受EBS返回支付，充值信息", notes = "接受EBS返回支付，充值信息", protocols = "http")
	@RequestMapping(value = "/pay/integration/notify", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	BaseResponse<DtoResult> reciveNotifyEbsPay(@RequestBody ReciveNotifyEbsPayDtoReq dtoReq);
}
