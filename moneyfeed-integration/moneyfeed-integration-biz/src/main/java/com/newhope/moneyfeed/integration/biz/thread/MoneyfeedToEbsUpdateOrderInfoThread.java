package com.newhope.moneyfeed.integration.biz.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderUpdateInfoDtoReq;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderModelBuilder;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderFromEbsService;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderLogService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderUpdateReq;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs.EbsResponseUpdateSimple;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.TransferWithEbsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderServiceImpl;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

/**
 * 该线程仅仅发送请求EBS，并且记录EBS的返回结果；
 * 在后续的定时任务中，根据EBS返回结果去回调订单中心。
 *
 * Created by yuyanlin on 2018/11/30
 */
public class MoneyfeedToEbsUpdateOrderInfoThread implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsUpdateOrderInfoThread.class);

    public final static String EBS_SUCCESS_CODE = "S000A000";

    private MoneyfeedToEbsOrderUpdateInfoDtoReq dtoReq;

    public MoneyfeedToEbsUpdateOrderInfoThread(MoneyfeedToEbsOrderUpdateInfoDtoReq dtoReq) {
        this.dtoReq = dtoReq;
    }

    @Override
    public void run() {
        final EbsOrderServiceImpl ebsOrderService = AppContext.getSpringContext().getBean(EbsOrderServiceImpl.class);
        final TransferWithEbsServiceImpl transferWithEbsService = AppContext.getSpringContext().getBean(TransferWithEbsServiceImpl.class);
        final EbsOrderLogService ebsOrderLogService = AppContext.getSpringContext().getBean(EbsOrderLogService.class);
        final CommonService commonService = AppContext.getSpringContext().getBean(CommonService.class);
        final EbsOrderFromEbsService ebsOrderFromEbsService = AppContext.getSpringContext().getBean(EbsOrderFromEbsService.class);

        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(dtoReq.getEbsOrderNo());
        EbsOrderFromEbsModel ebsOrderFromEbs = ebsOrderFromEbsService.getEbsOrderFromEbsByEbsOrderId(ebsOrderWithDetail.getId());

        // 记录日志
        commonService.saveFulllog(ebsOrderFromEbs.getRealPayAmount(),
                ebsOrderFromEbs.getRealPayAmount(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
                ebsOrderWithDetail,
                dtoReq,
                ebsOrderWithDetail.getEbsOrderNo(),
                new Date(),
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                ebsOrderWithDetail.getBankPayNo(),
                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
                UserOperEventType.ORDER,
                UserOperationEnums.INT_CALL_EBS_UPDATE_CAR_OR_DATE,
                "收到商城更新订单的车牌号和拉料日期的请求，EBS订单编码" + dtoReq.getEbsOrderNo(),
                UserOperSourceType.MEDIUM_CENTER);

        List<EbsOrderUpdateReq> reqList = converseDtoToRequestForUpdate(dtoReq);

        MoneyfeedToEbsOperationStatusEnum result = null;
        try {
            // 请求EBS
            EbsResponseUpdateSimple response = transferWithEbsService.sendEbsOrderUpdateRequest(reqList);

            ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                    ebsOrderWithDetail.getSaleOrderId(),
                    EbsOrderLogTypeEnum.TO_EBS_GET_UPDATE_RESULT_REQUEST.name(),
                    new Date(),
                    JSON.toJSONString(dtoReq),
                    StringUtils.EMPTY,
                    0,
                    (byte) 1);

            if (null != response && null != response.getReturnCode() && EBS_SUCCESS_CODE.equals(response.getReturnCode())) {
                result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS;
            } else {
                throw new Exception("更新订单车牌号和计划拉货日期失败，" + response.getReturnMsg());
            }
        } catch (Exception e) {
            logger.error("向EBS发送更新订单请求, EBS订单编码: " + dtoReq.getEbsOrderNo() + " 报错:" + e.getMessage());

            if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
                // 连接EBS失败，导致无法更新订单

                // 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_GET_UPDATE_RESULT_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
            } else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
                // 请求EBS返回结果超时，在定时任务中请求更新结果即可

                // 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_GET_UPDATE_RESULT_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                result = MoneyfeedToEbsOperationStatusEnum.WAIT_FOR_EBS_TIMEOUT;
            } else {
                // EBS业务异常，直接返回失败

                // 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
                        ebsOrderWithDetail.getSaleOrderId(),
                        EbsOrderLogTypeEnum.TO_EBS_GET_UPDATE_RESULT_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
            }
        }

        EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
        ebsOrderBuilder.orderUpdateStatus(result.name());

        ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());
    }

    private List<EbsOrderUpdateReq> converseDtoToRequestForUpdate(MoneyfeedToEbsOrderUpdateInfoDtoReq dtoReq) {
        if (null == dtoReq) {
            return Lists.newArrayList();
        }

        EbsOrderUpdateReq req = new EbsOrderUpdateReq();

        BeanUtils.copyProperties(dtoReq, req);

        req.setWhetherLock(dtoReq.getWhetherLock() ? "Y" : "N");

        return Lists.newArrayList(req);
    }
}
