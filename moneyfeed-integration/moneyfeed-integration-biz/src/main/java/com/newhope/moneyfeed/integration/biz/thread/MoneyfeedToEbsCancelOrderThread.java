package com.newhope.moneyfeed.integration.biz.thread;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCancelDtoReq;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderModelBuilder;
import com.newhope.moneyfeed.integration.api.service.ebs.baseData.EbsCompanyService;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderLogService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderCancelReq;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponseCancelResultList;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.MoneyFeedOrderCenterService;
import com.newhope.moneyfeed.integration.biz.service.common.TransferWithEbsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderFromEbsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderServiceImpl;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;

/**
 * Created by yuyanlin on 2018/11/27
 */
public class MoneyfeedToEbsCancelOrderThread implements Callable<Boolean> {

    private final Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsCancelOrderThread.class);

    private MoneyfeedToEbsOrderCancelDtoReq dtoReq;
    
    public MoneyfeedToEbsCancelOrderThread(MoneyfeedToEbsOrderCancelDtoReq dtoReq) {
        this.dtoReq = dtoReq;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info("开始向EBS发送取消订单请求, EBS订单编码：" + dtoReq.getEbsOrderNo());

        final EbsOrderServiceImpl ebsOrderService = AppContext.getSpringContext().getBean(EbsOrderServiceImpl.class);
        final TransferWithEbsServiceImpl transferWithEbsService = AppContext.getSpringContext().getBean(TransferWithEbsServiceImpl.class);
        final EbsOrderLogService ebsOrderLogService = AppContext.getSpringContext().getBean(EbsOrderLogService.class);
        final MoneyFeedOrderCenterService moneyFeedOrderCenterService = AppContext.getSpringContext().getBean(MoneyFeedOrderCenterService.class);
        final EbsCompanyService ebsCompanyService = AppContext.getSpringContext().getBean(EbsCompanyService.class);
        final CommonService commonService = AppContext.getSpringContext().getBean(CommonService.class);
        final EbsOrderFromEbsServiceImpl ebsOrderFromEbsServiceImpl = AppContext.getSpringContext().getBean(EbsOrderFromEbsServiceImpl.class);
        
        ArrayList<EbsOrderCancelReq> newArrayList = new ArrayList<EbsOrderCancelReq>();
        
        EbsOrderCancelReq ebsOrderCancelReq = new EbsOrderCancelReq();
        ebsOrderCancelReq.setOrg_id(dtoReq.getOrg_id());
        ebsOrderCancelReq.setEbsOrderNo(dtoReq.getEbsOrderNo());
		newArrayList.add(ebsOrderCancelReq);
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(dtoReq.getEbsOrderNo());
        if(ebsOrderWithDetail == null){
        	logger.info("通过ebsOrderNo " + dtoReq.getEbsOrderNo() + "查询ebs订单为空。");
        	return false;
        }
        Long ebsOrderId = ebsOrderWithDetail.getId();
        String saleOrderId = ebsOrderWithDetail.getSaleOrderId();
        
      //查询实际支持金额
        BigDecimal realPayAmount = BigDecimal.ZERO;
        EbsOrderFromEbsModel model = new EbsOrderFromEbsModel();
        model.setOrderId(ebsOrderWithDetail.getId());
        List<EbsOrderFromEbsModel> ebsOrderFromEbsModelList = ebsOrderFromEbsServiceImpl.query(model);
        if(!CollectionUtils.isEmpty(ebsOrderFromEbsModelList)){
        	realPayAmount = ebsOrderFromEbsModelList.get(0).getRealPayAmount();
        }
        //审计日志
        commonService.saveFulllog(realPayAmount, BigDecimal.ZERO, ebsOrderWithDetail.getOrderCancelStatus(), null,
				JSON.toJSONString(ebsOrderWithDetail), null,
				ebsOrderWithDetail.getEbsOrderNo(), new Date(), ebsOrderWithDetail.getId(), null, Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
				UserOperEventType.ORDER, UserOperationEnums.INT_SHOP_CANCEL_ORDER,
				"收到商城取消订单的请求，EBS订单编码：" + ebsOrderWithDetail.getEbsOrderNo(),
				UserOperSourceType.MEDIUM_CENTER);
        
        MoneyfeedToEbsOperationStatusEnum result = null;
        String returnMsg = Strings.EMPTY;
        try {
    		EbsResponseCancelResultList ebsOrderCancelResultList =
    				transferWithEbsService.getEbsOrderCancelResultList(newArrayList);
    		if(EBSInterfaceUtil.EBS_SUCCESS.equals(ebsOrderCancelResultList.getEbsResponseCancelResult().getReturnCode())){
    			result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
    		}else{
    			result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
    		}
    		returnMsg = ebsOrderCancelResultList.getEbsResponseCancelResult().getReturnMsg();
    		ebsOrderLogService.saveEbsOrderLogWithNoRollback(
    				ebsOrderId,
    				saleOrderId,
    				EbsOrderLogTypeEnum.TO_EBS_CANCEL_REQUEST.name(),
    				new Date(),
    				JSON.toJSONString(newArrayList),
    				returnMsg,
    				0,
    				(byte)1);
        } catch (Exception e) {
        	CommonService.formatExceptionMsg( this.getClass(), e);
        	
        	 if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
        		 logger.error("取消订单发送ebs连接超时，取消订单失败." , e);
                 returnMsg = "取消订单发送ebs连接超时，取消订单失败." ;
            	// 记录日志
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(
                		ebsOrderId,
                		saleOrderId,
                        EbsOrderLogTypeEnum.TO_EBS_CANCEL_REQUEST.name(),
                        new Date(),
                        JSON.toJSONString(dtoReq),
                        e.getMessage(),
                        1,
                        (byte) 0);

                // 通知商城，订单因为网络问题，不能取消
                EbsCompanyModel queryEbsCompanyByOrgId = ebsCompanyService.queryEbsCompanyByOrgId(dtoReq.getOrg_id());
                CancelOrderResultDtoReq cancelOrderResultDtoReq = new CancelOrderResultDtoReq();
            	
                cancelOrderResultDtoReq.setEbsOrderNo(dtoReq.getEbsOrderNo());
                cancelOrderResultDtoReq.setCompanyShortCode(queryEbsCompanyByOrgId.getShortCode());
                cancelOrderResultDtoReq.setCancelFlag(false);
                cancelOrderResultDtoReq.setMsg("取消订单发送ebs连接超时，取消订单失败.");

                // 调用订单中心失败时，在后续的定时任务中，根据日志类型进行操作
                moneyFeedOrderCenterService.returnToOrderCancelInfoAndRecordLog
                        (cancelOrderResultDtoReq,
                        		ebsOrderId, saleOrderId,
                                        EbsOrderLogTypeEnum.TO_MONEYFEED_SEND_CANCEL_FAIL_RESULT_IN_FIRST_TIME);
               
                result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
        	 }else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
        		 // 记录日志
                 ebsOrderLogService.saveEbsOrderLogWithNoRollback(
                 		ebsOrderId,
                 		saleOrderId,
                         EbsOrderLogTypeEnum.TO_EBS_CREATE_REQUEST.name(),
                         new Date(),
                         JSON.toJSONString(dtoReq),
                         e.getMessage(),
                         0,
                         (byte) 1);
                 logger.error("取消订单发送ebs网络超时，取消订单失败." , e);
                 returnMsg = "取消订单发送ebs网络超时，取消订单失败." ;
                 
                 result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
        	 } else {
        		 logger.error("取消订单异常，异常信息：" , e);
                 returnMsg = "取消订单异常，取消订单失败." ;
                 ebsOrderLogService.saveEbsOrderLogWithNoRollback(
                 		ebsOrderId,
                 		saleOrderId,
                         EbsOrderLogTypeEnum.TO_EBS_CANCEL_REQUEST.name(),
                         new Date(),
                         JSON.toJSONString(dtoReq),
                         e.getMessage(),
                         1,
                         (byte)0);

                 // 通知商城，订单因为网络问题，不能取消
                 EbsCompanyModel queryEbsCompanyByOrgId = ebsCompanyService.queryEbsCompanyByOrgId(dtoReq.getOrg_id());
                 CancelOrderResultDtoReq cancelOrderResultDtoReq = new CancelOrderResultDtoReq();
             	
                 cancelOrderResultDtoReq.setEbsOrderNo(dtoReq.getEbsOrderNo());
                 cancelOrderResultDtoReq.setCompanyShortCode(queryEbsCompanyByOrgId.getShortCode());
                 cancelOrderResultDtoReq.setCancelFlag(false);
                 cancelOrderResultDtoReq.setMsg("取消订单发送ebs异常，取消订单失败.");

                 // 调用订单中心失败时，在后续的定时任务中，根据日志类型进行操作
                 moneyFeedOrderCenterService.returnToOrderCancelInfoAndRecordLog
                         (cancelOrderResultDtoReq,
                         		ebsOrderId, saleOrderId,
                                         EbsOrderLogTypeEnum.TO_MONEYFEED_SEND_CANCEL_FAIL_RESULT_IN_FIRST_TIME);
                 
                 result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
        	 }
        }
        logger.info("向EBS发送取消订单请求结束, EBS订单编码：" + dtoReq.getEbsOrderNo() + ", 处理结果：" + result.name() + ", 返回消息：" + result.getDesc());
        
        // ebs order
        EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
        ebsOrderBuilder.orderCancelStatus(result.name());

        ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());
        
        commonService.saveFulllog(realPayAmount, BigDecimal.ZERO, ebsOrderWithDetail.getOrderCancelStatus(), result.name(),
				JSON.toJSONString(ebsOrderWithDetail), JSON.toJSONString(ebsOrderBuilder.build()),
				ebsOrderWithDetail.getEbsOrderNo(), new Date(), ebsOrderWithDetail.getId(), null, Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
				UserOperEventType.ORDER, UserOperationEnums.INT_CALL_EBS_TO_CANCAL_ORDER, returnMsg, UserOperSourceType.MEDIUM_CENTER);
        
        return true;
    }
    
    
    
