package com.newhope.order.biz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
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
import com.newhope.moneyfeed.common.log.AliyunLog;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.goods.api.dto.request.ProductEbsQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuAttributesDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.inv.EbsItemOnhandDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCancelDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCreateDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBalanceTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderUpdateInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.QueryEbsOrderDetailInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDto;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoItemDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderMaterialInfoDtoResult;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderProductTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderStatusEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsSpecialOperationEnum;
import com.newhope.moneyfeed.order.api.bean.IntegrationLogModel;
import com.newhope.moneyfeed.order.api.bean.OrderDetailModel;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.bean.OrderPresentFeedModel;
import com.newhope.moneyfeed.order.api.bean.OrderRuleModel;
import com.newhope.moneyfeed.order.api.bean.OrderSnapshotModel;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.IntegrationTimeInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.OrderMaterialInfoDtoResult;
import com.newhope.moneyfeed.order.api.dto.request.integration.PresentFeedDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ProductDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.product.SkuAttrParam;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderPayTypeEnum;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayLimitTypeEnums;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.OrderCalculateRuleEnums;
import com.newhope.order.biz.rpc.feign.EbsInvAPIFeignClient;
import com.newhope.order.biz.rpc.feign.MoneyfeedToEbsOrderFeignClient;
import com.newhope.order.biz.rpc.feign.SmsFeignClient;
import com.newhope.order.biz.rpc.feign.UserOperationFeignClient;
import com.newhope.order.biz.rpc.feign.WechatMsgFeignClient;
import com.newhope.order.biz.rpc.feign.pay.PayIntegrationFeignClient;
import com.newhope.order.biz.rpc.feign.pc.ProductFeignClient;
import com.newhope.order.biz.rpc.feign.uc.ClientUserFeignClient;
import com.newhope.order.biz.rpc.feign.uc.ShopFeignClient;
import com.newhope.order.biz.service.IntegrationLogService;
import com.newhope.order.biz.service.OrderChangeLogService;
import com.newhope.order.biz.service.OrderDetailService;
import com.newhope.order.biz.service.OrderFeedTransportService;
import com.newhope.order.biz.service.OrderIntegrationService;
import com.newhope.order.biz.service.OrderPresentFeedService;
import com.newhope.order.biz.service.OrderRollbackService;
import com.newhope.order.biz.service.OrderRuleService;
import com.newhope.order.biz.service.OrderService;
import com.newhope.order.biz.service.OrderSnapshotService;


@Service("OrderIntegrationService")
public class OrderIntegrationServiceImpl implements OrderIntegrationService {

	public static Logger logger = LoggerFactory.getLogger(OrderIntegrationServiceImpl.class);

	@Value("${newhope.wechat.hompageurl}")
	private String hompageurl;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrderRuleService orderRuleService;
	
	@Autowired
	OrderPresentFeedService orderPresentFeedService;
	
	@Autowired
	OrderFeedTransportService orderFeedTransportService;
	
	@Autowired
	OrderRollbackService orderRollbackService;
	
	@Autowired
	MoneyfeedToEbsOrderFeignClient moneyfeedToEbsOrderFeignClient;
	
	@Autowired
	EbsInvAPIFeignClient ebsInvAPIFeignClient;
	
	@Autowired
	OrderChangeLogService orderChangeLogService;
	
	@Autowired
	SmsFeignClient smsFeignClient;
	
	@Autowired
	WechatMsgFeignClient wechatMsgFeignClient;
	
	@Autowired
	ClientUserFeignClient clientUserFeignClient;
	
	@Autowired
	ShopFeignClient shopFeignClient;
	
	@Autowired
	ProductFeignClient productFeignClient;
	
	@Autowired
	IntegrationLogService integrationLogService;
	
	@Autowired
	UserOperationFeignClient userOperationFeignClient;
	
	@Autowired
	OrderSnapshotService orderSnapshotService;
	
	@Autowired
	PayIntegrationFeignClient payIntegrationFeignClient;
	
	private final static String weight_cn = "重量";
	
	@Override
	public DtoResult sendOrderCreateInfo(MoneyfeedToEbsOrderCreateDtoReq dtoReq) {
		IntegrationLogModel integrationLogModel = new IntegrationLogModel();
		integrationLogModel.setOrderId(Long.valueOf(dtoReq.getMoneyFeedOrderId()));
		integrationLogModel.setType("SEND");
		integrationLogModel.setBizData(JSON.toJSONString(dtoReq));
		integrationLogModel.setRemark("发送创建订单请求");
		integrationLogModel.setOperType("sendOrderCreateInfo");
		integrationLogService.addLog(integrationLogModel);
		
		BaseResponse<DtoResult> result = moneyfeedToEbsOrderFeignClient.moneyfeedToEbsOrderCreate(dtoReq);
		
		integrationLogModel = new IntegrationLogModel();
		integrationLogModel.setOrderId(Long.valueOf(dtoReq.getMoneyFeedOrderId()));
		integrationLogModel.setType("RETURN");
		integrationLogModel.setBizData(JSON.toJSONString(result));
		integrationLogModel.setRemark("发送创建订单请求返回结果");
		integrationLogModel.setOperType("sendOrderCreateInfo");
		integrationLogService.addLog(integrationLogModel);
		return new DtoResult(result.getCode(), result.getMsg());
	}
	
	@Override
	@Transactional
	public DtoResult receiveOrderCreateInfo(ReceiveOrderCreateInfoDtoReq dtoReq) {
		IntegrationLogModel integrationLogModel = new IntegrationLogModel();
		integrationLogModel.setOrderId(Long.valueOf(dtoReq.getMoneyfeedOrderId()));
		integrationLogModel.setType("RECIVE");
		integrationLogModel.setBizData(JSON.toJSONString(dtoReq));
		integrationLogModel.setRemark("接收中台返回创建订单核价信息");
		integrationLogModel.setOperType("receiveOrderCreateInfo");
		integrationLogService.addLog(integrationLogModel);
		
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setId(Long.valueOf(dtoReq.getMoneyfeedOrderId()));
		List<OrderInfoModel> orderInfoList = orderService.query(queryModel);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			return DtoResult.fail(ResultCode.OC_ORDER_ID_NOT_EXISTS);
		}
		OrderInfoModel orderInfo = orderInfoList.get(0);
		
		//更新订单信息
		OrderInfoModel oldModel = new OrderInfoModel();
		oldModel.setId(orderInfo.getId());
		oldModel.setStatus(OrderStatusEnum.CREATING.name());
		OrderInfoModel newModel = new OrderInfoModel();
		if (dtoReq.isResult()) {
			newModel.setStatus(OrderStatusEnum.WAITING_FOR_PAYMENT.name());
			newModel.setEbsOrderNo(dtoReq.getEbsorderNo());
			newModel.setRemark(dtoReq.getRemark());
			newModel.setTotalOrginalAmount(dtoReq.getTotalOrginalAmount());
			newModel.setTotalPayAmount(dtoReq.getTotalPayAmount());
			newModel.setTotalDiscountAmount(dtoReq.getTotalDiscountAmount());
			//总重量
			BigDecimal totalFeedWeight = dtoReq.getProductList().stream().map(ProductDtoReq::getProductCount).reduce(BigDecimal.ZERO, BigDecimal::add);
			newModel.setTotalFeedWeight(totalFeedWeight);
			//设置超时时间
			if (PayLimitTypeEnums.LIMIT_TIME_PRICE.name().equals(orderInfo.getLimitTimeType())) {
				OrderRuleModel queryOrderRuleModel = new OrderRuleModel();
				queryOrderRuleModel.setUcShopId(orderInfo.getUcShopId());
				List<OrderRuleModel> orderRuleList = orderRuleService.query(queryOrderRuleModel);
				if (CollectionUtils.isNotEmpty(orderRuleList) && null != orderRuleList.get(0).getLimitTimeCreateEnd()) {
					Date limitDate = DateUtil.addMinute(new Date(), orderRuleList.get(0).getLimitTimeCreateEnd() * 60);
					newModel.setPayLimitTime(limitDate);
				}
				
			}
		}else {
			if (EbsSpecialOperationEnum.CANCEL_ORDER_IN_MANUAL_PRICE.getDesc().equals(dtoReq.getRemark())) {
				newModel.setStatus(OrderStatusEnum.SHOP_REPEAL.name());
			}else {
				newModel.setStatus(OrderStatusEnum.EBS_SYNC_FAILED.name());
			}
			if (StringUtils.isNotBlank(dtoReq.getRemark()) && dtoReq.getRemark().length() > 255) {
				newModel.setRemark(dtoReq.getRemark().substring(0, 255));
			}else {
				newModel.setRemark(dtoReq.getRemark());
			}
			newModel.setCloseTime(new Date());
		}
		
