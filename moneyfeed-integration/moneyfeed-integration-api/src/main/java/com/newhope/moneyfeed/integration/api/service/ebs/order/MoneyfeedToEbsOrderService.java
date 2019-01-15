package com.newhope.moneyfeed.integration.api.service.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.*;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;

/**
 * 商城调用EBS，订单相关服务
 *
 * Created by yuyanlin on 2018/11/20
 */
public interface MoneyfeedToEbsOrderService {

    /**
     * 商城请求调用EBS，创建订单
     *
     * @param moneyfeedToEbsOrderCreateDtoReq
     * @return
     */
    DtoResult moneyfeedToEbsOrderCreate(MoneyfeedToEbsOrderCreateDtoReq moneyfeedToEbsOrderCreateDtoReq) throws Exception;

    /**
     * 商城请求调用EBS，支付订单
     *
     * @param moneyfeedToEbsOrderPayDtoReq
     * @return
     */
    DtoResult moneyfeedToEbsOrderPay(MoneyfeedToEbsOrderPayDtoReq moneyfeedToEbsOrderPayDtoReq);

    /**
     * 商城请求调用EBS，取消订单
     *
     * @param moneyfeedToEbsOrderCancelDtoReq
     * @return
     */
    DtoResult moneyfeedToEbsOrderCancel(MoneyfeedToEbsOrderCancelDtoReq moneyfeedToEbsOrderCancelDtoReq);

    /**
     * 查询
     * @param search
     * @return
     */
    QueryEbsOrderDetailInfoDtoResult queryEbsOrderDetailInfo(QueryEbsOrderDetailInfoDtoReq search);

    /**
     * 商城请求调用EBS，更新订单部分信息
     * @param moneyfeedToEbsOrderUpdateInfoDtoReq
     * @return
     */
    DtoResult moneyfeedToEbsOrderUpdateInfo(MoneyfeedToEbsOrderUpdateInfoDtoReq moneyfeedToEbsOrderUpdateInfoDtoReq);

    /**
     * 查询EBS订单信息并更新EBS订单信息
     */
    void  queryEbsOrderDetailInfoJob();

    /**
     * 重新发送MQ消息
     */
    void resendMQ();

    /**
     * 商城请求调用EBS，进行余额支付
     *
     * @param balancePayDtoReq
     * @return
     */
    DtoResult moneyfeedToEbsOrderPayInBalanceType(MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balancePayDtoReq) throws Exception;


    /**
     * 商城请求调用EBS，进行银行卡支付
     *
     * @param bankCardPayDtoReq
     * @return
     */
    DtoResult moneyfeedToEbsOrderPayInBankCardType(MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardPayDtoReq) throws Exception;
    
    /**
     * 商城请求调用EBS，订单充值或者账户充值
     * @param orderRechargeDtoReq
     * @return
     */
	DtoResult moneyfeedToEbsOrderRecharge(MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq);
	
	/**
	 * 先取消订单后创建
	 * @param orderReCreateDtoReq
	 * @return
	 */
	DtoResult moneyfeedToEbsOrderCreateAfterCancel(MoneyfeedToEbsOrderReCreateDtoReq orderReCreateDtoReq) throws Exception;
}