//    /**
//     * 通知商城更新状态
//     * @param ebsOrderWithDetail
//     * @param ebsCompanyService
//     * @param orderFeignClient
//     */
//    public void sendReciveCancelOrderMsg(EbsOrderExModel ebsOrderWithDetail , EbsCompanyService ebsCompanyService, EbsOrderServiceImpl ebsOrderService , 
//    		OrderFeignClient orderFeignClient){
//    	EbsCompanyModel queryEbsCompanyByOrgId = ebsCompanyService.queryEbsCompanyByOrgId(ebsOrderWithDetail.getEbsOrgId());
//    	
//    	 CancelOrderResultDtoReq dtoReq = new CancelOrderResultDtoReq();
//         dtoReq.setEbsOrderNo(ebsOrderWithDetail.getEbsOrderNo());
//         dtoReq.setCompanyShortCode(queryEbsCompanyByOrgId == null ? null : queryEbsCompanyByOrgId.getShortCode());
//         dtoReq.setCancelFlag(true);
//         dtoReq.setMsg(null);
//         
//         BaseResponse<DtoResult> response = orderFeignClient.reciveCancelOrderResult(dtoReq);
//         if(!response.isSuccess()){ //如果通知商城失败，那么更新状态为处理中(OPERATING)，然后通过定时任务来处理
//        	 EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
//             ebsOrderBuilder.orderCancelStatus(MoneyfeedToEbsOperationStatusEnum.OPERATING.name());
//        	 ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());
//         }
//    }
    
    
}
