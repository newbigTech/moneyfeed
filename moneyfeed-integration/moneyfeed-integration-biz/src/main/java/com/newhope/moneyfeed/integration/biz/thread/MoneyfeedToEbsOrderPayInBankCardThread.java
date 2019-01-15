package com.newhope.moneyfeed.integration.biz.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBankCardTypeDtoReq;
import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderPaymentModelBuilder;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderPayOrChargeReq;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.TransferWithEbsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderLogServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderPaymentServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderServiceImpl;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by yuyanlin on 2018/12/17
 */
public class MoneyfeedToEbsOrderPayInBankCardThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsOrderPayInBankCardThread.class);

    private MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardTypeDtoReq;

    public MoneyfeedToEbsOrderPayInBankCardThread(MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardTypeDtoReq) {
        this.bankCardTypeDtoReq = bankCardTypeDtoReq;
    }

    @Override
    public void run() {
        logger.info("开始向EBS发送订单银行卡支付, EBS订单号：" + bankCardTypeDtoReq.getEbsOrderNo());

        final EbsOrderServiceImpl ebsOrderService = AppContext.getSpringContext().getBean(EbsOrderServiceImpl.class);
        final EbsOrderPaymentServiceImpl ebsOrderPaymentService = AppContext.getSpringContext().getBean(EbsOrderPaymentServiceImpl.class);
        final EbsOrderLogServiceImpl ebsOrderLogService = AppContext.getSpringContext().getBean(EbsOrderLogServiceImpl.class);
        final TransferWithEbsServiceImpl transferWithEbsService = AppContext.getSpringContext().getBean(TransferWithEbsServiceImpl.class);
        final CommonService commonService = AppContext.getSpringContext().getBean(CommonService.class);

        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(bankCardTypeDtoReq.getEbsOrderNo());

        // 记录日志
        commonService.saveFulllog(bankCardTypeDtoReq.getTotalPayAmount(),
                bankCardTypeDtoReq.getTotalPayAmount(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                null,
                bankCardTypeDtoReq,
                bankCardTypeDtoReq.getEbsOrderNo(),
                new Date(),
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                null,
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_RECEIVE_PAY_REQ_OF_SHOP,
                "收到商城银行卡支付订单的请求，商城订单号" + ebsOrderWithDetail.getSaleOrderId(),
                UserOperSourceType.MEDIUM_CENTER);

        // 发送EBS，通知EBS余额支付
        MoneyfeedToEbsOperationStatusEnum result = null;

        Date payDate;

        try {
            payDate = DateUtils.parseDate(bankCardTypeDtoReq.getPayDateString(), DateUtils.date_sdf.toPattern());
        } catch (ParseException e) {
            payDate = new Date();
        }

        try {
            EbsOrderPayOrChargeReq req = converseDtoReqToEbsReq(bankCardTypeDtoReq, ebsOrderWithDetail);

            transferWithEbsService.sendEbsOrderPayOrChargeRequest(req);

            // 记录日志
            ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                    ebsOrderWithDetail.getSaleOrderId(),
                    EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                    new Date(),
                    JSON.toJSONString(bankCardTypeDtoReq),
                    StringUtils.EMPTY,
                    0,
                    (byte) 1);

            result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
        } catch (Exception e) {
            CommonService.formatExceptionMsg(MoneyfeedToEbsOrderPayInBankCardThread.class, e);

            logger.error("请求余额支付订单失败，商城订单号：" + ebsOrderWithDetail.getSaleOrderId() + " , 原因: " + e.getMessage());

            if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
                // 记录错误日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(bankCardTypeDtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 调用订单中心失败时，在后续的定时任务中，根据日志类型进行操作，发送MQ消息
                commonService.sendToOrderPayOrChargeMqResult(bankCardTypeDtoReq.getEbsOrderNo(),
                        PayTypeEnums.CARD_PAY.name(),
                        false,
                        "网络连接EBS系统失败, 银行卡支付订单失败",
                        bankCardTypeDtoReq.getOrgId(),
                        bankCardTypeDtoReq.getMoneyfeedPayNo(),
                        MQMsgKindEnum.pay);

                result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
            } else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
                // 请求EBS返回结果超时，在定时任务中请求创建结果即可

                // 记录成功的日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(bankCardTypeDtoReq),
                        e.getMessage(),
                        0,
                        (byte) 1);

                result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
            } else {
                // 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(bankCardTypeDtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 通知商城，订单因为网络问题，不能创建
                commonService.sendToOrderPayOrChargeMqResult(bankCardTypeDtoReq.getEbsOrderNo(),
                        PayTypeEnums.CARD_PAY.name(),
                        false,
                        "EBS系统业务异常, 余额支付订单失败，" + e.getMessage(),
                        bankCardTypeDtoReq.getOrgId(),
                        bankCardTypeDtoReq.getMoneyfeedPayNo(),
                        MQMsgKindEnum.pay);

                result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
            }

        }

        // 记录日志
        commonService.saveFulllog(bankCardTypeDtoReq.getTotalPayAmount(),
                bankCardTypeDtoReq.getTotalPayAmount(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                result.name(),
                bankCardTypeDtoReq,
                bankCardTypeDtoReq,
                bankCardTypeDtoReq.getEbsOrderNo(),
                new Date(),
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                null,
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_SEND_PAY_INFO_TO_EBS,
                "请求EBS银行卡支付订单，商城订单号" + ebsOrderWithDetail.getSaleOrderId(),
                UserOperSourceType.MEDIUM_CENTER);

        // 根据请求EBS的结果result，进行业务处理

        // ebs_order_payment
        if (MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(result.name())) {
            EbsOrderPaymentModelBuilder ebsOrderPaymentModelBuilder = EbsOrderPaymentModelBuilder.anEbsOrderPaymentModel();
            ebsOrderPaymentModelBuilder.currency(bankCardTypeDtoReq.getCurrency())
                    .orderId(ebsOrderWithDetail.getId())
                    .payDate(new Date())
                    .payType(PayTypeEnums.CARD_PAY.name())
                    .accNo(bankCardTypeDtoReq.getAccNo())
                    .totalPayAmount(bankCardTypeDtoReq.getTotalPayAmount())
                    .ebsRealAccount(bankCardTypeDtoReq.getEbsRealAccount());

            ebsOrderPaymentService.save(Lists.newArrayList(ebsOrderPaymentModelBuilder.build()));
        }

        // ebs_order
        EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
        ebsOrderBuilder.payType(PayTypeEnums.CARD_PAY.name())
                .totalAmount(bankCardTypeDtoReq.getTotalPayAmount())
                .cardPayAmount(bankCardTypeDtoReq.getTotalPayAmount())
                .orderPayStatus(result.name()).bankPayNo(bankCardTypeDtoReq.getAccNo());

        ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());
    }

    private EbsOrderPayOrChargeReq converseDtoReqToEbsReq(MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardTypeDtoReq, EbsOrderExModel ebsOrder) {
        EbsOrderPayOrChargeReq req = new EbsOrderPayOrChargeReq();

        if (null == bankCardTypeDtoReq) {
            return req;
        }

        req.setMoneyfeedOrderId(ebsOrder.getSaleOrderId());
        req.setEbsOrderNo(bankCardTypeDtoReq.getEbsOrderNo());
        req.setCurrency(bankCardTypeDtoReq.getCurrency());
        req.setCustomerNo(ebsOrder.getEbsCustomerNo());
        req.setPayOrChargeType(PayTypeEnums.CARD_PAY.name());
        req.setPayDateString(bankCardTypeDtoReq.getPayDateString());
        req.setTotalBankAmount(bankCardTypeDtoReq.getTotalPayAmount());
        req.setMoneyfeedPayNo(bankCardTypeDtoReq.getMoneyfeedPayNo());
        req.setAccNo(bankCardTypeDtoReq.getAccNo());
        req.setOrgId(bankCardTypeDtoReq.getOrgId());
        req.setCollectionMethod(bankCardTypeDtoReq.getEbsRealAccount());

        return req;
    }
}
