package com.newhope.moneyfeed.integration.api.rest.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * EBS请求（反馈结果）商城相关接口
 *
 * 方式有可能是integration主动请求EBS，然后通知商城
 *
 * Created by yuyanlin on 2018/11/21
 */
@Api(value = "EbsToMoneyfeedOrderAPI", description = "EBS请求（反馈结果）商城相关接口", protocols = "http")
public interface EbsToMoneyfeedOrderAPI {

    @ApiOperation(value = "批量查询EBS订单，创建结果，创建结果反馈给商城", notes = "批量查询EBS订单，创建结果，创建结果反馈给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/create/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedOrdersCreateResult();

    @ApiOperation(value = "重新把上次未发送成功的订单创建失败的结果，发送给商城", notes = "重新把上次未发送成功的订单创建失败的结果，发送给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/retry/orders/fail/create/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedRetrySendFailCreateResult();

    @ApiOperation(value = "批量查询EBS订单，支付结果，支付结果反馈给商城", notes = "批量查询EBS订单，支付结果，支付结果反馈给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/pay/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedOrdersPayResult();

    @ApiOperation(value = "批量查询EBS订单，取消结果，取消结果反馈给商城", notes = "批量查询EBS订单，取消结果，取消结果反馈给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/cancel/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedOrdersCancelResult();

    @ApiOperation(value = "批量查询EBS订单，获取锁库失败的结果，反馈给商城", notes = "批量查询EBS订单，获取锁库失败的结果，反馈给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/lock/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedOrderLockWarehouseResult();
    
    @ApiOperation(value = "重新发送上次取消订单信息而未成功发送到商城结果", notes = "重新发送上次取消订单信息而未成功发送到商城结果，发送给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/retry/orders/fail/cancel/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedRetrySendFailCancelResult();

    @ApiOperation(value = "批量查询EBS订单，更新结果，并且把更新结果反馈给商城", notes = "批量查询EBS订单，更新结果，并且把更新结果反馈给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/update/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedSendUpdateInfoResult();
    
    @ApiOperation(value = "批量查询充值记录，反馈给商城", notes = "批量查询充值记录，反馈给商城", protocols = "http")
    @RequestMapping(value = "/ebs/to/moneyfeed/orders/recharge/result", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BaseResponse<DtoResult> ebsToMoneyfeedOrdersRechargeResult();

}
