package com.newhope.moneyfeed.integration.biz.service.common;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.QueryEbsOrderDetailInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderLogService;
import com.newhope.moneyfeed.integration.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.MoneyfeedToEbsOrderServiceImpl;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.IntegrationTimeInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsResultDtoReq;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

/**
 *  订单中心服务调用
 * Created by Dave Chen on 2018/11/22.
 */
@Service
public class MoneyFeedOrderCenterService {

    @Autowired
    private EbsOrderLogService ebsOrderLogService;

    @Autowired
    private OrderFeignClient orderFeignClient;


    @Autowired
    private MoneyfeedToEbsOrderServiceImpl moneyfeedToEbsOrderService;

    private final Logger logger = LoggerFactory.getLogger(MoneyFeedOrderCenterService.class);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean returnToOrderCreateInfoAndRecordLog(ReceiveOrderCreateInfoDtoReq dtoReq, Long ebsOrderId, String moneyfeedOrderId, EbsOrderLogTypeEnum logTypeEnum) {

        Boolean result = false;

        try {
            BaseResponse<DtoResult> response = orderFeignClient.receiveOrderCreateInfo(dtoReq);

            if (null != response && response.getCode().equals(ResultCode.SUCCESS.getCode())) {
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, moneyfeedOrderId, logTypeEnum.name(),
                        new Date(), JSON.toJSONString(dtoReq), StringUtils.EMPTY, 0, (byte)1);

                result = true;
            } else if (null != response && response.getCode().equals(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode())){
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, moneyfeedOrderId, logTypeEnum.name(),
                        new Date(), JSON.toJSONString(dtoReq), response.getMsg(), 0, (byte)1);

                result = true;
            } else {
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, moneyfeedOrderId, logTypeEnum.name(),
                        new Date(), JSON.toJSONString(dtoReq), response.getMsg(), 1, (byte)0);