		//更新订单信息、订单详情信心
		boolean flag = orderService.update(oldModel, newModel);
		if (flag) {
			//更新主订单成功再操作详情、赠料，幂等防止多次调用
			if (dtoReq.isResult()) {
				//清掉订单详情，订单快照，赠品表，然后重新插入
				OrderDetailModel delOrderDetailModel = new OrderDetailModel();
				delOrderDetailModel.setOrderId(orderInfo.getId());
				orderDetailService.remove(delOrderDetailModel);
				
				OrderSnapshotModel delOrderSnapshotModel = new OrderSnapshotModel();
				delOrderSnapshotModel.setOrderId(orderInfo.getId());
				orderSnapshotService.remove(delOrderSnapshotModel);
				
				OrderPresentFeedModel delOrderPresentFeedModel = new OrderPresentFeedModel();
				delOrderPresentFeedModel.setOrderId(orderInfo.getId());
				orderPresentFeedService.remove(delOrderPresentFeedModel);
				
				List<ProductDtoReq> productList = dtoReq.getProductList();
				/**商品快照数据*/
		        List<OrderSnapshotModel> snapshots = new ArrayList<>();
		        //商品详情数据
		        List<OrderDetailModel> orderDetails = new ArrayList<>();
				//更新订单详情
				for (ProductDtoReq productDtoReq : productList) {
					//订单详情和快照对象
					OrderDetailModel orderDetail = new OrderDetailModel();
		            OrderSnapshotModel snapshot = new OrderSnapshotModel();
		        
					//订单详情的重量、原厂单价、折扣单价
					BigDecimal quantity = productDtoReq.getProductCount();
					BigDecimal orginalAmount = productDtoReq.getTotalOrginalAmount();
					//BigDecimal discountAmount = productDtoReq.getTotalDiscountAmount();
					
					//订单详情的总厂价，总支付价，总折扣价，数量
					BigDecimal totalDetailEbsPlanAmount = orginalAmount.multiply(quantity);
					BigDecimal realDetailEbsPayAmount = productDtoReq.getTotalPayAmount();
					BigDecimal discountDetailEbsAmount = totalDetailEbsPlanAmount.subtract(realDetailEbsPayAmount);
					int count = 0;
		        
			        //查询商品信息
					//根据返回物料code查询物料信息
					ProductEbsQueryDtoReq productEbsQueryDtoReq = new ProductEbsQueryDtoReq();
					productEbsQueryDtoReq.setSuppliesCodes(Arrays.asList(productDtoReq.getSuppliesCode()));
					productEbsQueryDtoReq.setOrgId(orderInfo.getOrgId());
					BaseResponse<ProductSkuListDtoResult> productResponse = productFeignClient.getEbsProduct(productEbsQueryDtoReq);

					if (!productResponse.isSuccess() 
							|| null == productResponse.getData() 
							|| CollectionUtils.isEmpty(productResponse.getData().getProductSkuList())) {
						logger.error("EBS增加物料时没有查询出商品SKU,查询参数：" + JSON.toJSONString(productEbsQueryDtoReq));
						throw new BizException(ResultCode.OC_ORDER_GOODS_QUERY_ERROR);
					}

			        ProductSkuDtoResult skuDto = productResponse.getData().getProductSkuList().get(0);
		            /**构建商品详情数据*/
		            BeanUtils.copyProperties(skuDto, orderDetail);
		            orderDetail.setOrderId(orderInfo.getId());
		            orderDetail.setCompanyShortCode(orderInfo.getCompanyShortCode());
		            orderDetail.setOrderNo(orderInfo.getOrderNo());
		            orderDetail.setUnit(productDtoReq.getUnit());
		            orderDetail.setTotalDiscountAmount(discountDetailEbsAmount);
		            orderDetail.setTotalOrginalAmount(totalDetailEbsPlanAmount);
		            orderDetail.setTotalPayAmount(realDetailEbsPayAmount);
		            orderDetail.setTotalFeedWeight(quantity);
		            orderDetail.setCount(count);
		            /**构建商品快照数据*/
		            BeanUtils.copyProperties(skuDto, snapshot);
		            snapshot.setOrderId(orderInfo.getId());
		            snapshot.setSkuId(skuDto.getProductSkuId());
		            snapshot.setOrderNo(orderInfo.getOrderNo());
		            snapshot.setQuantity(count);
		            snapshot.setShopId(orderInfo.getUcShopId());
		            snapshot.setPrice(orginalAmount);

		            List<ProductSkuAttributesDtoResult> skuAttrParamsDto = skuDto.getProductSkuAttributesExModels();
		            if (CollectionUtils.isNotEmpty(skuAttrParamsDto)) {
		                List<SkuAttrParam> skuAttrParams = new ArrayList<SkuAttrParam>(skuAttrParamsDto.size());
		                for (ProductSkuAttributesDtoResult skuAttrParamDto : skuAttrParamsDto) {
		                    SkuAttrParam skuAttrParam = new SkuAttrParam();
		                    BeanUtils.copyProperties(skuAttrParamDto, skuAttrParam);
		                    skuAttrParams.add(skuAttrParam);
		                    if (weight_cn.equals(skuAttrParamDto.getName())) {
		                        orderDetail.setWeightParam(skuAttrParamDto.getParmValue());
		                        //根据重量参数计算数量 (总重量除以重量参数)
		                        count = (quantity.divide(new BigDecimal(skuAttrParamDto.getParmValue())).intValue());
		                        orderDetail.setCount(count);
		                        snapshot.setQuantity(count);
		                    }
		                }
		                snapshot.setSkuAttrParams(JSON.toJSONString(skuAttrParams));
		            }

		            snapshots.add(snapshot);
		            orderDetails.add(orderDetail);
		            
				}
				/**插入订单详情数据*/
		        if (CollectionUtils.isNotEmpty(orderDetails)) {
		            if (!orderDetailService.save(orderDetails)) {
		                logger.error(String.format(ResultCode.OC_ORDER_DETAIL_CREATE_ERROR.getDesc() + ":{%s}"));
		                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
		            }
		        }
		        /**插入商品快照数据*/
		        if (CollectionUtils.isNotEmpty(snapshots)) {
		            if (!orderSnapshotService.save(snapshots)) {
		                logger.error(String.format(ResultCode.OC_ORDER_SNAPSHOT_CREATE_ERROR.getDesc() + ":{%s}"));
		                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
		            }
		        }
				
				
				//更新订单详情
				/*List<ProductDtoReq> productList = dtoReq.getProductList();
				OrderDetailModel rderDetailQueryModel = new OrderDetailModel();
				rderDetailQueryModel.setOrderId(orderInfo.getId());
				List<OrderDetailModel> orderDetailList = orderDetailService.query(rderDetailQueryModel);
				for (ProductDtoReq productDtoReq : productList) {
					for(OrderDetailModel orderDetail : orderDetailList) {
						if (productDtoReq.getSuppliesCode().equals(orderDetail.getSuppliesCode())) {
							OrderDetailModel oldOrderDetailModel = new OrderDetailModel();
							oldOrderDetailModel.setOrderId(orderInfo.getId());
							oldOrderDetailModel.setSuppliesCode(productDtoReq.getSuppliesCode());
							OrderDetailModel newOrderDetailModel = new OrderDetailModel();
							//newOrderDetailModel.setTotalOrginalAmount(productDtoReq.getTotalOrginalAmount());
							newOrderDetailModel.setTotalPayAmount(productDtoReq.getTotalPayAmount());
							//总折扣金额 =(原单价-折扣后单价)*数量
							newOrderDetailModel.setTotalDiscountAmount(productDtoReq.getTotalOrginalAmount().subtract(productDtoReq.getTotalDiscountAmount()).multiply(orderDetail.getTotalFeedWeight()));
							newOrderDetailModel.setUnit(productDtoReq.getUnit());
							orderDetailService.update(oldOrderDetailModel, newOrderDetailModel);
							break;
						}
					}
				}*/
				
				//更新订单赠料
				List<PresentFeedDtoReq> presentFeedList = dtoReq.getPresentFeedList();
				if (CollectionUtils.isNotEmpty(presentFeedList)) {
					List<OrderPresentFeedModel> orderPresentFeedList = new ArrayList<>();
					
					//根据返回物料code查询物料信息
					List<String> suppliesCodeList = presentFeedList.stream().map(PresentFeedDtoReq::getSuppliesCode).collect(Collectors.toList());
					ProductEbsQueryDtoReq ProductEbsQueryDtoReq = new ProductEbsQueryDtoReq();
					ProductEbsQueryDtoReq.setSuppliesCodes(suppliesCodeList);
					ProductEbsQueryDtoReq.setOrgId(orderInfo.getOrgId());
					BaseResponse<ProductSkuListDtoResult> productResponse = productFeignClient.getEbsProduct(ProductEbsQueryDtoReq);
					
					
					List<ProductSkuDtoResult> productSkuList = null;
					if(ResultCode.SUCCESS.getCode().equals(productResponse.getCode())
							&& null != productResponse.getData()){
						productSkuList = productResponse.getData().getProductSkuList();
					}
					
					for (PresentFeedDtoReq presentFeedDtoReq : presentFeedList) {
						OrderPresentFeedModel orderPresentFeedModel = new OrderPresentFeedModel();
						orderPresentFeedModel.setOrderId(orderInfo.getId());
						orderPresentFeedModel.setOrderNo(orderInfo.getOrderNo());
						
						orderPresentFeedModel.setCount(presentFeedDtoReq.getPresentFeedCount());
						orderPresentFeedModel.setUnit(presentFeedDtoReq.getUnit());
						orderPresentFeedModel.setSuppliesCode(presentFeedDtoReq.getSuppliesCode());
						//设置物料信息
						if (CollectionUtils.isNotEmpty(productSkuList)) {
							for (ProductSkuDtoResult productSkuDto : productSkuList) {
								if (presentFeedDtoReq.getSuppliesCode().equals(productSkuDto.getSuppliesCode())) {
									orderPresentFeedModel.setPcProductId(productSkuDto.getProductId());
									orderPresentFeedModel.setProductName(productSkuDto.getProductName());
									orderPresentFeedModel.setCompanyId(productSkuDto.getCompanyId());
									orderPresentFeedModel.setOrganizationCode(productSkuDto.getOrganizationCode());
									orderPresentFeedModel.setCompanyShortCode(productSkuDto.getCompanyShortCode());
									//设置重量信息
									if (CollectionUtils.isNotEmpty(productSkuDto.getProductSkuAttributesExModels())) {
										for (ProductSkuAttributesDtoResult productSkuAttributesDto : productSkuDto.getProductSkuAttributesExModels()) {
											if (weight_cn.equals(productSkuAttributesDto.getName())) {
												orderPresentFeedModel.setWeightParam(productSkuAttributesDto.getParmValue());
												break;
											}
										}
									}
									break;
								}
							}
						}
						orderPresentFeedList.add(orderPresentFeedModel);
					}
					orderPresentFeedService.save(orderPresentFeedList);
				}
			}
			
			//记录日志
			UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
			userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
			userOperationLogDtoReq.setEventId(orderInfo.getId());
			userOperationLogDtoReq.setEbsOrderId(dtoReq.getEbsorderNo());
			userOperationLogDtoReq.setOrderId(orderInfo.getId());
			userOperationLogDtoReq.setEventDate(new Date());
			userOperationLogDtoReq.setBeforeEventAmount(orderInfo.getTotalPayAmount());
			userOperationLogDtoReq.setAfterEventAmount(newModel.getTotalPayAmount());
			userOperationLogDtoReq.setBeforeEventStatus(orderInfo.getStatus());
			userOperationLogDtoReq.setAfterEventStatus(newModel.getStatus());
			userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.OD_ITG_RETURN_ORDER);
			userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(orderInfo));
			orderInfoList = orderService.query(queryModel);
			userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(orderInfoList.get(0)));
			userOperationLogDtoReq.setComment("接收中台创建订单结果");
			userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
			BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
	        if (!operaResp.isSuccess()) {
	            logger.error(String.format(ResultCode.OC_ORDER_OPERALOG_ERROR.getDesc() + "[error:{%s}" + "data:{%s}]", operaResp.getCode(), userOperationLogDtoReq));
	        }
	        
	        if (OrderCalculateRuleEnums.PERSON_CALCULATE.name().equals(orderInfo.getCusOrderType())){
				//成功发送消息
				if (OrderStatusEnum.WAITING_FOR_PAYMENT.name().equals(newModel.getStatus())) {
					OrderRuleModel queryOrderRuleModel = new OrderRuleModel();
					queryOrderRuleModel.setUcShopId(orderInfo.getUcShopId());
					List<OrderRuleModel> orderRuleList = orderRuleService.query(queryOrderRuleModel);
					
					SmsSendDtoReq smsSendDtoReq = new SmsSendDtoReq();
					smsSendDtoReq.setAuthCode(false);
					smsSendDtoReq.setMobile(orderInfo.getBuyerMobile());
					smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151772080.name());
					Map<String, String> paramMap = new HashMap<>();
					paramMap.put("orderNo", orderInfo.getOrderNo());
					if (CollectionUtils.isNotEmpty(orderRuleList) && null != orderRuleList.get(0).getLimitTimeCreateEnd()) {
						paramMap.put("limitTime", String.valueOf(orderRuleList.get(0).getLimitTimeCreateEnd()));
					}
					
					smsSendDtoReq.setParamMap(paramMap);
					smsFeignClient.sendSms(smsSendDtoReq);
					
					WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
					//查询用户微信OPENID
					BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient.queryUserThirdAccount(orderInfo.getBuyerId(), null, AppSourceEnums.WECHAT.name());
					if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
						wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
						List<String> params = new ArrayList<>();
						params.add(orderInfo.getOrderNo());
						if (CollectionUtils.isNotEmpty(orderRuleList) && null != orderRuleList.get(0).getLimitTimeCreateEnd()) {
							params.add(String.valueOf(orderRuleList.get(0).getLimitTimeCreateEnd()));
						}
						wechatMsgDtoReq.setParams(params);
						String url = hompageurl + "/static/html/order/order_payment.html?orderId=" + orderInfo.getId() + "&pre_path=_static_html_order_orderList";
						wechatMsgDtoReq.setUrl(url);
						wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.ORDER_WAITING_PAY);
						wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
					}
				}else if(OrderStatusEnum.SHOP_REPEAL.name().equals(newModel.getStatus())){
					SmsSendDtoReq smsSendDtoReq = new SmsSendDtoReq();
					smsSendDtoReq.setAuthCode(false);
					smsSendDtoReq.setMobile(orderInfo.getBuyerMobile());
					smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151772083.name());
					Map<String, String> paramMap = new HashMap<>();
					paramMap.put("orderNo", orderInfo.getOrderNo());
					paramMap.put("tel", orderInfo.getUcShopMobile());
					smsSendDtoReq.setParamMap(paramMap);
					smsFeignClient.sendSms(smsSendDtoReq);
					
					WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
					//查询用户微信OPENID
					BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient.queryUserThirdAccount(orderInfo.getBuyerId(), null, AppSourceEnums.WECHAT.name());
					if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
						wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
						List<String> params = new ArrayList<>();
						params.add(orderInfo.getOrderNo());
						params.add(orderInfo.getUcShopMobile());
						wechatMsgDtoReq.setParams(params);
						String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfo.getId() + "&pre_path=_static_html_order_orderList";
						wechatMsgDtoReq.setUrl(url);
						wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.ORDER_CLOSE);
						wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
					}
				}
			}
		}
		
		return  DtoResult.success();
	}

	@Override
	public DtoResult sendOrderPayInfo(MoneyfeedToEbsOrderPayInBalanceTypeDtoReq dtoReq) {
		IntegrationLogModel integrationLogModel = new IntegrationLogModel();
		//integrationLogModel.setOrderId(Long.valueOf(dtoReq.getEbsOrderNo()));
		integrationLogModel.setType("SEND");
		integrationLogModel.setBizData(JSON.toJSONString(dtoReq));
		integrationLogModel.setRemark("发送订单EBS订单支付请求,ebsNo:" + dtoReq.getEbsOrderNo());
		integrationLogModel.setOperType("sendOrderPayInfo");
		integrationLogService.addLog(integrationLogModel);
		
		BaseResponse<DtoResult> result = moneyfeedToEbsOrderFeignClient.moneyfeedToEbsOrderPayInBalanceType(dtoReq);
		
		integrationLogModel = new IntegrationLogModel();
		//integrationLogModel.setOrderId(Long.valueOf(dtoReq.getEbsOrderNo()));
		integrationLogModel.setType("RETURN");
		integrationLogModel.setBizData(JSON.toJSONString(result));
		integrationLogModel.setRemark("发送订单EBS订单支付请求返回结果,ebsNo:" + dtoReq.getEbsOrderNo());
		integrationLogModel.setOperType("sendOrderPayInfo");
		integrationLogService.addLog(integrationLogModel);
		return new DtoResult(result.getCode(), result.getMsg());
	}
	
	@Override
	@Transactional
	public DtoResult receiveOrderPayInfo(PayResultDtoReq dtoReq) {
		
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setEbsOrderNo(dtoReq.getEbsorderNo());
		List<OrderInfoModel> orderInfoList = orderService.query(queryModel);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			return DtoResult.fail(ResultCode.OC_EBS_ORDER_NO_NOT_EXISTS);
		}
		OrderInfoModel orderInfo = orderInfoList.get(0);
		
		//更新订单信息
		OrderInfoModel oldModel = new OrderInfoModel();
		oldModel.setId(orderInfo.getId());
		oldModel.setStatus(OrderStatusEnum.PAYING.name());
		OrderInfoModel newModel = new OrderInfoModel();
		if (dtoReq.getPayFlag()) {
			newModel.setStatus(OrderStatusEnum.WAITING_PULL_MATERIAL.name());
			newModel.setPayType(OrderPayTypeEnum.BALANCE.name());
			newModel.setPayFlag(true);
			newModel.setPayTime(new Date());
		}else {
			newModel.setStatus(OrderStatusEnum.WAITING_FOR_PAYMENT.name());
			if (StringUtils.isNotBlank(dtoReq.getMsg()) && dtoReq.getMsg().length() > 255) {
				newModel.setRemark(dtoReq.getMsg().substring(0, 255));
			}else {
				newModel.setRemark(dtoReq.getMsg());
			}
			
		}
		boolean flag = orderService.update(oldModel, newModel);
		if (flag) {
			//通知支付中心支付结果
			ReciveNotifyEbsPayDtoReq reciveNotifyEbsPayDtoReq = new ReciveNotifyEbsPayDtoReq();
			BeanUtils.copyProperties(dtoReq, reciveNotifyEbsPayDtoReq);
			payIntegrationFeignClient.reciveNotifyEbsPay(reciveNotifyEbsPayDtoReq);
			
			//记录日志
			UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
			userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
			userOperationLogDtoReq.setEventId(orderInfo.getId());
			userOperationLogDtoReq.setEbsOrderId(orderInfo.getEbsOrderNo());
			userOperationLogDtoReq.setOrderId(orderInfo.getId());
			userOperationLogDtoReq.setEventDate(new Date());
			userOperationLogDtoReq.setBeforeEventAmount(orderInfo.getTotalPayAmount());
			userOperationLogDtoReq.setAfterEventAmount(orderInfo.getTotalPayAmount());
			userOperationLogDtoReq.setBeforeEventStatus(orderInfo.getStatus());
			userOperationLogDtoReq.setAfterEventStatus(newModel.getStatus());
			userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.PA_EBS_RETURN_PAY_RESULT);
			userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(orderInfo));
			orderInfoList = orderService.query(queryModel);
			userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(orderInfoList.get(0)));
			userOperationLogDtoReq.setComment("接收中台ebs支付订单结果");
			userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
			BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
	        if (!operaResp.isSuccess()) {
	            logger.error(String.format(ResultCode.OC_ORDER_OPERALOG_ERROR.getDesc() + "[error:{%s}" + "data:{%s}]", operaResp.getCode(), userOperationLogDtoReq));
	        }
	        
	        //支付成功 发送消息
	        if (dtoReq.getPayFlag()) {
	        	OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
	            orderFeedTransportModel.setOrderId(orderInfo.getId());
	            List<OrderFeedTransportModel> listOrderFeedTransportModel = orderFeedTransportService.query(orderFeedTransportModel);
	            if (CollectionUtils.isNotEmpty(listOrderFeedTransportModel)) {
	            	orderFeedTransportModel = listOrderFeedTransportModel.get(0);
	            	if (StringUtils.isNotEmpty(orderFeedTransportModel.getDriverMobile())) {
	            		
	            		SmsSendDtoReq smsSendDtoReq = new SmsSendDtoReq();
						smsSendDtoReq.setAuthCode(false);
						smsSendDtoReq.setMobile(orderFeedTransportModel.getDriverMobile());
						smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151767096.name());
						Map<String, String> paramMap = new HashMap<>();
						paramMap.put("userName", orderInfo.getBuyerName());
						paramMap.put("shopName", orderInfo.getUcShopName());
						paramMap.put("transportTime", DateUtils.date2Str(orderInfo.getPlanTransportTime(), DateUtils.datetimeFormat));
						paramMap.put("weight", orderInfo.getTotalFeedWeight().divide(new BigDecimal(1000)).toString());
						paramMap.put("tel", orderInfo.getBuyerMobile());
						
						smsSendDtoReq.setParamMap(paramMap);
						smsFeignClient.sendSms(smsSendDtoReq);
					}
	            }
	            
	        }
		}
		
		return  DtoResult.success();
	}

	@Override
	public DtoResult sendCancelOrderToEbs(CancelOrderToEbsDtoReq cancelOrderToEbsDtoReq) {
		QueryEbsOrderDetailInfoDtoReq search = new QueryEbsOrderDetailInfoDtoReq();
		search.setMoneyfeedOrderIds(Arrays.asList(cancelOrderToEbsDtoReq.getOrderId().toString()));
		BaseResponse<QueryEbsOrderDetailInfoDtoResult> feignResult = moneyfeedToEbsOrderFeignClient.moneyfeedQueryEbsOrder(search);
		
		DtoResult dtoResult = new DtoResult();
		if(!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) 
				|| null == feignResult.getData() || CollectionUtils.isEmpty(feignResult.getData().getOrderInfos())){
			logger.error("客户取消订单失败"+JSON.toJSONString(dtoResult));
			dtoResult.setCode(ResultCode.OC_CANCEL_ORDER_FAIL.getCode());
			dtoResult.setMsg("未查找到对应EBS订单");
			return dtoResult;
		}
		QueryEbsOrderDetailInfoItemDtoResult orderDetailInfoResult = feignResult.getData().getOrderInfos().get(0);
		
		if(StringUtils.isBlank(orderDetailInfoResult.getEbsOrderNo())){
			logger.error("[OrderIntegrationServiceImpl.sendCancelOrderForEbs请求数据EBS订单no为空]");
			dtoResult.setCode(ResultCode.OC_EBS_ORDER_NO_NOT_EXISTS.getCode());
			dtoResult.setMsg("订单正在处理中");
			return dtoResult;
		}
		if (StringUtils.isBlank(cancelOrderToEbsDtoReq.getEbsOrderNo())) {
			OrderInfoModel oldModel = new OrderInfoModel();
			oldModel.setId(cancelOrderToEbsDtoReq.getOrderId());
			OrderInfoModel newModel = new OrderInfoModel();
			newModel.setEbsOrderNo(orderDetailInfoResult.getEbsOrderNo());
			orderService.update(oldModel, newModel);
		}
		//ebs已删除，直接返回成功
		if (EbsOrderStatusEnum.DELETE.name().equals(orderDetailInfoResult.getEbsOrderStatus())) {
			return DtoResult.fail(ResultCode.OC_EBS_ORDER_NO_DELETE);
		}
		
		//组装数据请求中台
		MoneyfeedToEbsOrderCancelDtoReq moneyfeedToEbsOrderCancelDtoReq = new MoneyfeedToEbsOrderCancelDtoReq();
		moneyfeedToEbsOrderCancelDtoReq.setEbsOrderNo(orderDetailInfoResult.getEbsOrderNo());
		moneyfeedToEbsOrderCancelDtoReq.setOrg_id(cancelOrderToEbsDtoReq.getOrgId());
		
		IntegrationLogModel integrationLogModel = new IntegrationLogModel();
		integrationLogModel.setOrderId(cancelOrderToEbsDtoReq.getOrderId());
		integrationLogModel.setType("SEND");
		integrationLogModel.setBizData(JSON.toJSONString(moneyfeedToEbsOrderCancelDtoReq));
		integrationLogModel.setRemark("发送关闭订单请求");
		integrationLogModel.setOperType("sendCancelOrderToEbs");
		integrationLogService.addLog(integrationLogModel);
		
		BaseResponse<DtoResult> result = moneyfeedToEbsOrderFeignClient.moneyfeedToEbsOrderCancel(moneyfeedToEbsOrderCancelDtoReq);
		
		integrationLogModel = new IntegrationLogModel();
		integrationLogModel.setOrderId(cancelOrderToEbsDtoReq.getOrderId());
		integrationLogModel.setType("RETURN");
		integrationLogModel.setBizData(JSON.toJSONString(result));
		integrationLogModel.setRemark("发送关闭订单请求返回结果");
		integrationLogModel.setOperType("sendCancelOrderToEbs");
		integrationLogService.addLog(integrationLogModel);
		
		return new DtoResult(result.getCode(), result.getMsg());
	}
	
	@Override
	public DtoResult reciveCancelOrderResult(CancelOrderResultDtoReq cancelOrderResultDtoReq) {
		IntegrationLogModel integrationLogModel = new IntegrationLogModel();
		//integrationLogModel.setOrderId(cancelOrderResultDtoReq.getEbsOrderNo());
		integrationLogModel.setType("RECIVE");
		integrationLogModel.setBizData(JSON.toJSONString(cancelOrderResultDtoReq));
		integrationLogModel.setRemark("接收中台返回关闭订单信息,ebsNo:" + cancelOrderResultDtoReq.getEbsOrderNo());
		integrationLogModel.setOperType("reciveCancelOrderResult");
		integrationLogService.addLog(integrationLogModel);
		
		DtoResult dtoResult = DtoResult.success();
		
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setEbsOrderNo(cancelOrderResultDtoReq.getEbsOrderNo());
		List<OrderInfoModel> orderInfoList = orderService.query(queryModel);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			return DtoResult.fail(ResultCode.OC_ORDER_ID_NOT_EXISTS);
		}
		OrderInfoModel orderInfo = orderInfoList.get(0);
		if(OrderStatusEnum.CLOSED.name().equals(orderInfo.getStatus())
				|| OrderStatusEnum.CUSTOMER_REPEAL.name().equals(orderInfo.getStatus())
				|| OrderStatusEnum.SHOP_REPEAL.name().equals(orderInfo.getStatus())){
			return dtoResult;
		}
		OrderInfoModel oldOrderInfoModel = new OrderInfoModel();
		oldOrderInfoModel.setId(orderInfo.getId());
		OrderInfoModel newOrderInfoModel = new OrderInfoModel();
		if(cancelOrderResultDtoReq.isCancelFlag()){
			newOrderInfoModel.setStatus(OrderStatusEnum.CUSTOMER_REPEAL.name());
		}else{
			//TODO:取消失败状态
			newOrderInfoModel.setRemark(cancelOrderResultDtoReq.getMsg());
		}
		
		//修改订单主表
		orderService.update(oldOrderInfoModel, newOrderInfoModel);
		
		//记录日志
		UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
		userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
		userOperationLogDtoReq.setEventId(orderInfo.getId());
		userOperationLogDtoReq.setEbsOrderId(cancelOrderResultDtoReq.getEbsOrderNo());
		userOperationLogDtoReq.setOrderId(orderInfo.getId());
		userOperationLogDtoReq.setEventDate(new Date());
		userOperationLogDtoReq.setBeforeEventAmount(orderInfo.getTotalPayAmount());
		userOperationLogDtoReq.setAfterEventAmount(orderInfo.getTotalPayAmount());
		userOperationLogDtoReq.setBeforeEventStatus(orderInfo.getStatus());
		String newStatus = StringUtils.isNotBlank(newOrderInfoModel.getStatus()) ? newOrderInfoModel.getStatus() : orderInfo.getStatus();
		userOperationLogDtoReq.setAfterEventStatus(newStatus);
		userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.INT_CALL_EBS_TO_CANCAL_ORDER);
		userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(orderInfo));
		orderInfoList = orderService.query(queryModel);
		userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(orderInfoList.get(0)));
		userOperationLogDtoReq.setComment("接收中台用户取消订单结果");
		userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
		userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
		
		return dtoResult;
	}

	@Override
	public DtoResult sendTransportInfoToEbs(TransportInfoToEbsDtoReq transportInfoToEbsDtoReq) {
		logger.info("[OrderIntegrationServiceImpl.sendTransportInfoToEbs]更新拉料信息请求参数为："+ JSON.toJSONString(transportInfoToEbsDtoReq));
		DtoResult dtoResult = new DtoResult();
		OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
		OrderInfoModel orderInfoModel = new OrderInfoModel();
		if(transportInfoToEbsDtoReq.getOrderId() == null){
			if(transportInfoToEbsDtoReq.getOrderFeedTransportId() == null){
				logger.error("[OrderIntegrationServiceImpl.sendTransportInfoToEbs]拉料信息ID为空");
				return  DtoResult.fail(ResultCode.FAIL);
			}
			//查询订单拉料信息
			orderFeedTransportModel.setId(transportInfoToEbsDtoReq.getOrderFeedTransportId());
			List<OrderFeedTransportModel> listOrderFeedTransportModel = orderFeedTransportService.query(orderFeedTransportModel);
			if(CollectionUtils.isEmpty(listOrderFeedTransportModel)){
				logger.error("[OrderIntegrationServiceImpl.sendTransportInfoToEbs]没有查到拉料信息：" + transportInfoToEbsDtoReq.getOrderFeedTransportId());
				return  DtoResult.fail(ResultCode.FAIL);
			}
			orderFeedTransportModel = listOrderFeedTransportModel.get(0);
			orderInfoModel.setId(orderFeedTransportModel.getOrderId());
		}else{
			if(transportInfoToEbsDtoReq.getOrderId() == null){
				logger.error("[OrderIntegrationServiceImpl.sendTransportInfoToEbs]订单ID为空");
				return  DtoResult.fail(ResultCode.FAIL);
			}
			orderInfoModel.setId(transportInfoToEbsDtoReq.getOrderId());
		}
		//查询订单
		List<OrderInfoModel> orderInfoModels = orderService.query(orderInfoModel);
		if(CollectionUtils.isEmpty(orderInfoModels)){
			logger.error("[OrderIntegrationServiceImpl.sendTransportInfoToEbs]没有查到订单信息：" + orderInfoModel.getId());
			return  DtoResult.fail(ResultCode.FAIL);
		}
		orderInfoModel = orderInfoModels.get(0);
		
		//数据请求中台
		MoneyfeedToEbsOrderUpdateInfoDtoReq moneyfeedToEbsOrderUpdateInfoDtoReq = new MoneyfeedToEbsOrderUpdateInfoDtoReq();
		moneyfeedToEbsOrderUpdateInfoDtoReq.setEbsOrderNo(orderInfoModel.getEbsOrderNo());
		moneyfeedToEbsOrderUpdateInfoDtoReq.setOrgId(orderInfoModel.getOrgId());
		moneyfeedToEbsOrderUpdateInfoDtoReq.setWhetherLock(transportInfoToEbsDtoReq.getWhetherLock());
		
		if(transportInfoToEbsDtoReq.getCarNo() != null){
			moneyfeedToEbsOrderUpdateInfoDtoReq.setCarNo(transportInfoToEbsDtoReq.getCarNo());
		}
		if(transportInfoToEbsDtoReq.getPlanTransportTime() != null){
			moneyfeedToEbsOrderUpdateInfoDtoReq.setPlanTransportTime(DateUtils.date2Str(transportInfoToEbsDtoReq.getPlanTransportTime(), DateUtils.date_sdf));
			moneyfeedToEbsOrderUpdateInfoDtoReq.setPlanTransportTimeDate(transportInfoToEbsDtoReq.getPlanTransportTime());
		}
		else{
			moneyfeedToEbsOrderUpdateInfoDtoReq.setPlanTransportTime(DateUtils.date2Str(orderInfoModel.getPlanTransportTime(), DateUtils.date_sdf));
			moneyfeedToEbsOrderUpdateInfoDtoReq.setPlanTransportTimeDate(orderInfoModel.getPlanTransportTime());
		}
		logger.info("[OrderIntegrationServiceImpl.sendTransportInfoToEbs请求EBS参数]" + JSON.toJSONString(moneyfeedToEbsOrderUpdateInfoDtoReq));
		BaseResponse<DtoResult> feginResult = moneyfeedToEbsOrderFeignClient.moneyfeedToEbsOrderUpdateInfo(moneyfeedToEbsOrderUpdateInfoDtoReq);
		logger.info("[OrderIntegrationServiceImpl.sendTransportInfoToEbs返回EBS结果为]" + JSON.toJSONString(feginResult));
		if(feginResult.isSuccess()){
			dtoResult.setCode(ResultCode.SUCCESS.getCode());
			dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		}else{
			dtoResult.setCode(ResultCode.FAIL.getCode());
			dtoResult.setMsg(ResultCode.FAIL.getDesc());
		}
		return dtoResult;
	}

	@Override
	public DtoResult reciveTransportInfoToEbsResult(TransportInfoToEbsResultDtoReq transportInfoToEbsResultDtoReq) {
		logger.info("[OrderIntegrationServiceImpl.reciveTransportInfoToEbsResult]接收的参数" + JSON.toJSONString(transportInfoToEbsResultDtoReq));
		DtoResult dtoResult = new DtoResult();
		if(transportInfoToEbsResultDtoReq.getEbsOrderNo() == null){
			logger.error("[OrderIntegrationServiceImpl.reciveTransportInfoToEbsResult]请求数据EBS订单no为空");
			dtoResult.setCode(ResultCode.OC_EBS_ORDER_NO_NOT_EXISTS.getCode());
			dtoResult.setMsg(ResultCode.OC_EBS_ORDER_NO_NOT_EXISTS.getDesc());
			return dtoResult;
		}
		if(!transportInfoToEbsResultDtoReq.isModifyFlag()){
			OrderInfoModel orderInfoModel = new OrderInfoModel();
			orderInfoModel.setEbsOrderNo(transportInfoToEbsResultDtoReq.getEbsOrderNo());
			List<OrderInfoModel> list = orderService.query(orderInfoModel);
			orderInfoModel = list.get(0);
			OrderFeedTransportModel oldOrderFeedTransportModel = new OrderFeedTransportModel();
			oldOrderFeedTransportModel.setOrderId(list.get(0).getId());
			OrderFeedTransportModel newOrderFeedTransportModel = new OrderFeedTransportModel();
			newOrderFeedTransportModel.setRemark(transportInfoToEbsResultDtoReq.getMsg());
			orderFeedTransportService.update(oldOrderFeedTransportModel, newOrderFeedTransportModel);
			addUcLog(orderInfoModel, new StringBuffer(transportInfoToEbsResultDtoReq.getMsg()), UserOperationEnums.INT_SHOP_UPDATE_CAR_OR_DATE);
		}
		
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		return dtoResult;
	}
	
	@Override
	public DtoResult reciveRepositoryLow(RepositoryLowDtoReq repositoryLowDtoReq) {
		logger.info("[OrderIntegrationServiceImpl.reciveRepositoryLow]接收EBS库存不足数据" + JSON.toJSONString(repositoryLowDtoReq));
		DtoResult dtoResult = new DtoResult();
		if(repositoryLowDtoReq.getMoneyfeedOrderId() == null){
			logger.error("[OrderIntegrationServiceImpl.reciveRepositoryLow]请求数据MoneyfeedOrderId为空");
			dtoResult.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return dtoResult;
		}
		
		OrderInfoModel orderInfoModel = new OrderInfoModel();
		orderInfoModel.setId(repositoryLowDtoReq.getMoneyfeedOrderId());
		List<OrderInfoModel> list = orderService.query(orderInfoModel);
		orderInfoModel = list.get(0);
		OrderInfoModel oldOrderInfoModel = new OrderInfoModel();
		oldOrderInfoModel.setId(orderInfoModel.getId());
		OrderInfoModel newOrderInfoModel = new OrderInfoModel();
		boolean isUpdate = false;
		
		//如果当前库存够，并且当前库存和上次有不同,商城订单状态为待拉料
		if(!repositoryLowDtoReq.getRepositoryLowFlag() && !repositoryLowDtoReq.getRepositoryLowFlag().equals(repositoryLowDtoReq.getBeforeRepositoryLowFlag())){
			newOrderInfoModel.setStatus(OrderStatusEnum.WAITING_PULL_MATERIAL.name());
			orderService.update(oldOrderInfoModel, newOrderInfoModel);
			
			isUpdate = true;
		}
		//如果当前库存不够,商城订单状态为备货中
		else if(repositoryLowDtoReq.getRepositoryLowFlag()){
			//库存不够，商城订单状态为备货中
			oldOrderInfoModel.setId(orderInfoModel.getId());
			newOrderInfoModel.setStatus(OrderStatusEnum.MATERIAL_PREPARING.name());
			orderService.update(oldOrderInfoModel, newOrderInfoModel);
			
			//如果不是0点到7点，发消息给用户,因库存可能提前跑23点55以后的也不发消息
			long current = System.currentTimeMillis();//当前时间毫秒数
			long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
			long seven = zero + 7 * 60 * 60 * 1000 - 1;//今天7点59分59秒的毫秒数
			long eleven = (zero + 23 * 60 * 60 * 1000) + (55 * 60 * 1000);
//			System.out.println(new Date(eleven));
	        if(current > seven && current < eleven){
	        	//发送短信给用户
				SmsSendDtoReq dtoReq = new SmsSendDtoReq();
				dtoReq.setAuthCode(false);
				dtoReq.setMobile(orderInfoModel.getBuyerMobile());
				dtoReq.setTemplateId(SmsTemplateEnums.SMS_154592019.name());
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("transportTime", DateUtils.date2Str(orderInfoModel.getTransportTime(), DateUtils.date_sdf));
				paramMap.put("orderNo", orderInfoModel.getOrderNo());
				paramMap.put("tel", orderInfoModel.getUcShopMobile());
				dtoReq.setParamMap(paramMap);
				smsFeignClient.sendSms(dtoReq);
				
	        	//发送微信给用户
				BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient.queryUserThirdAccount(orderInfoModel.getBuyerId(), null, AppSourceEnums.WECHAT.name());
				if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
					WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
					wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
					List<String> params = new ArrayList<>();
					params.add(orderInfoModel.getOrderNo());
					params.add(orderInfoModel.getUcShopMobile());
					wechatMsgDtoReq.setParams(params);
					String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfoModel.getId() + "&pre_path=_static_html_order_orderList";
					wechatMsgDtoReq.setUrl(url);
					wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.ORDER_STOCK_CHANGE);
					wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
				}

//				当日订单支付成功但锁库存失败	商户后台配置的手机号码（客户交易关键信息通知人）	
//				【料你富】今日订单${userName}的${orderNo}库存不足，为避免发生纠纷，请及时安排生产或联系客户修改拉料时间。	
//				－	1.触发时间：实时	短信通知	锁库失败订单的订单编号、用户名称		SMS_154586847
				//发送短信给商户配置联系人
				dtoReq = new SmsSendDtoReq();
				dtoReq.setAuthCode(false);
				dtoReq.setMobile(orderInfoModel.getNotifyMobile());
				dtoReq.setTemplateId(SmsTemplateEnums.SMS_154586847.name());
				paramMap = new HashMap<>();
				paramMap.put("userName", orderInfoModel.getBuyerName());
				paramMap.put("orderNo", orderInfoModel.getOrderNo());
				dtoReq.setParamMap(paramMap);
				smsFeignClient.sendSms(dtoReq);
	        }
	        
			isUpdate = true;
		}
		if(isUpdate){
			StringBuffer comment = new StringBuffer("接收库存是否不足消息");
			addUcLog(orderInfoModel, comment, UserOperationEnums.INT_UPDATE_INVENTORY_SUCCESS);
		}
		
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		return dtoResult;
	}
	
	@Override
	@Transactional
	public DtoResult reciveIntegrationTimeInfo(IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq) {
		/*该方法处理内容,ebs的订单信息变更时，包括以下：
			下面参数为和商城订单值 进行比较，不一样，则更新
			transportTime	进厂时间
			closeTime	订单关闭时间
			checkinTime	订单开票时间
			cancleTime	EBS取消时间
			dealTime	出厂时间
			carNo	EBS车牌号
			carInWeight	进厂车辆重量
			carOutWeight	出厂车辆重量
			planTransportTime	计划拉料日期（YYYY-MM-DD）
		 */
		DtoResult dtoResult = new DtoResult();
		if(integrationTimeInfoDtoReq.getMoneyfeedOrderId() == null){
			logger.error("[OrderIntegrationServiceImpl.reciveIntegrationTimeInfo请求数据商城订单ID为空]");
			dtoResult.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return dtoResult;
		}
		StringBuffer comment = new StringBuffer("根据中台的各种事件修改订单--s");
		//查询订单
		OrderInfoModel orderInfoModel = new OrderInfoModel();
		orderInfoModel.setId(Long.valueOf(integrationTimeInfoDtoReq.getMoneyfeedOrderId()));
		orderInfoModel = orderService.query(orderInfoModel).get(0);
		//查询订单拉料信息
		OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
		orderFeedTransportModel.setOrderId(orderInfoModel.getId());
		List<OrderFeedTransportModel> listOrderFeedTransportModel = orderFeedTransportService.query(orderFeedTransportModel);
		orderFeedTransportModel = listOrderFeedTransportModel.get(0);
		//查询赠包信息
		OrderPresentFeedModel orderPresentFeedModel = new OrderPresentFeedModel();
		orderPresentFeedModel.setOrderId(orderInfoModel.getId());
		List<OrderPresentFeedModel> orderPresentFeedModels = orderPresentFeedService.query(orderPresentFeedModel);
		BigDecimal presentWeight = BigDecimal.ZERO;
		if(CollectionUtils.isNotEmpty(orderPresentFeedModels)){
			for (OrderPresentFeedModel orderPresentFeedModel2 : orderPresentFeedModels) {
				presentWeight = presentWeight.add(new BigDecimal(orderPresentFeedModel2.getCount()));
			}
		}
				
		//发送消息对象
		SmsSendDtoReq smsSendDtoReq = null;
		WechatMsgDtoReq wechatMsgDtoReq = null;
		
		//商城订单修改新老对象
		OrderInfoModel oldOrderInfoModel = new OrderInfoModel();
		oldOrderInfoModel.setId(Long.valueOf(integrationTimeInfoDtoReq.getMoneyfeedOrderId()));
		OrderInfoModel newOrderInfoModel = new OrderInfoModel();
		
		//是否需要修改商城订单标志
		boolean updateFlag = false;
		//判断事件的优先级,订单状态的更改以最后的事件为准
		if(integrationTimeInfoDtoReq.getEbsOrderNo() != null && StringUtils.isEmpty(orderInfoModel.getEbsOrderNo())){
			newOrderInfoModel.setEbsOrderNo(integrationTimeInfoDtoReq.getEbsOrderNo());
			updateFlag = true;
		}
		
		if(integrationTimeInfoDtoReq.getCheckinTime() != null){
			if(orderInfoModel.getCheckinTime() == null || (integrationTimeInfoDtoReq.getCheckinTime().compareTo(orderInfoModel.getCheckinTime()) != 0)){
				newOrderInfoModel.setCheckinTime(integrationTimeInfoDtoReq.getCheckinTime());
				
				updateFlag = true;
			}
		}
		
		if(integrationTimeInfoDtoReq.getTransportTime() != null){
			if(orderInfoModel.getTransportTime() == null || (integrationTimeInfoDtoReq.getTransportTime().compareTo(orderInfoModel.getTransportTime()) != 0)){
				newOrderInfoModel.setTransportTime(integrationTimeInfoDtoReq.getTransportTime());
				newOrderInfoModel.setStatus(OrderStatusEnum.ALREADY_ENTRY_FACTORY.name());
				
				wechatMsgDtoReq = new WechatMsgDtoReq();
				List<String> params = new ArrayList<>();
				params.add(orderInfoModel.getOrderNo());
				params.add(DateUtils.date2Str(integrationTimeInfoDtoReq.getTransportTime(), DateUtils.datetimeFormat));
				params.add(DateUtils.date2Str(integrationTimeInfoDtoReq.getCheckinTime(), DateUtils.datetimeFormat));
				params.add(integrationTimeInfoDtoReq.getCarNo());
				wechatMsgDtoReq.setParams(params);
				String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfoModel.getId() + "&pre_path=_static_html_order_orderList";
				wechatMsgDtoReq.setUrl(url);
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.ORDER_BILL);
				updateFlag = true;
			}
		}
		
		//如果BOOKED("EBS已登记，已收款，待拉货"),并且商城订单是待提货,商城订单为待拉料
		if(EbsOrderStatusEnum.BOOKED.name().equals(integrationTimeInfoDtoReq.getEbsOrderStatus())
				&& (OrderStatusEnum.WAITING_OFF_LINE_PAY.name().equals(orderInfoModel.getStatus()))){
			newOrderInfoModel.setStatus(OrderStatusEnum.WAITING_PULL_MATERIAL.name());
			newOrderInfoModel.setPayFlag(true);
			updateFlag = true;
		}
		
		//CANCELLED("EBS订单，已被取消"),商城订单分为商户取消和退款
		if(EbsOrderStatusEnum.CANCELLED.name().equals(integrationTimeInfoDtoReq.getEbsOrderStatus())
				&& !OrderStatusEnum.SHOP_REPEAL.name().equals(orderInfoModel.getStatus())){
			//如果是待支付前状态取消，则为普通取消;如果是已支付后的状态取消，则为退款
			if(!orderInfoModel.getPayFlag()){
				newOrderInfoModel.setStatus(OrderStatusEnum.SHOP_REPEAL.name());
				newOrderInfoModel.setCloseTime(new Date());
				
				//消息参数
				smsSendDtoReq = new SmsSendDtoReq();
				smsSendDtoReq.setAuthCode(false);
				smsSendDtoReq.setMobile(orderInfoModel.getBuyerMobile());
				smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151772083.name());
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("orderNo", orderInfoModel.getOrderNo());
				paramMap.put("tel", orderInfoModel.getUcShopMobile());
				smsSendDtoReq.setParamMap(paramMap);
				
				wechatMsgDtoReq = new WechatMsgDtoReq();
				List<String> params = new ArrayList<>();
				params.add(orderInfoModel.getOrderNo());
				params.add(orderInfoModel.getUcShopMobile());
				wechatMsgDtoReq.setParams(params);
				String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfoModel.getId() + "&pre_path=_static_html_order_orderList";
				wechatMsgDtoReq.setUrl(url);
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.ORDER_CLOSE);
			}else{
				newOrderInfoModel.setStatus(OrderStatusEnum.REFUNDED.name());
				newOrderInfoModel.setCloseTime(new Date());
				
				smsSendDtoReq = new SmsSendDtoReq();
				smsSendDtoReq.setAuthCode(false);
				smsSendDtoReq.setMobile(orderInfoModel.getBuyerMobile());
				smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151767110.name());
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("orderNo", orderInfoModel.getOrderNo());
				paramMap.put("amount", String.valueOf(orderInfoModel.getTotalPayAmount().setScale(2, BigDecimal.ROUND_HALF_UP)));
				paramMap.put("tel", orderInfoModel.getUcShopMobile());
				smsSendDtoReq.setParamMap(paramMap);
				
				wechatMsgDtoReq = new WechatMsgDtoReq();
				List<String> params = new ArrayList<>();
				params.add(orderInfoModel.getOrderNo());
				params.add(String.valueOf(orderInfoModel.getTotalPayAmount().setScale(2, BigDecimal.ROUND_HALF_UP)));
				params.add(orderInfoModel.getUcShopMobile());
				wechatMsgDtoReq.setParams(params);
				String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfoModel.getId() + "&pre_path=_static_html_order_orderList";
				wechatMsgDtoReq.setUrl(url);
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.REIMBURSE_SUCCESS);
			}
			updateFlag = true;
		}
		if(EbsOrderStatusEnum.CLOSED.name().equals(integrationTimeInfoDtoReq.getEbsOrderStatus())
				&& !OrderStatusEnum.COMPLETED.name().equals(orderInfoModel.getStatus())){
			newOrderInfoModel.setStatus(OrderStatusEnum.COMPLETED.name());
			newOrderInfoModel.setEndTime(new Date());
			
			smsSendDtoReq = new SmsSendDtoReq();
			smsSendDtoReq.setAuthCode(false);
			smsSendDtoReq.setMobile(orderInfoModel.getBuyerMobile());
			smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151772067.name());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orderNo", orderInfoModel.getOrderNo());
			paramMap.put("shopName", orderInfoModel.getUcShopName());
			paramMap.put("weight", String.valueOf((orderInfoModel.getTotalFeedWeight().add(presentWeight)).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)));
			paramMap.put("carNo", orderFeedTransportModel.getEbsCarNo() != null ? orderFeedTransportModel.getEbsCarNo() : orderFeedTransportModel.getCarNo());
			smsSendDtoReq.setParamMap(paramMap);
			
			wechatMsgDtoReq = new WechatMsgDtoReq();
			List<String> params = new ArrayList<>();
			params.add(orderInfoModel.getOrderNo());
			params.add(String.valueOf(orderInfoModel.getTotalFeedWeight().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)) +"吨");
			params.add(orderFeedTransportModel.getEbsCarNo() != null ? orderFeedTransportModel.getEbsCarNo() : orderFeedTransportModel.getCarNo());
			wechatMsgDtoReq.setParams(params);
			String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfoModel.getId() + "&pre_path=_static_html_order_orderList";
			wechatMsgDtoReq.setUrl(url);
			wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.DRIVER_OUT);
			updateFlag = true;
		}
		
		if(integrationTimeInfoDtoReq.getCloseTime() != null){
			if(orderInfoModel.getEndTime() == null || (integrationTimeInfoDtoReq.getCloseTime().compareTo(orderInfoModel.getEndTime()) != 0)){
				newOrderInfoModel.setEndTime(integrationTimeInfoDtoReq.getCloseTime());
				updateFlag = true;
			}
		}
		if(integrationTimeInfoDtoReq.getCancleTime() != null){
			if(orderInfoModel.getCloseTime() == null || (integrationTimeInfoDtoReq.getCancleTime().compareTo(orderInfoModel.getCloseTime()) != 0)){
				newOrderInfoModel.setCloseTime(integrationTimeInfoDtoReq.getCancleTime());
				updateFlag = true;
			}
		}
		if(integrationTimeInfoDtoReq.getDealTime() != null){
			if(orderInfoModel.getDealTime() == null || (integrationTimeInfoDtoReq.getDealTime().compareTo(orderInfoModel.getDealTime()) != 0)){
				newOrderInfoModel.setDealTime(integrationTimeInfoDtoReq.getDealTime());
				updateFlag = true;
			}
		}
		if(integrationTimeInfoDtoReq.getPlanTransportTime() != null
				&& (integrationTimeInfoDtoReq.getPlanTransportTime().compareTo(orderInfoModel.getPlanTransportTime()) != 0)){
			if(!(DateUtils.date2Str(integrationTimeInfoDtoReq.getPlanTransportTime(), DateUtils.date_sdf).equals(DateUtils.date2Str(orderInfoModel.getPlanTransportTime(), DateUtils.date_sdf)))){
				if(orderInfoModel.getOldPlanTransportTime().getTime() != integrationTimeInfoDtoReq.getPlanTransportTime().getTime()){
					
					newOrderInfoModel.setPlanTransportTime(integrationTimeInfoDtoReq.getPlanTransportTime());
					newOrderInfoModel.setOldPlanTransportTime(orderInfoModel.getTransportTime());
					smsSendDtoReq = new SmsSendDtoReq();
					smsSendDtoReq.setAuthCode(false);
					smsSendDtoReq.setMobile(orderFeedTransportModel.getDriverMobile());
					smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151767099.name());
					Map<String, String> paramMap = new HashMap<>();
					paramMap.put("userName", orderInfoModel.getBuyerName());
					paramMap.put("shopName", orderInfoModel.getUcShopName());
					paramMap.put("transportTime", DateUtils.date_sdf_wz.format(integrationTimeInfoDtoReq.getPlanTransportTime()));
					paramMap.put("weight", String.valueOf((orderInfoModel.getTotalFeedWeight().add(presentWeight)).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)));
					paramMap.put("tel", orderInfoModel.getUcShopMobile());
					smsSendDtoReq.setParamMap(paramMap);
					
					wechatMsgDtoReq = new WechatMsgDtoReq();
					List<String> params = new ArrayList<>();
					params.add(orderInfoModel.getOrderNo());
					params.add(DateUtils.date2Str(integrationTimeInfoDtoReq.getPlanTransportTime(), DateUtils.date_sdf));
					wechatMsgDtoReq.setParams(params);
					String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderInfoModel.getId() + "&pre_path=_static_html_order_orderList";
					wechatMsgDtoReq.setUrl(url);
					wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.TRANSPORT_TIME_MODIFY);
					
					updateFlag = true;
				}
			}
		}
		
		//如果有需要修改的数据则修改
		if(updateFlag){
			logger.info("[OrderIntegrationServiceImpl.reciveIntegrationTimeInfo]修改单条订单主数据" + JSON.toJSONString(newOrderInfoModel));
			//修改订单主表状态和时间
			orderService.update(oldOrderInfoModel, newOrderInfoModel);
			comment.append("改订单" +"-");
		}
				
		if(StringUtils.isNotEmpty(integrationTimeInfoDtoReq.getCarNo())){
			//如果有司机信息的事件，修改拉料信息表
			if(!integrationTimeInfoDtoReq.getCarNo().equals(listOrderFeedTransportModel.get(0).getEbsCarNo())){
				logger.info("[OrderIntegrationServiceImpl.reciveIntegrationTimeInfo]修改EBS车牌号" + integrationTimeInfoDtoReq.getCarNo());
				OrderFeedTransportModel oldOrderFeedTransportModel = new OrderFeedTransportModel();
				oldOrderFeedTransportModel.setId(listOrderFeedTransportModel.get(0).getId());
				OrderFeedTransportModel newOrderFeedTransportModel = new OrderFeedTransportModel();
				newOrderFeedTransportModel.setEbsCarNo(integrationTimeInfoDtoReq.getCarNo());
				orderFeedTransportService.update(oldOrderFeedTransportModel, newOrderFeedTransportModel);
				
				comment.append("改拉料" +"-");
				updateFlag = true;
			}
		}
		
		//发送消息
		if(smsSendDtoReq != null){
			smsFeignClient.sendSms(smsSendDtoReq);
		}
		if(wechatMsgDtoReq != null){
			//查询用户微信OPENID
			BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient.queryUserThirdAccount(orderInfoModel.getBuyerId(), null, AppSourceEnums.WECHAT.name());
			if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
				wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
				wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
			}
		}
		
		if(updateFlag){
			logger.info("[OrderIntegrationServiceImpl.reciveIntegrationTimeInfo]传入的数据是" + JSON.toJSONString(integrationTimeInfoDtoReq));
			addUcLog(orderInfoModel, comment, UserOperationEnums.INT_UPDATE_EBS_ORDER_INFO);
		}
		
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		return dtoResult;
	}
	
	@Override
	@Transactional
	public DtoResult reciveIntegrationOrderInfo(IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq) {
		logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]进入修改订单物料数据" + JSON.toJSONString(integrationTimeInfoDtoReq));
		DtoResult dtoResult = new DtoResult();
		if(integrationTimeInfoDtoReq.getMoneyfeedOrderId() == null){
			logger.error("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo请求数据商城订单ID为空]");
			dtoResult.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return dtoResult;
		}
		//记录日志的备注说明
		StringBuffer comment = new StringBuffer("接收中台推送的修物料信息--");
		//查询订单
		OrderInfoModel orderInfoModel = new OrderInfoModel();
		orderInfoModel.setId(Long.valueOf(integrationTimeInfoDtoReq.getMoneyfeedOrderId()));
		orderInfoModel = orderService.query(orderInfoModel).get(0);
		
		//商城订单修改新老对象
		OrderInfoModel oldOrderInfoModel = new OrderInfoModel();
		oldOrderInfoModel.setId(Long.valueOf(integrationTimeInfoDtoReq.getMoneyfeedOrderId()));
		OrderInfoModel newOrderInfoModel = new OrderInfoModel();		
		
		//订单主表的金额初始都为0，原价，支付价，折扣价，重量
		BigDecimal totalOrderOrginalAmount = new BigDecimal(0);
		BigDecimal totalOrderPayAmount = new BigDecimal(0);
		BigDecimal totalOrderDiscountAmount = new BigDecimal(0);
		BigDecimal totalOrderFeedWeight = BigDecimal.ZERO;

		//清掉订单详情，订单快照，赠品表，然后重新插入
		logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]清掉订单详情，订单快照，赠品表");
		OrderDetailModel orderDetailModel = new OrderDetailModel();
		orderDetailModel.setOrderId(orderInfoModel.getId());
		orderDetailService.remove(orderDetailModel);
		
		OrderSnapshotModel orderSnapshotModel = new OrderSnapshotModel();
		orderSnapshotModel.setOrderId(orderInfoModel.getId());
		orderSnapshotService.remove(orderSnapshotModel);
		
		OrderPresentFeedModel orderPresentFeedModel = new OrderPresentFeedModel();
		orderPresentFeedModel.setOrderId(orderInfoModel.getId());
		orderPresentFeedService.remove(orderPresentFeedModel);
		
		//EBS物料列表
		List<OrderMaterialInfoDtoResult> materials = integrationTimeInfoDtoReq.getMaterials();
		if (CollectionUtils.isEmpty(materials)) {
			logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]修改订单物料数据为空，不用修改" + JSON.toJSONString(integrationTimeInfoDtoReq));
			dtoResult.setCode(ResultCode.SUCCESS.getCode());
			dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
			return dtoResult;
		}
		//赠品列表
		List<PresentFeedDtoReq> presentFeedList = new ArrayList<>();
		//迭代物料信息
		for (OrderMaterialInfoDtoResult orderMaterialInfoDtoResult : materials) {
			if (EbsOrderProductTypeEnum.PRESENT_PRODUCT.name().equals(orderMaterialInfoDtoResult.getItemType())) {
				PresentFeedDtoReq presentFeedDtoReq = new PresentFeedDtoReq();
				presentFeedDtoReq.setPresentFeedCount(orderMaterialInfoDtoResult.getQuantity().intValue());
				presentFeedDtoReq.setSuppliesCode(orderMaterialInfoDtoResult.getMaterielNo());
				presentFeedDtoReq.setUnit(orderMaterialInfoDtoResult.getUnit());
				presentFeedList.add(presentFeedDtoReq);
				continue;
			}
			List<Long> skuids = new ArrayList<>();
			Map<Long, Integer> skuCountMap = Maps.newHashMap();

			List<String> suppliesCodeList = new ArrayList<>();
			suppliesCodeList.add(orderMaterialInfoDtoResult.getMaterielNo());
			//根据返回物料code查询物料信息
			ProductEbsQueryDtoReq productEbsQueryDtoReq = new ProductEbsQueryDtoReq();
			productEbsQueryDtoReq.setSuppliesCodes(suppliesCodeList);
			productEbsQueryDtoReq.setOrgId(orderMaterialInfoDtoResult.getOrgId());
			BaseResponse<ProductSkuListDtoResult> productResponse = productFeignClient.getEbsProduct(productEbsQueryDtoReq);

			if (!productResponse.isSuccess() || CollectionUtils.isEmpty(productResponse.getData().getProductSkuList())) {
				logger.error("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]修改物料时没有查询出商品SKU,查询参数：" + JSON.toJSONString(productEbsQueryDtoReq));
				throw new BizException(ResultCode.OC_ORDER_GOODS_QUERY_ERROR);
			}
			
			//订单详情和快照对象
			OrderDetailModel orderDetail = new OrderDetailModel();
            OrderSnapshotModel snapshot = new OrderSnapshotModel();
            /**商品快照数据*/
	        List<OrderSnapshotModel> snapshots = new ArrayList<>();
	        //商品详情数据
	        List<OrderDetailModel> orderDetails = new ArrayList<>();
	        
			//订单详情的重量，厂单价
			BigDecimal quantity = orderMaterialInfoDtoResult.getQuantity();
			BigDecimal planAmount = orderMaterialInfoDtoResult.getPlanAmount();
			//订单详情的总厂价，总支付价，总折扣价，数量
			BigDecimal totalDetailEbsPlanAmount = planAmount.multiply(quantity);
			BigDecimal realDetailEbsPayAmount = orderMaterialInfoDtoResult.getRealPayAmount();
			BigDecimal discountDetailEbsAmount = totalDetailEbsPlanAmount.subtract(realDetailEbsPayAmount);
			int count = (orderMaterialInfoDtoResult.getQuantity().divide(orderMaterialInfoDtoResult.getFactor()).intValue());
			
			//订单主表的钱
			totalOrderOrginalAmount = totalOrderOrginalAmount.add(totalDetailEbsPlanAmount);
			totalOrderPayAmount = totalOrderPayAmount.add(realDetailEbsPayAmount);
			totalOrderDiscountAmount = totalOrderOrginalAmount.subtract(totalOrderPayAmount);
			totalOrderFeedWeight = totalOrderFeedWeight.add(quantity);
			
	        //查询商品信息
	        skuids.add(productResponse.getData().getProductSkuList().get(0).getProductSkuId());
            skuCountMap.put(productResponse.getData().getProductSkuList().get(0).getProductSkuId(), orderMaterialInfoDtoResult.getQuantity().intValue());
	        ProductQueryDtoReq productQueryDtoReq = new ProductQueryDtoReq();
	        productQueryDtoReq.setProductSkuIds(skuids);
	        productQueryDtoReq.setShopId(orderInfoModel.getUcShopId());
	        productQueryDtoReq.setVerify(Boolean.TRUE);
        
	        BaseResponse<ProductSkuListDtoResult> skuResponse = productFeignClient.getProduct(productQueryDtoReq);
	        ProductSkuListDtoResult skuDtoResult = skuResponse.getData();
	        if (!ResultCode.SUCCESS.getCode().equals(skuResponse.getCode()) || skuDtoResult == null) {
	        	logger.error("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]物料查询商品出错" + JSON.toJSONString(skuDtoResult));
	            throw new BizException(ResultCode.OC_ORDER_GOODS_QUERY_ERROR);
	        }
	        if (CollectionUtils.isEmpty(skuDtoResult.getProductSkuList())) {
	        	logger.error("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]物料查询商品出错" + JSON.toJSONString(skuDtoResult));
	            logger.error(String.format(ResultCode.OC_ORDER_GOODS_QUERY_ERROR.getDesc() + ":{skuids:{%s}}", JSON.toJSONString(skuids)));
	            throw new BizException(ResultCode.OC_ORDER_GOODS_QUERY_ERROR);
	        }

	        ProductSkuDtoResult skuDto = skuDtoResult.getProductSkuList().get(0);
            /**构建商品详情数据*/
            BeanUtils.copyProperties(skuDto, orderDetail);
            orderDetail.setOrderNo(orderInfoModel.getOrderNo());
            orderDetail.setUnit(orderMaterialInfoDtoResult.getUnit());
            orderDetail.setTotalDiscountAmount(discountDetailEbsAmount);
            orderDetail.setTotalOrginalAmount(totalDetailEbsPlanAmount);
            orderDetail.setTotalPayAmount(realDetailEbsPayAmount);
            orderDetail.setTotalFeedWeight(quantity);
            orderDetail.setCount(count);
            /**构建商品快照数据*/
            BeanUtils.copyProperties(skuDto, snapshot);
            snapshot.setSkuId(skuDto.getProductSkuId());
            snapshot.setOrderNo(orderInfoModel.getOrderNo());
            snapshot.setQuantity(count);
            snapshot.setShopId(orderInfoModel.getUcShopId());
            snapshot.setPrice(planAmount);

            List<ProductSkuAttributesDtoResult> skuAttrParamsDto = skuDto.getProductSkuAttributesExModels();
            if (CollectionUtils.isNotEmpty(skuAttrParamsDto)) {
                List<SkuAttrParam> skuAttrParams = new ArrayList<SkuAttrParam>(skuAttrParamsDto.size());
                for (ProductSkuAttributesDtoResult skuAttrParamDto : skuAttrParamsDto) {
                    SkuAttrParam skuAttrParam = new SkuAttrParam();
                    BeanUtils.copyProperties(skuAttrParamDto, skuAttrParam);
                    skuAttrParams.add(skuAttrParam);
                    if (weight_cn.equals(skuAttrParamDto.getName())) {
                        orderDetail.setWeightParam(skuAttrParamDto.getParmValue());
                    }
                }
                snapshot.setSkuAttrParams(JSON.toJSONString(skuAttrParams));
            }

            snapshots.add(snapshot);
            orderDetails.add(orderDetail);
            
            /**插入订单详情数据*/
	        if (CollectionUtils.isNotEmpty(orderDetails)) {
	            for (OrderDetailModel detail : orderDetails) {
	                detail.setOrderId(orderInfoModel.getId());
	                detail.setCompanyShortCode(orderInfoModel.getCompanyShortCode());
	            }
	            logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]加入订单物料数据" + JSON.toJSONString(orderDetails));
	            if (!orderDetailService.save(orderDetails)) {
	                logger.error(String.format(ResultCode.OC_ORDER_DETAIL_CREATE_ERROR.getDesc() + ":{%s}"));
	                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
	            }
	        }
	        /**插入商品快照数据*/
	        if (CollectionUtils.isNotEmpty(snapshots)) {
	            for (OrderSnapshotModel snapshot2 : snapshots) {
	                snapshot2.setOrderId(orderInfoModel.getId());
	            }
	            logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]加入订单物料快照数据" + JSON.toJSONString(snapshots));
	            if (!orderSnapshotService.save(snapshots)) {
	                logger.error(String.format(ResultCode.OC_ORDER_SNAPSHOT_CREATE_ERROR.getDesc() + ":{%s}"));
	                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
	            }
	        }
		}
		//增加订单赠料
		if (CollectionUtils.isNotEmpty(presentFeedList)) {
			List<OrderPresentFeedModel> orderPresentFeedList = new ArrayList<>();
			//根据返回物料code查询物料信息
			List<String> suppliesCodeList = presentFeedList.stream().map(PresentFeedDtoReq::getSuppliesCode).collect(Collectors.toList());
			ProductEbsQueryDtoReq ProductEbsQueryDtoReq = new ProductEbsQueryDtoReq();
			ProductEbsQueryDtoReq.setSuppliesCodes(suppliesCodeList);
			ProductEbsQueryDtoReq.setOrgId(orderInfoModel.getOrgId());
			BaseResponse<ProductSkuListDtoResult> productResponse = productFeignClient.getEbsProduct(ProductEbsQueryDtoReq);
			if(!productResponse.isSuccess()){
				logger.error("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]增加赠品时查询商品出错" + String.format(ResultCode.OC_ORDER_SNAPSHOT_CREATE_ERROR.getDesc() + ":{%s}"));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
			}
			List<ProductSkuDtoResult> productSkuList = null;
			if(ResultCode.SUCCESS.getCode().equals(productResponse.getCode())
					&& null != productResponse.getData()){
				productSkuList = productResponse.getData().getProductSkuList();
			}
			
			for (PresentFeedDtoReq presentFeedDtoReq : presentFeedList) {
				OrderPresentFeedModel createOrderPresentFeedModel = new OrderPresentFeedModel();
				createOrderPresentFeedModel.setOrderId(orderInfoModel.getId());
				createOrderPresentFeedModel.setOrderNo(orderInfoModel.getOrderNo());
				createOrderPresentFeedModel.setCount(presentFeedDtoReq.getPresentFeedCount());
				createOrderPresentFeedModel.setUnit(presentFeedDtoReq.getUnit());
				createOrderPresentFeedModel.setSuppliesCode(presentFeedDtoReq.getSuppliesCode());
				//设置物料信息
				if (CollectionUtils.isNotEmpty(productSkuList)) {
					for (ProductSkuDtoResult productSkuDto : productSkuList) {
						if (presentFeedDtoReq.getSuppliesCode().equals(productSkuDto.getSuppliesCode())) {
							createOrderPresentFeedModel.setPcProductId(productSkuDto.getProductId());
							createOrderPresentFeedModel.setProductName(productSkuDto.getProductName());
							createOrderPresentFeedModel.setCompanyId(productSkuDto.getCompanyId());
							createOrderPresentFeedModel.setOrganizationCode(productSkuDto.getOrganizationCode());
							createOrderPresentFeedModel.setCompanyShortCode(productSkuDto.getCompanyShortCode());
							//设置重量信息
							if (CollectionUtils.isNotEmpty(productSkuDto.getProductSkuAttributesExModels())) {
								for (ProductSkuAttributesDtoResult productSkuAttributesDto : productSkuDto.getProductSkuAttributesExModels()) {
									if (weight_cn.equals(productSkuAttributesDto.getName())) {
										createOrderPresentFeedModel.setWeightParam(productSkuAttributesDto.getParmValue());
										break;
									}
								}
							}
							break;
						}
					}
				}
				orderPresentFeedList.add(createOrderPresentFeedModel);
			}
			logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]加入物料订单赠包数据" + JSON.toJSONString(orderPresentFeedList));
			orderPresentFeedService.save(orderPresentFeedList);
		}

		newOrderInfoModel = new OrderInfoModel();
		newOrderInfoModel.setTotalDiscountAmount(totalOrderDiscountAmount);
		newOrderInfoModel.setTotalPayAmount(totalOrderPayAmount);
		newOrderInfoModel.setTotalOrginalAmount(totalOrderOrginalAmount);
		newOrderInfoModel.setTotalFeedWeight(totalOrderFeedWeight);
		logger.info("[OrderIntegrationServiceImpl.reciveIntegrationOrderInfo]修改物料后修改订单主表" + JSON.toJSONString(newOrderInfoModel));
		orderService.update(oldOrderInfoModel, newOrderInfoModel);
		//添加日志
		addUcLog(orderInfoModel, comment, UserOperationEnums.INT_UPDATE_EBS_ORDER_INFO);
		
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		return dtoResult;
	}

	@Override
	public void pullOrderEventInfo() {
		//查询需要从EBS同步数据的订单排除的状态
		List<String> statuses = new ArrayList<String>();
		statuses.add(OrderStatusEnum.PAYING.name());
		statuses.add(OrderStatusEnum.CUSTOMER_REPEALING.name());
		statuses.add(OrderStatusEnum.EBS_SYNC_FAILED.name());
		statuses.add(OrderStatusEnum.COMPLETED.name());
		statuses.add(OrderStatusEnum.REFUNDED.name());
		statuses.add(OrderStatusEnum.CUSTOMER_REPEAL.name());
		statuses.add(OrderStatusEnum.SHOP_REPEAL.name());
		statuses.add(OrderStatusEnum.CLOSED.name());
		
		//查询需要拉取信息的订单
		OrderInfoQueryDtoReq req = new OrderInfoQueryDtoReq();
		req.setNotStatusList(statuses);
		req.setPageSize(null);
		PageList<OrderDtoResult> list = orderService.queryOrderInfoList(req);
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		logger.info("[OrderIntegrationService.pullOrderEventInfo]从商城查询出拉取数据订单条数为:" + list.size());
		//组装订单号传递给中台
		List<String> moneyfeedOrderIds = new ArrayList<String>();
		for (OrderDtoResult orderInfoDtoResult : list) {
			moneyfeedOrderIds.add(orderInfoDtoResult.getId().toString());
		}
		
		//从中台查询数据同步本地订单
		QueryEbsOrderDetailInfoDtoReq search = new QueryEbsOrderDetailInfoDtoReq();
		search.setMoneyfeedOrderIds(moneyfeedOrderIds);
		BaseResponse<QueryEbsOrderDetailInfoDtoResult> feignResult = moneyfeedToEbsOrderFeignClient.moneyfeedQueryEbsOrder(search);
		if(feignResult.isSuccess()){
			QueryEbsOrderDetailInfoDtoResult queryEbsOrderDetailInfoDtoResult = feignResult.getData();
			if(CollectionUtils.isNotEmpty(queryEbsOrderDetailInfoDtoResult.getOrderInfos())){
				logger.info("[OrderIntegrationService.pullOrderEventInfo]从中台查询出拉取数据订单条数为:" + queryEbsOrderDetailInfoDtoResult.getOrderInfos().size());
//				logger.info("[OrderIntegrationService.pullOrderEventInfo]从商城查询出拉取数据订单数据为:" + JSON.toJSONString(queryEbsOrderDetailInfoDtoResult.getOrderInfos()));
//				AliyunLog.log("[OrderIntegrationService.pullOrderEventInfo]从商城查询出拉取数据订单数据为:" + JSON.toJSONString(queryEbsOrderDetailInfoDtoResult.getOrderInfos()));
				//循环处理查询出的订单数据
				List<QueryEbsOrderDetailInfoItemDtoResult> ebsOrderInfos = queryEbsOrderDetailInfoDtoResult.getOrderInfos();
				for (QueryEbsOrderDetailInfoItemDtoResult queryEbsOrderDetailInfoItemDtoResult : ebsOrderInfos) {
					IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq = new IntegrationTimeInfoDtoReq();
					integrationTimeInfoDtoReq.setCancleTime(queryEbsOrderDetailInfoItemDtoResult.getCancleTime());
					integrationTimeInfoDtoReq.setCarInWeight(queryEbsOrderDetailInfoItemDtoResult.getCarInWeight());
					integrationTimeInfoDtoReq.setCarNo(queryEbsOrderDetailInfoItemDtoResult.getCarNo());
					integrationTimeInfoDtoReq.setCarOutWeight(queryEbsOrderDetailInfoItemDtoResult.getCarOutWeight());
					integrationTimeInfoDtoReq.setCheckinTime(queryEbsOrderDetailInfoItemDtoResult.getCheckinTime());
					integrationTimeInfoDtoReq.setCloseTime(queryEbsOrderDetailInfoItemDtoResult.getCloseTime());
					integrationTimeInfoDtoReq.setDealTime(queryEbsOrderDetailInfoItemDtoResult.getDealTime());
					integrationTimeInfoDtoReq.setEbsOrderStatus(queryEbsOrderDetailInfoItemDtoResult.getEbsOrderStatus());
					integrationTimeInfoDtoReq.setMoneyfeedOrderId(queryEbsOrderDetailInfoItemDtoResult.getMoneyfeedOrderId());
					integrationTimeInfoDtoReq.setPlanTransportTime(queryEbsOrderDetailInfoItemDtoResult.getPlanTransportTime());
					integrationTimeInfoDtoReq.setTransportTime(queryEbsOrderDetailInfoItemDtoResult.getTransportTime());
					integrationTimeInfoDtoReq.setLastUpdateTime(queryEbsOrderDetailInfoItemDtoResult.getLastUpdateTime());
					integrationTimeInfoDtoReq.setEbsOrderNo(queryEbsOrderDetailInfoItemDtoResult.getEbsOrderNo());

					List<OrderMaterialInfoDtoResult> materials = new ArrayList<>();
					if(CollectionUtils.isNotEmpty(queryEbsOrderDetailInfoItemDtoResult.getMaterials())){
						for (QueryEbsOrderMaterialInfoDtoResult queryEbsOrderMaterialInfoDtoResult : queryEbsOrderDetailInfoItemDtoResult.getMaterials()) {
							OrderMaterialInfoDtoResult orderMaterialInfoDtoResult = new OrderMaterialInfoDtoResult();
							BeanUtils.copyProperties(queryEbsOrderMaterialInfoDtoResult, orderMaterialInfoDtoResult);
							materials.add(orderMaterialInfoDtoResult);
						}
						integrationTimeInfoDtoReq.setMaterials(materials);
					}
					try{
						reciveIntegrationTimeInfo(integrationTimeInfoDtoReq);
					}catch(Exception e){
						logger.error("[OrderIntegrationService.pullOrderEventInfo]处理从中台拉取的订单信息出现异常的订单ID是" + integrationTimeInfoDtoReq.getMoneyfeedOrderId());
						logger.error("[OrderIntegrationService.pullOrderEventInfo]处理事件订单信息出现异常", e);
						continue;
					}
				}
			}
		}
	}
	
	/**  
	* @Title: addUcLog  
	* @Description: 添加统一日志
	*/
	private void addUcLog(OrderInfoModel orderInfoModel, StringBuffer comment, UserOperationEnums userOperationEnums) {
		OrderInfoModel newOrderInfoModel;
		//记录日志
		newOrderInfoModel = new OrderInfoModel();
		newOrderInfoModel.setId(orderInfoModel.getId());
		newOrderInfoModel = orderService.query(newOrderInfoModel).get(0);
		UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
		userOperationLogDtoReq.setAfterEventAmount(newOrderInfoModel.getTotalPayAmount());
		userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(newOrderInfoModel));
		userOperationLogDtoReq.setAfterEventStatus(newOrderInfoModel.getStatus());
		userOperationLogDtoReq.setBeforeEventAmount(orderInfoModel.getTotalPayAmount());
		userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(orderInfoModel));
		userOperationLogDtoReq.setBeforeEventStatus(orderInfoModel.getStatus());
		userOperationLogDtoReq.setComment(comment.toString());
		userOperationLogDtoReq.setEbsOrderId(newOrderInfoModel.getEbsOrderNo());
		userOperationLogDtoReq.setEventDate(new Date());
		userOperationLogDtoReq.setEventId(orderInfoModel.getId());
		userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
		userOperationLogDtoReq.setOrderId(orderInfoModel.getId());
		userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
		userOperationLogDtoReq.setUserOperationEnums(userOperationEnums);
		userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
		
	}

	@Override
	public DtoResult reciveNotifyEbsPay(ReciveNotifyEbsPayDtoReq dtoReq) {
		//银行卡支付
		if (PayTypeEnums.CARD_PAY.name().equals(dtoReq.getPayType())) {
			BaseResponse<DtoResult> feginDtoResult = payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
		}
		//订单充值
		else if (PayTypeEnums.SO_PAY.name().equals(dtoReq.getPayType())) {
			BaseResponse<DtoResult> feginDtoResult = payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
		}
		//账户充值
		else if (PayTypeEnums.ACC_PAY.name().equals(dtoReq.getPayType())) {
			String s = "";
			BaseResponse<DtoResult> feginDtoResult = payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
		}else {
			logger.error("--支付返回结果未找到匹配类型---");
		}
		return null;
	}

	@Override
	public OrderValidateRepositoryDtoResult validateRepository(Long orderId) {
		OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = new OrderValidateRepositoryDtoResult();
		if (orderId == null) {
			logger.error("[OrderIntegrationServiceImpl.validateRepository]订单ID没有传");
			orderValidateRepositoryDtoResult.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			orderValidateRepositoryDtoResult.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return orderValidateRepositoryDtoResult;
		}
		
		//查询订单物料明细
		OrderDetailModel orderDetailModel = new OrderDetailModel();
		orderDetailModel.setOrderId(orderId);
		List<OrderDetailModel> orderDetailModels = orderDetailService.query(orderDetailModel);
		List<EbsItemOnhandDtoReq> reqList = new ArrayList<>();
		for (OrderDetailModel orderDetailModel2 : orderDetailModels) {
			EbsItemOnhandDtoReq ebsItemOnhandDtoReq = new EbsItemOnhandDtoReq();
			ebsItemOnhandDtoReq.setItemCode(orderDetailModel2.getSuppliesCode());
			ebsItemOnhandDtoReq.setOrganizationCode(orderDetailModel2.getOrganizationCode());
			reqList.add(ebsItemOnhandDtoReq);
		}
		
		//调用中台查询物料信息
    	logger.info("[OrderIntegrationServiceImpl.validateRepository]查询EBS库存的参数" + JSON.toJSONString(reqList));
		BaseResponse<EbsItemOnhandDtoResult> feginEbsItemOnhandDtoResult = ebsInvAPIFeignClient.ebsInvItemOnhandQuery(reqList);
		logger.info("[OrderIntegrationServiceImpl.validateRepository]EBS返回库存的结果" + JSON.toJSONString(feginEbsItemOnhandDtoResult));
		
		Map<String,Integer> calcMap = new HashMap<>();
		Map<String,String> nameMap = new HashMap<>();
		if(feginEbsItemOnhandDtoResult.isSuccess() && CollectionUtils.isNotEmpty(feginEbsItemOnhandDtoResult.getData().getDataList())){
			List<EbsItemOnhandDto> list = feginEbsItemOnhandDtoResult.getData().getDataList();
			for (EbsItemOnhandDto ebsItemOnhandDto : list) {
				String key = ebsItemOnhandDto.getItemCode() + ebsItemOnhandDto.getOrganizationCode();
				if(calcMap.containsKey(key)){
					Integer count = calcMap.get(key);
					count = count + ebsItemOnhandDto.getReservableQty().intValue();
					calcMap.put(key, count);
					nameMap.put(key, ebsItemOnhandDto.getItemDesc());
				}else{
					calcMap.put(key, ebsItemOnhandDto.getReservableQty().intValue());
					nameMap.put(key, ebsItemOnhandDto.getItemDesc());
				}
			}
		}else{
			logger.error("[OrderIntegrationServiceImpl.validateRepository]查询库存出错");
			orderValidateRepositoryDtoResult.setCode(ResultCode.OC_ORDER_QUERY_REPOSITORY_ERROR.getCode());
			orderValidateRepositoryDtoResult.setMsg(ResultCode.OC_ORDER_QUERY_REPOSITORY_ERROR.getDesc());
			return orderValidateRepositoryDtoResult;
		}
		
		//比较物料库存是否够
		List<String> materialName = new ArrayList<>();
		for (OrderDetailModel orderDetailModel2 : orderDetailModels) {
			String key = orderDetailModel2.getSuppliesCode() + orderDetailModel2.getOrganizationCode();
			if(calcMap.containsKey(key)){
				Integer count = calcMap.get(key);
				if(orderDetailModel2.getCount().intValue() > count.intValue()){
					materialName.add(nameMap.get(key));
				}
			}else{
				materialName.add(nameMap.get(key));
			}
		}
		if(CollectionUtils.isNotEmpty(materialName)){
			logger.error("[OrderIntegrationServiceImpl.validateRepository]物料不足" + JSON.toJSONString(materialName));
			orderValidateRepositoryDtoResult.setRepositoryLowFlag(true);
			orderValidateRepositoryDtoResult.setMaterialName(materialName);
		}else{
			orderValidateRepositoryDtoResult.setRepositoryLowFlag(false);
		}
		orderValidateRepositoryDtoResult.setCode(ResultCode.SUCCESS.getCode());
		return orderValidateRepositoryDtoResult;
	}
	
}






