package com.newhope.moneyfeed.integration.biz.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleItemsModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyFeedToEbsOrderCreateProductDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCreateDtoReq;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderFromSaleItemsModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderFromSaleModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderModelBuilder;
import com.newhope.moneyfeed.integration.api.service.ebs.baseData.EbsCompanyService;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderLogService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderCreateReq;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.MoneyFeedOrderCenterService;
import com.newhope.moneyfeed.integration.biz.service.common.TransferWithEbsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.baseData.EbsCompanyServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderDetailServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderFromSaleItemsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderFromSaleServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderServiceImpl;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

/**
 * Created by yuyanlin on 2018/11/27
 */
public class MoneyfeedToEbsCreateOrderThread implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsCreateOrderThread.class);

    private MoneyfeedToEbsOrderCreateDtoReq dtoReq;

    public MoneyfeedToEbsCreateOrderThread(MoneyfeedToEbsOrderCreateDtoReq dtoReq) {
        this.dtoReq = dtoReq;
    }

    @Override
    public void run() {
        logger.info("开始向EBS发送订单创建请求, 商城订单号：" + dtoReq.getMoneyFeedOrderId());

        final EbsOrderServiceImpl ebsOrderService = AppContext.getSpringContext().getBean(EbsOrderServiceImpl.class);
        final EbsOrderDetailServiceImpl ebsOrderDetailService = AppContext.getSpringContext().getBean(EbsOrderDetailServiceImpl.class);
        final EbsOrderFromSaleServiceImpl ebsOrderFromSaleService = AppContext.getSpringContext().getBean(EbsOrderFromSaleServiceImpl.class);
        final EbsOrderFromSaleItemsServiceImpl ebsOrderFromSaleItemsService = AppContext.getSpringContext().getBean(EbsOrderFromSaleItemsServiceImpl.class);
        final CommonService commonService = AppContext.getSpringContext().getBean(CommonService.class);
        final TransferWithEbsServiceImpl transferWithEbsService = AppContext.getSpringContext().getBean(TransferWithEbsServiceImpl.class);
        final EbsOrderLogService ebsOrderLogService = AppContext.getSpringContext().getBean(EbsOrderLogService.class);
        final MoneyFeedOrderCenterService moneyFeedOrderCenterService = AppContext.getSpringContext().getBean(MoneyFeedOrderCenterService.class);

        // 记录日志
        commonService.saveFulllog(null,
                null,
                null,
                null,
                null,
                dtoReq,
                null,
                new Date(),
                Long.valueOf(dtoReq.getMoneyFeedOrderId()),
                null,
                Long.valueOf(dtoReq.getMoneyFeedOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_CALL_EBS_CREATE_ORDER,
                "收到商城创建订单的请求，商城订单号" + dtoReq.getMoneyFeedOrderId(),
                UserOperSourceType.MEDIUM_CENTER);

        // 发送EBS，通知EBS创建订单
        List<EbsOrderCreateReq> reqList = converseDtoToRequestForCreate(dtoReq);

        String logMessageString = StringUtils.EMPTY;

        MoneyfeedToEbsOperationStatusEnum result = null;
        try {
            transferWithEbsService.sendEbsOrderCreateRequest(reqList);

            // 记录日志
            ebsOrderLogService.saveEbsOrderLogWithNoRollback(null,
                    dtoReq.getMoneyFeedOrderId(),
                    EbsOrderLogTypeEnum.TO_EBS_CREATE_REQUEST.name(),
                    new Date(),
                    JSON.toJSONString(dtoReq),
                    StringUtils.EMPTY,
                    0,
                    (byte) 1);

            result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
        } catch (Exception e) {
            logMessageString = e.getMessage();

            logger.error("请求创建订单失败，商城订单号：" + dtoReq.getMoneyFeedOrderId() + " , 原因: " + e.getMessage());

            if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
                // 记录错误日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(null,
                        dtoReq.getMoneyFeedOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_CREATE_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 通知商城，订单因为网络问题，不能创建
                ReceiveOrderCreateInfoDtoReq toMoneyfeedDtoReq = new ReceiveOrderCreateInfoDtoReq();
                toMoneyfeedDtoReq.setResult(false);
                toMoneyfeedDtoReq.setRemark("网络连接EBS系统失败, 创建订单失败, 商城订单号 " + dtoReq.getMoneyFeedOrderId());
                toMoneyfeedDtoReq.setMoneyfeedOrderId(dtoReq.getMoneyFeedOrderId());

                // 调用订单中心失败时，在后续的定时任务中，根据日志类型进行操作
                Boolean callOrderSystemResult = moneyFeedOrderCenterService.returnToOrderCreateInfoAndRecordLog
                        (toMoneyfeedDtoReq,
                                null, dtoReq.getMoneyFeedOrderId(),
                                        EbsOrderLogTypeEnum.TO_MONEYFEED_SEND_CREATE_FAIL_RESULT_IN_FIRST_TIME);

                result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
            } else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
                // 请求EBS返回结果超时，在定时任务中请求创建结果即可

                // 记录成功的日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(null,
                        dtoReq.getMoneyFeedOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_CREATE_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        0,
                        (byte) 1);

                result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
            } else {
                // 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(null,
                        dtoReq.getMoneyFeedOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_CREATE_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 通知商城，订单因为网络问题，不能创建
                ReceiveOrderCreateInfoDtoReq toMoneyfeedDtoReq = new ReceiveOrderCreateInfoDtoReq();
                toMoneyfeedDtoReq.setResult(false);
                toMoneyfeedDtoReq.setRemark("创建订单失败, 商城订单号 " + dtoReq.getMoneyFeedOrderId() + " 原因：" + e.getMessage());
                toMoneyfeedDtoReq.setMoneyfeedOrderId(dtoReq.getMoneyFeedOrderId());

                // 调用订单中心失败时，在后续的定时任务中，需要根据日志类型进行操作
                Boolean callOrderSystemResult = moneyFeedOrderCenterService.returnToOrderCreateInfoAndRecordLog
                        (toMoneyfeedDtoReq,
                                null, dtoReq.getMoneyFeedOrderId(),
                                EbsOrderLogTypeEnum.TO_MONEYFEED_SEND_CREATE_FAIL_RESULT_IN_FIRST_TIME);

                result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
            }

        }

        // 记录日志
        commonService.saveFulllog(null,
                null,
                null,
                result.name(),
                dtoReq,
                dtoReq,
                null,
                new Date(),
                Long.valueOf(dtoReq.getMoneyFeedOrderId()),
                null,
                Long.valueOf(dtoReq.getMoneyFeedOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_GET_EBS_CREATE_ORDER,
                "请求EBS系统创建订单，商城订单号" + dtoReq.getMoneyFeedOrderId() + " " + logMessageString,
                UserOperSourceType.MEDIUM_CENTER);

        // 根据请求EBS的结果 result，进行业务处理

        // insert ebs order
        EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
        ebsOrderBuilder.saleOrderId(dtoReq.getMoneyFeedOrderId()).saleOrderNo(dtoReq.getMoneyFeedOrderNo())
                .orderCreateStatus(result.name())
                .orderPayStatus(MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name())
                .orderCancelStatus(MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name())
                .orderUpdateStatus(MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name())
                .ebsOrgId(dtoReq.getOrgId()).ebsCustomerNo(dtoReq.getCustomerNum())
                .orderType(dtoReq.getOrderType());

        EbsOrderModel ebsOrder = ebsOrderBuilder.build();
        ebsOrderService.save(Lists.newArrayList(ebsOrder));

        // insert ebs order detail
        EbsOrderDetailModelBuilder ebsOrderDetailBuildler = EbsOrderDetailModelBuilder.anEbsOrderDetailModel();
        ebsOrderDetailBuildler.orderId(ebsOrder.getId())
                .orderCreateDate(new Date())
                .planTransportTime(dtoReq.getPlanTransportTimeDate())
                .materialChanged(false);

        EbsOrderDetailModel ebsOrderDetail = ebsOrderDetailBuildler.build();
        ebsOrderDetailService.save(Lists.newArrayList(ebsOrderDetail));

        // insert ebs order from sale
        EbsOrderFromSaleModelBuilder ebsOrderFromSaleBuilder = EbsOrderFromSaleModelBuilder.anEbsOrderFromSaleModel();
        ebsOrderFromSaleBuilder.orderId(ebsOrder.getId()).planPickupDate(dtoReq.getPlanTransportTimeDate())
                .carNo(dtoReq.getCarNo()).shopId(dtoReq.getShopId()).saleOrderJosn(JSON.toJSONString(dtoReq));

        EbsOrderFromSaleModel orderFromSale = ebsOrderFromSaleBuilder.build();
        ebsOrderFromSaleService.save(Lists.newArrayList(orderFromSale));

        // insert ebs order from sale detail
        List<EbsOrderFromSaleItemsModel> orderFromSaleItemList = Lists.newArrayList();
        for (MoneyFeedToEbsOrderCreateProductDtoReq productDto : dtoReq.getProductList()) {
            EbsOrderFromSaleItemsModelBuilder itemBuilder = EbsOrderFromSaleItemsModelBuilder.anEbsOrderFromSaleItemsModel();
            itemBuilder.saleOrderItemJson(JSON.toJSONString(productDto)).quantity(productDto.getProductCount())
                    .unit(productDto.getUnit()).materielNo(productDto.getSuppliesCode()).organizationCode(productDto.getOrganizationCode())
                    .orderFromSaleId(orderFromSale.getId()).orderId(ebsOrder.getId()).orgId(ebsOrder.getEbsOrgId());

            orderFromSaleItemList.add(itemBuilder.build());
        }

        ebsOrderFromSaleItemsService.save(orderFromSaleItemList);

        logger.info("向EBS发送订单创建请求结束, 商城订单号：" + dtoReq.getMoneyFeedOrderId() + ", 处理结果：" + result.name());

    }

    private List<EbsOrderCreateReq> converseDtoToRequestForCreate(MoneyfeedToEbsOrderCreateDtoReq dtoReq) {

        EbsCompanyService ebsCompanyService = AppContext.getSpringContext().getBean(EbsCompanyServiceImpl.class);

        List<EbsOrderCreateReq> reqList = Lists.newArrayList();

        EbsCompanyModel company = ebsCompanyService.queryEbsCompanyByOrgId(dtoReq.getOrgId());

        for (MoneyFeedToEbsOrderCreateProductDtoReq productDto : dtoReq.getProductList()) {
            EbsOrderCreateReq request = new EbsOrderCreateReq();
            BeanUtils.copyProperties(dtoReq, request);
            BeanUtils.copyProperties(productDto, request);

            request.setCompanyShortCode(company.getShortCode());

            reqList.add(request);
        }

        return reqList;
    }

}
