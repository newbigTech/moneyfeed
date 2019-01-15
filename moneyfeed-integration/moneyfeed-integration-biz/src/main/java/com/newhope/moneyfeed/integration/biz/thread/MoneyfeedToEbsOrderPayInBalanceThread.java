package com.newhope.moneyfeed.integration.biz.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBalanceTypeDtoReq;
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
public class MoneyfeedToEbsOrderPayInBalanceThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsOrderPayInBalanceThread.class);

    private MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balanceTypeDtoReq;

    public MoneyfeedToEbsOrderPayInBalanceThread(MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balanceTypeDtoReq) {
        this.balanceTypeDtoReq = balanceTypeDtoReq;
    }

    @Override
    public void run() {
        logger.info("开始向EBS发送订单余额支付, EBS订单号：" + balanceTypeDtoReq.getEbsOrderNo());

        final EbsOrderServiceImpl ebsOrderService = AppContext.getSpringContext().getBean(EbsOrderServiceImpl.class);
        final EbsOrderPaymentServiceImpl ebsOrderPaymentService = AppContext.getSpringContext().getBean(EbsOrderPaymentServiceImpl.class);
        final EbsOrderLogServiceImpl ebsOrderLogService = AppContext.getSpringContext().getBean(EbsOrderLogServiceImpl.class);
        final TransferWithEbsServiceImpl transferWithEbsService = AppContext.getSpringContext().getBean(TransferWithEbsServiceImpl.class);
        final CommonService commonService = AppContext.getSpringContext().getBean(CommonService.class);

        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(balanceTypeDtoReq.getEbsOrderNo());

        // 记录日志
        commonService.saveFulllog(balanceTypeDtoReq.getTotalPayAmount(),
                balanceTypeDtoReq.getTotalPayAmount(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                null,
                balanceTypeDtoReq,
                balanceTypeDtoReq.getEbsOrderNo(),
                new Date(),
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                null,
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_RECEIVE_PAY_REQ_OF_SHOP,
                "收到商城余额支付订单的请求，商城订单号" + ebsOrderWithDetail.getSaleOrderId(),
                UserOperSourceType.MEDIUM_CENTER);

        // 发送EBS，通知EBS余额支付
        MoneyfeedToEbsOperationStatusEnum result = null;

        Date payDate;

        try {
            payDate = DateUtils.parseDate(balanceTypeDtoReq.getPayDateString(), DateUtils.date_sdf.toPattern());
        } catch (ParseException e) {
            payDate = new Date();
        }

        try {
            EbsOrderPayOrChargeReq req = converseDtoReqToEbsReq(balanceTypeDtoReq, ebsOrderWithDetail);
            
            transferWithEbsService.sendEbsOrderPayOrChargeRequest(req);

            // 记录日志
            ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                    ebsOrderWithDetail.getSaleOrderId(),
                    EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                    payDate,
                    JSON.toJSONString(balanceTypeDtoReq),
                    StringUtils.EMPTY,
                    0,
                    (byte) 1);

            result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
        } catch (Exception e) {
            CommonService.formatExceptionMsg(MoneyfeedToEbsOrderPayInBalanceThread.class, e);

            logger.error("请求余额支付订单失败，商城订单号：" + ebsOrderWithDetail.getSaleOrderId() + " , 原因: " + e.getMessage());

            if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
                // 记录错误日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                        payDate,
                        JSON.toJSONString(balanceTypeDtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 通知商城，订单因为网络问题，不能创建
                commonService.sendToOrderPayOrChargeMqResult(balanceTypeDtoReq.getEbsOrderNo(),
                        PayTypeEnums.BAL_PAY.name(),
                        false,
                        "网络连接EBS系统失败, 余额支付订单失败",
                        balanceTypeDtoReq.getOrgId(),
                        balanceTypeDtoReq.getMoneyfeedPayNo(),
                        MQMsgKindEnum.pay);

                result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
            } else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
                // 请求EBS返回结果超时，在定时任务中请求创建结果即可

                // 记录成功的日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                        payDate,
                        JSON.toJSONString(balanceTypeDtoReq),
                        e.getMessage(),
                        0,
                        (byte) 1);

                result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
            } else {
                // 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_PAY_REQUEST.name(),
                        payDate,
                        JSON.toJSONString(balanceTypeDtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 通知商城，订单因为网络问题，不能创建
                commonService.sendToOrderPayOrChargeMqResult(balanceTypeDtoReq.getEbsOrderNo(),
                        PayTypeEnums.BAL_PAY.name(),
                        false,
                        "EBS系统业务异常, 余额支付订单失败，" + e.getMessage(),
                        balanceTypeDtoReq.getOrgId(),
                        balanceTypeDtoReq.getMoneyfeedPayNo(),
                        MQMsgKindEnum.pay);


                result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
            }

        }

        // 记录日志
        commonService.saveFulllog(balanceTypeDtoReq.getTotalPayAmount(),
                balanceTypeDtoReq.getTotalPayAmount(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                result.name(),
                balanceTypeDtoReq,
                balanceTypeDtoReq,
                balanceTypeDtoReq.getEbsOrderNo(),
                new Date(),
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                null,
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_SEND_PAY_INFO_TO_EBS,
                "请求EBS余额支付订单，商城订单号" + ebsOrderWithDetail.getSaleOrderId() + ", " + result.name(),
                UserOperSourceType.MEDIUM_CENTER);

        // 根据请求EBS的结果result，进行业务处理，如果请求EBS成功，则记录一条支付记录，如果失败，就不记录支付记录
        if (MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(result.name())) {
            EbsOrderPaymentModelBuilder ebsOrderPaymentModelBuilder = EbsOrderPaymentModelBuilder.anEbsOrderPaymentModel();

            ebsOrderPaymentModelBuilder.balanceAmount(balanceTypeDtoReq.getTotalBalanceAmount()).currency(balanceTypeDtoReq.getCurrency())
                    .discountAmount(balanceTypeDtoReq.getDiscountAmount())
                    .orderId(ebsOrderWithDetail.getId())
                    .payDate(payDate)
                    .payType(PayTypeEnums.BAL_PAY.name())
                    .totalPayAmount(balanceTypeDtoReq.getTotalPayAmount());


            ebsOrderPaymentService.save(Lists.newArrayList(ebsOrderPaymentModelBuilder.build()));
        }

        EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
        ebsOrderBuilder.payType(PayTypeEnums.BAL_PAY.name())
                .ebsPayAmount(balanceTypeDtoReq.getTotalBalanceAmount())
                .orderPayStatus(result.name())
                .totalAmount(balanceTypeDtoReq.getTotalPayAmount());

        ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());
    }

    private EbsOrderPayOrChargeReq converseDtoReqToEbsReq(MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balanceTypeDtoReq, EbsOrderExModel ebsOrder) {
        EbsOrderPayOrChargeReq req = new EbsOrderPayOrChargeReq();

        if (null == balanceTypeDtoReq) {
            return req;
        }

        req.setMoneyfeedOrderId(ebsOrder.getSaleOrderId());
        req.setEbsOrderNo(balanceTypeDtoReq.getEbsOrderNo());
        req.setCurrency(balanceTypeDtoReq.getCurrency());
        req.setCustomerNo(balanceTypeDtoReq.getCusNo());
        req.setMoneyfeedPayNo(balanceTypeDtoReq.getMoneyfeedPayNo());
        req.setPayOrChargeType(PayTypeEnums.BAL_PAY.name());
        req.setPayDateString(balanceTypeDtoReq.getPayDateString());
        req.setTotalBalanceAmount(balanceTypeDtoReq.getTotalBalanceAmount());
        req.setDiscountAmount(balanceTypeDtoReq.getDiscountAmount());
        req.setOrgId(balanceTypeDtoReq.getOrgId());

        return req;
    }

}
