package com.newhope.order.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.bean.OrderRuleModel;
import com.newhope.moneyfeed.order.api.constant.CommonConstant;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryBaseDtoReq;
import com.newhope.moneyfeed.order.api.enums.OrderPayTypeEnum;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.dal.dao.OrderInfoDao;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.order.biz.rpc.feign.SmsFeignClient;
import com.newhope.order.biz.rpc.feign.UserOperationFeignClient;
import com.newhope.order.biz.rpc.feign.WechatMsgFeignClient;
import com.newhope.order.biz.rpc.feign.uc.ClientUserFeignClient;
import com.newhope.order.biz.service.OrderIntegrationService;
import com.newhope.order.biz.service.OrderMsgService;
import com.newhope.order.biz.service.OrderRuleService;
import com.newhope.order.biz.service.OrderScheduleService;
import com.newhope.order.biz.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderScheduleServiceImpl implements OrderScheduleService {
	
	private final Logger logger = LoggerFactory.getLogger(OrderScheduleServiceImpl.class);
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderInfoDao orderInfoDao;
	
	@Autowired
	OrderIntegrationService orderIntegrationService;

	@Autowired
	SmsFeignClient smsFeignClient;
	
	@Autowired
	WechatMsgFeignClient wechatMsgFeignClient;
	
    @Autowired
	ClientUserFeignClient clientUserFeignClient;
    
    @Autowired
    OrderMsgService orderMsgService;

    @Autowired
	UserOperationFeignClient userOperationFeignClient;
    
    @Autowired
    OrderRuleService orderRuleService;

    @Value("${newhope.wechat.hompageurl}")
    private String hompageurl;
    

	@Override
	public void closeOvertimeNotpaid() {
		
		OrderInfoQueryBaseDtoReq orderInfoQueryBaseDtoReq = new OrderInfoQueryBaseDtoReq();
		orderInfoQueryBaseDtoReq.setStatusList(Arrays.asList(OrderStatusEnum.WAITING_FOR_PAYMENT.name()));
		orderInfoQueryBaseDtoReq.setPayLimitEndTime(new Date());
		orderInfoQueryBaseDtoReq.setNotOrderPayType(OrderPayTypeEnum.OFF_LINE.name());
		List<OrderInfoModel> list =  orderInfoDao.queryOrderBaseInfoList(orderInfoQueryBaseDtoReq);
		if (CollectionUtils.isNotEmpty(list)) {
			Map<String, String> paramMap = new HashMap<String, String>(1);//sms参数集合
			for(OrderInfoModel model : list) {
				try {
					AppContext.getSpringContext().getBean(this.getClass()).closeOrder(model);
					//sms 
					paramMap.put("orderNo", model.getOrderNo());
					orderMsgService.sendSmsMsg(model.getBuyerMobile(), SmsTemplateEnums.SMS_151767094.name(), paramMap);
					//wechat
					String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + model.getId() + "&pre_path=_static_html_order_orderList";
					orderMsgService.sendWechatMsg(model.getBuyerId(), WechatMsgTypeEnums.PAY_OVERTIME_CLOSED, 
							Arrays.asList(model.getOrderNo()), url);
					
				} catch (Exception e) {
					logger.error("OrderScheduleServiceImpl.closeOvertimeNotpaid发送消息时异常:", e);
				}
				
			}
		}

	}
	
	@Transactional
	public void closeOrder(OrderInfoModel model) {
		OrderInfoModel oldModel = new OrderInfoModel();
		oldModel.setId(model.getId());
		oldModel.setStatus(model.getStatus());
		
		OrderInfoModel newModel = new OrderInfoModel();
		newModel.setStatus(OrderStatusEnum.CLOSED.getValue());
		newModel.setCloseTime(new Date());
		boolean flag = orderService.update(oldModel, newModel);
		
		if (flag) {
			//同步ebs订单关闭信息
			CancelOrderToEbsDtoReq cancelOrderToEbsDtoReq = new CancelOrderToEbsDtoReq();
			cancelOrderToEbsDtoReq.setOrgId(model.getOrgId());
			cancelOrderToEbsDtoReq.setEbsOrderNo(model.getEbsOrderNo());
			cancelOrderToEbsDtoReq.setOrderId(model.getId());
			DtoResult dtoResult = orderIntegrationService.sendCancelOrderToEbs(cancelOrderToEbsDtoReq);
			if (ResultCode.OC_EBS_ORDER_NO_DELETE.getCode().equals(dtoResult.getCode())) {
				newModel = new OrderInfoModel();
				newModel.setStatus(OrderStatusEnum.SHOP_REPEAL.name());
				orderService.update(oldModel, newModel);
			}else if(!ResultCode.SUCCESS.getCode().equals(dtoResult.getCode())){
				logger.error("关闭订单失败"+JSON.toJSONString(dtoResult));
				throw new BizException(ResultCode.OC_CLOSE_ORDER_FAIL);
			}
			
			//记录日志
			OrderInfoModel newOrderInfoModel = new OrderInfoModel();
			newOrderInfoModel.setId(oldModel.getId());
			newOrderInfoModel = orderService.query(newOrderInfoModel).get(0);
			UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
			userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
			userOperationLogDtoReq.setEventId(newOrderInfoModel.getId());
			userOperationLogDtoReq.setEbsOrderId(newOrderInfoModel.getEbsOrderNo());
			userOperationLogDtoReq.setOrderId(newOrderInfoModel.getId());
			userOperationLogDtoReq.setEventDate(new Date());
			userOperationLogDtoReq.setBeforeEventAmount(model.getTotalPayAmount());
			userOperationLogDtoReq.setAfterEventAmount(newOrderInfoModel.getTotalPayAmount());
			userOperationLogDtoReq.setBeforeEventStatus(model.getStatus());
			userOperationLogDtoReq.setAfterEventStatus(newOrderInfoModel.getStatus());
			userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.OD_CUSTOMER_CLOSE_ORDER);
			userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(model));
			userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(newOrderInfoModel));
			userOperationLogDtoReq.setComment("定时任务关闭订单");
			userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
			BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
	        if (!operaResp.isSuccess()) {
	            logger.error(String.format(ResultCode.OC_ORDER_OPERALOG_ERROR.getDesc() + "[error:{%s}" + "data:{%s}]", operaResp.getCode(), userOperationLogDtoReq));
	        }
		}
	}

	@Override
	public void remindDoPay() {
		OrderInfoQueryBaseDtoReq orderInfoQueryBaseDtoReq = new OrderInfoQueryBaseDtoReq();
		orderInfoQueryBaseDtoReq.setStatusList(Arrays.asList(OrderStatusEnum.WAITING_FOR_PAYMENT.name()));
		orderInfoQueryBaseDtoReq.setNotOrderPayType(OrderPayTypeEnum.OFF_LINE.name());
		List<OrderInfoModel> list =  orderInfoDao.queryOrderBaseInfoList(orderInfoQueryBaseDtoReq);
		if (CollectionUtils.isNotEmpty(list)) {

			int hour = 1; // 短信发送 提前小时数
			int minute = -1; // 短信发送提前分钟数
			String limitHour = "1";// 消息参数-剩余支付时间
			Date nowTime = new Date();// 当前时间
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowTime);
			calendar.add(Calendar.HOUR, hour);
			Date endNode = calendar.getTime();// 发送短信最迟节点
			calendar.add(Calendar.MINUTE, minute);
			Date beginNode = calendar.getTime();// 发送短信起始节点

			Map<String, String> paramMap = new HashMap<String, String>(2);// sms参数集合
			for (OrderInfoModel model : list) {
				Date payLimitDate = model.getPayLimitTime();
				if (payLimitDate == null){continue;}
				if (beginNode.getTime() <= payLimitDate.getTime() && endNode.getTime() > payLimitDate.getTime()) {
					try {
						// sms
						paramMap.put("orderNo", model.getOrderNo());
						paramMap.put("limitTime", limitHour);
						orderMsgService.sendSmsMsg(model.getBuyerMobile(), SmsTemplateEnums.SMS_151767090.name(), paramMap);
						// wechat
						String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + model.getId() + "&pre_path=_static_html_order_orderList";
						orderMsgService.sendWechatMsg(model.getBuyerId(), WechatMsgTypeEnums.ORDER_PRE_PAY,
								Arrays.asList(model.getOrderNo(), limitHour), url);
					} catch (Exception e) {
						logger.error("OrderScheduleServiceImpl.remindDoPay发送消息时异常:", e);
					}
				}
			}
		}
	}
	@Override
	public void remindTransport() {
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setStatus(OrderStatusEnum.WAITING_PULL_MATERIAL.name());
		List<OrderInfoModel> list = orderService.query(queryModel);
 		if(CollectionUtils.isNotEmpty(list)){
			Calendar instance = Calendar.getInstance();
			instance.add(Calendar.DAY_OF_MONTH, CommonConstant.REMIND_TIME);
			for(OrderInfoModel model : list) {
				try {
					if (DateUtils.date_sdf.format(instance.getTime()).equals(DateUtils.date_sdf.format(model.getPlanTransportTime()))) {
						//send message
						BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient.queryUserThirdAccount(model.getBuyerId(), null,AppSourceEnums.WECHAT.name());
						WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
						wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
						List<String> params = new ArrayList<>();
						params.add(model.getOrderNo());
						params.add(DateUtils.date_sdf_wz.format(model.getPlanTransportTime()));
						params.add(DateUtils.date_sdf_wz.format(model.getUcShopMobile()));
						wechatMsgDtoReq.setParams(params);
						String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + model.getId() + "&pre_path=_static_html_order_orderList";
                        wechatMsgDtoReq.setUrl(url);
						wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.TRANSPORT_TIME_REMIND);
						wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
					}
				} catch (Exception e) {
					logger.error("OrderScheduleServiceImpl.remindTransport:", e);
				}

			}
		}

	}

	@Override
	public void remindPreparingMaterial() {
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setStatus(OrderStatusEnum.MATERIAL_PREPARING.name());
		List<OrderInfoModel> list = orderService.query(queryModel);
 		if(CollectionUtils.isNotEmpty(list)){
 			//拉料时间与今天是同一天的
 			Date date = new Date();
 			Map<Long, List<OrderInfoModel>> shopMap = new HashMap<>();
 			for(OrderInfoModel model : list) {
 				//根据商家分组
				if (null != model.getPlanTransportTime() 
						&& org.apache.commons.lang3.time.DateUtils.isSameDay(date, model.getPlanTransportTime())) {
					if (null != shopMap.get(model.getUcShopId())) {
						shopMap.get(model.getUcShopId()).add(model);
					}else {
						List<OrderInfoModel> shopModelList = new ArrayList<>();
						shopModelList.add(model);
						shopMap.put(model.getUcShopId(), shopModelList);
					}
				}
 			}
 			//遍历商家分组 发送消息给商家配置联系人
 			shopMap.forEach((key, value) -> {
 				String orderNos = value.stream().map(model -> model.getOrderNo()).collect(Collectors.joining(","));
 				try {
 					OrderRuleModel ruleQueryModel = new OrderRuleModel();
 			        ruleQueryModel.setUcShopId(key);
 			        List<OrderRuleModel> rules = orderRuleService.query(ruleQueryModel);
 			        if (CollectionUtils.isNotEmpty(rules)) {
						if (rules.size() > 1) {
						    throw new BizException(ResultCode.OC_SUPPLIER_RULE_ABNORMAL);
						}
						SmsSendDtoReq dtoReq = new SmsSendDtoReq();
						dtoReq.setAuthCode(false);
						dtoReq.setMobile(rules.get(0).getNotifyMobile());
						dtoReq.setTemplateId(SmsTemplateEnums.SMS_151772077.name());
						HashMap<String, String> paramMap = new HashMap<>();
						paramMap.put("orderNos", orderNos);
						dtoReq.setParamMap(paramMap);
						smsFeignClient.sendSms(dtoReq);
 			        }
 					
 				} catch (Exception e) {
 					logger.error("OrderScheduleServiceImpl.remindPreparingMaterial:", e);
 				}
 			});
 		}
		
	}
	
}