                result = false;
            }

            logger.info("请求订单中心（返回更新结果）, 商城订单id " + dtoReq.getMoneyfeedOrderId() + " ，请求结果: " + result);
        } catch (Exception e) {
            CommonService.formatExceptionMsg(this.getClass(), e);

            logger.error("请求订单中心, 商城订单Id " + moneyfeedOrderId + " ，返回创建结果失败: " + e.getMessage());

            ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, moneyfeedOrderId, logTypeEnum.name(),
                    new Date(), JSON.toJSONString(dtoReq), e.getMessage(), 1, (byte)0);

            result = false;
        }

        return result;
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean returnToOrderCancelInfoAndRecordLog(CancelOrderResultDtoReq dtoReq, Long ebsOrderId , String saleOrderId, EbsOrderLogTypeEnum logTypeEnum) throws Exception {
    	Boolean result = false;
    	
        try {
            BaseResponse<DtoResult> response = orderFeignClient.reciveCancelOrderResult(dtoReq);

            if (null != response && response.getCode().equals(ResultCode.SUCCESS.getCode())) {
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, saleOrderId, logTypeEnum.name(),
                        new Date(), JSON.toJSONString(dtoReq), StringUtils.EMPTY, 0, (byte)1);

                result = true;
            } else if (null != response && response.getCode().equals(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode())){
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, saleOrderId, logTypeEnum.name(),
                        new Date(), JSON.toJSONString(dtoReq), response.getMsg(), 0, (byte)1);

                result = true;
            } else {
                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, saleOrderId, logTypeEnum.name(),
                        new Date(), JSON.toJSONString(dtoReq), response.getMsg(), 1, (byte)0);

                result = false;
            }

            logger.info("请求订单中心（返回取消结果）, EBS订单编号 " + dtoReq.getEbsOrderNo() + " ，请求结果 " + result);
        } catch (Exception e) {
            CommonService.formatExceptionMsg(this.getClass(), e);

            logger.error("请求订单中心, EBS订单编码 " + dtoReq.getEbsOrderNo() + " ，返回创建结果失败: " + e.getMessage());

            ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderId, saleOrderId, logTypeEnum.name(),
                    new Date(), JSON.toJSONString(dtoReq), e.getMessage(), 1, (byte)0);

            result = false;
        }

        return result;
    }

    public boolean returnToOrderCreateInfoNoRecordLog(ReceiveOrderCreateInfoDtoReq dtoReq) {

        Boolean result = false;

        try {
            BaseResponse<DtoResult> response = orderFeignClient.receiveOrderCreateInfo(dtoReq);

            if (null != response && response.getCode().equals(ResultCode.SUCCESS.getCode())) {
                result = true;
            } else if (null != response && response.getCode().equals(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode())){
                result = true;
            } else {
                result = false;
            }

            logger.info("请求订单中心（返回创建结果）, 商城订单id " + dtoReq.getMoneyfeedOrderId() + " ，请求结果 " + result);
        } catch (Exception e) {
            CommonService.formatExceptionMsg(this.getClass(), e);

            logger.error("请求订单中心, 商城订单Id " + dtoReq.getMoneyfeedOrderId() + " ，返回创建结果失败: " + e.getMessage());

            result = false;
        }

        return result;
    }
    
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean returnToOrderCancelInfoNoRecordLog(CancelOrderResultDtoReq dtoReq) {

        Boolean result = false;

        try {
        	BaseResponse<DtoResult> response = orderFeignClient.reciveCancelOrderResult(dtoReq);

            if (null != response && response.getCode().equals(ResultCode.SUCCESS.getCode())) {
                result = true;
            } else if (null != response && response.getCode().equals(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode())){
                result = false;
//                if(flag){ //可能ebsNo还没有同步过来，这个时候调商城的时候，返回订单不存在，就要认为是失败
//                	throw new BizException("取消订单： " + ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
//                }
            } else {
                result = false;
            }

            logger.info("请求订单中心（返回取消结果）, EBS订单编号 " + dtoReq.getEbsOrderNo() + " ，请求结果 " + result +
            		" ，response code " + response.getCode() + " , response msg " + response.getMsg());
        } catch (Exception e) {
            CommonService.formatExceptionMsg(this.getClass(), e);

            logger.error("请求订单中心（不记录日志）失败: " + e.getMessage());

            result = false;
        }

        return result;
    }

    public boolean returnToOrderUpdateInfoNoRecordLog(TransportInfoToEbsResultDtoReq toMoneyfeedDtoReq) {
        Boolean result;

        try {
            BaseResponse<DtoResult> response = orderFeignClient.reciveTransportInfoToEbsResult(toMoneyfeedDtoReq);

            if (null != response && response.getCode().equals(ResultCode.SUCCESS.getCode())) {
                result = true;
            } else if (null != response && response.getCode().equals(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode())){
                result = true;
            } else {
                result = false;
            }

            logger.info("请求订单中心（返回更新结果）, EBS订单编号 " + toMoneyfeedDtoReq.getEbsOrderNo() + " ，请求结果 " + result);
        } catch (Exception e) {
            CommonService.formatExceptionMsg(this.getClass(), e);

            logger.error("请求订单中心, EBS订单编号 " + toMoneyfeedDtoReq.getEbsOrderNo() + " ，返回修改订单结果失败: " + e.getMessage());

            result = false;
        }

        return result;
    }

    /**
     * 请求商场更新EBS修改过后的订单物料信息
     * @param saleOrderID
     */
    public  void updateSaleOrderItem(String saleOrderID){

        logger.error("请求订单中心更新 EBS变更的物料信息，商场订单ID为： "+saleOrderID );

        QueryEbsOrderDetailInfoDtoReq search =new QueryEbsOrderDetailInfoDtoReq();
        search.setMoneyfeedOrderIds(new ArrayList<String>(1));
        search.getMoneyfeedOrderIds().add(saleOrderID);

        QueryEbsOrderDetailInfoDtoResult result = moneyfeedToEbsOrderService.queryEbsOrderDetailInfo(search);

        if(result!=null && result.getOrderInfos()!=null &&! result.getOrderInfos().isEmpty()){

            IntegrationTimeInfoDtoReq req = new IntegrationTimeInfoDtoReq();

            BeanUtils.copyProperties(result.getOrderInfos().get(0),req);

            try {
            	logger.info(">>>>>>>>>---调用商城更新物料数据" + JSON.toJSONString(req)); 
                orderFeignClient.reciveEbsOrderInfo(req);
            }
            catch (Exception e){
                logger.error("请求订单中心更新物料信息时出错, EBS订单编号： " + result.getOrderInfos().get(0).getEbsOrderNo() );
               CommonService.formatExceptionMsg(this.getClass(),e);
            }

        }




    }
}
