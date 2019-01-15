package com.newhope.moneyfeed.integration.api.rest.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.*;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by yuyanlin on 2018/11/20
 */
@Api(value = "MoneyfeedToEbsOrderAPI", description = "商城请求EBS相关接口", protocols = "http")
public interface MoneyfeedToEbsOrderAPI {

    @ApiOperation(value = "商城请求EBS，创建订单", notes = "商城请求EBS，创建订单", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/order", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderCreate(@Valid @RequestBody MoneyfeedToEbsOrderCreateDtoReq moneyfeedToEbsOrderCreateDtoReq);

    @ApiOperation(value = "商城请求EBS，取消订单", notes = "商城请求EBS，取消订单", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/cancel", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderCancel(@Valid @RequestBody MoneyfeedToEbsOrderCancelDtoReq moneyfeedToEbsOrderCancelDtoReq);

    @ApiOperation(value = "商城请求EBS，查询订单信息", notes = "商城请求EBS，查询订单信息", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/query", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<QueryEbsOrderDetailInfoDtoResult> moneyfeedQueryEbsOrder(@RequestBody QueryEbsOrderDetailInfoDtoReq search);

    @ApiOperation(value = "商城请求EBS，更新部分订单信息，比如车牌号、计划拉料日期", notes = "商城请求EBS，更新部分订单信息，比如车牌号、计划拉料日期", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/update/info", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderUpdateInfo(@Valid @RequestBody MoneyfeedToEbsOrderUpdateInfoDtoReq moneyfeedToEbsOrderUpdateInfoDtoReq);

    @ApiOperation(value = "获取信息订单信息，并更新中台EBS订单详细表", notes = "获取信息订单信息，并更新中台EBS订单详细表", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/operation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> updateOrderDetailFromEbs();

    @ApiOperation(value = "重新发送MQ信息", notes = "重新发送MQ信息", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/common/resendmq", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> resendMQ();

    @ApiOperation(value = "商城请求EBS，用余额进行支付", notes = "商城请求EBS，用余额进行支付", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/pay/balance", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderPayInBalanceType(@Valid @RequestBody MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balancePayDtoReq);

    @ApiOperation(value = "商城请求EBS，用银行卡进行支付", notes = "商城请求EBS，用银行卡进行支付", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/pay/bankcard", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderPayInBankCardType(@Valid @RequestBody MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardPayDtoReq);
    
    @ApiOperation(value = "商城请求EBS，订单充值或账户充值", notes = "商城请求EBS，订单充值或账户充值", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/recharge", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderRecharge(@Valid @RequestBody MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq);
    
    @ApiOperation(value = "商城请求EBS，中台先取消订单后创建订单", notes = "商城请求EBS，中台先取消订单后创建订单", protocols = "http")
    @RequestMapping(value = "/moneyfeed/to/ebs/recreate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> moneyfeedToEbsOrderCreateAfterCancel(@Valid @RequestBody MoneyfeedToEbsOrderReCreateDtoReq orderReCreateDtoReq);
}
