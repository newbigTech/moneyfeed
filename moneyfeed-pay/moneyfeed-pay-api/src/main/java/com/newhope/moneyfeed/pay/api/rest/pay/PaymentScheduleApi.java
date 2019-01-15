package com.newhope.moneyfeed.pay.api.rest.pay;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCallbackDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 调度任务
 * @author bena.peng
 * @date 2018/12/19 14:06
 */

@Api(
        value = "支付模块调度任务API",
        description = "支付模块调度任务API",
        protocols = "http"
)
public interface PaymentScheduleApi {

    @ApiOperation(
            value = "与第三方支付对账",
            notes = "与第三方支付对账",
            protocols = "http"
    )
    @RequestMapping(
            value = "/pay/schedule/bill",
            method = RequestMethod.POST,
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    BaseResponse<DtoResult> syncThirdPaymentBill();
    
    @ApiOperation(value="定时关闭超时未处理支付订单", notes="定时关闭超时未处理支付订单", protocols="http")
	@RequestMapping(value = "/pay/schedule/closeTimeoutPayOrder", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> closeTimeoutPayOrder();
}
