package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsUpdateOrderInfoThread;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsItemsModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderLogModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsRechargeModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderUpdateInfoDtoReq;
import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.enums.common.MQSendToClientEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderProductTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderStatusEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsSpecialOperationEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.common.SendMQCommonModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderFromEbsItemsModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderFromEbsModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderLogModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderPaymentModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderRechargeModelBuilder;
import com.newhope.moneyfeed.integration.api.service.ebs.baseData.EbsCompanyService;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsToMoneyfeedOrderService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderCancelReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderUpdateReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.LockWarehouseReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult.EbsOrderForCreateForResultReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult.EbsOrderForPayOrChargeForResultReq;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs.EbsResponseUpdateSimple;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EBSResponseLockWarehouseResult;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponseCancelResult;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponseCancelResultList;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponseCreateResult;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponsePayOrChargeResult;
import com.newhope.moneyfeed.integration.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.MoneyFeedOrderCenterService;
import com.newhope.moneyfeed.integration.biz.service.common.SendMQService;
import com.newhope.moneyfeed.integration.biz.service.common.TransferWithEbsServiceImpl;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.PresentFeedDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ProductDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsResultDtoReq;

/**
 * Created by yuyanlin on 2018/11/21
 */
@Service
public class EbsToMoneyfeedOrderServiceImpl implements EbsToMoneyfeedOrderService {

	private final Logger logger = LoggerFactory.getLogger(EbsToMoneyfeedOrderServiceImpl.class);

	@Autowired
	private EbsOrderServiceImpl ebsOrderService;

	@Autowired
	private EbsOrderDetailServiceImpl ebsOrderDetailService;

	@Autowired
	private EbsOrderFromEbsServiceImpl ebsOrderFromEbsService;

	@Autowired
	private EbsOrderFromEbsItemsServiceImpl ebsOrderFromEbsItemsService;

	@Autowired
	private MoneyFeedOrderCenterService moneyFeedOrderCenterService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private EbsOrderLogServiceImpl ebsOrderLogService;

	@Autowired
	private TransferWithEbsServiceImpl transferWithEbsService;

	@Autowired
	private EbsCompanyService ebsCompanyService;

	@Autowired
	private OrderFeignClient orderFeignClient;

	@Autowired
	private EbsOrderFromSaleServiceImpl ebsOrderFromSaleService;

	@Autowired
	private SendMQService mqService;

	@Autowired
	private EbsOrderPaymentServiceImpl ebsOrderPaymentService;

	@Autowired
	private EbsRechargeServiceImpl ebsRechargeService;

	@Override
	public DtoResult ebsToMoneyfeedOrdersCreateResult() {
		DtoResult dtoResult = commonService.getSuccessDtoResult();

		List<EbsOrderModel> creatingOrderList = ebsOrderService.getCreatingEbsOrderList();

		if (CollectionUtils.isEmpty(creatingOrderList)) {
			return dtoResult;
		}

		Map<String, List<String>> orgIdMoneyfeedIdListMap = ebsOrderService
				.getOrgIdMoneyfeedIdListMapFromEbsOrderList(creatingOrderList);

		for (Map.Entry<String, List<String>> entry : orgIdMoneyfeedIdListMap.entrySet()) {
			List<EbsResponseCreateResult> createResultList = Lists.newArrayList();

			List<EbsOrderForCreateForResultReq> reqList = convertMoneyfeedOrderIdListToCreateForResultReqList(
					entry.getKey(), entry.getValue());

			try {
				createResultList = transferWithEbsService.getEbsOrderCreateResultList(reqList);
			} catch (Exception e) {
				logger.error("请求EBS系统订单创建结果报错: " + e.getMessage());

				dtoResult.setCode(ResultCode.FAIL.getCode());

				return dtoResult;
			}

			List<ReceiveOrderCreateInfoDtoReq> dtoReqList = converseResponseListToDtoReqList(createResultList);

			for (ReceiveOrderCreateInfoDtoReq dtoReq : dtoReqList) {
				operateOneCreateResult(dtoReq);
			}
		}

		return dtoResult;
	}

	private List<EbsOrderForCreateForResultReq> convertMoneyfeedOrderIdListToCreateForResultReqList(String orgId,
			List<String> moneyfeedOrderIdListList) {
		List<EbsOrderForCreateForResultReq> reqList = Lists.newArrayList();

		EbsCompanyModel company = ebsCompanyService.queryEbsCompanyByOrgId(orgId);

		for (String moneyfeedOrderId : moneyfeedOrderIdListList) {
			EbsOrderForCreateForResultReq req = new EbsOrderForCreateForResultReq();
			req.setCompanyShortCode(company.getShortCode());
			req.setMoneyfeedOrderId(moneyfeedOrderId);

			reqList.add(req);
		}

		return reqList;
	}

	/**
	 * 返回的订单有三种结果 1、已录入、有价格， --------创建成功，直接返回给商城 2、已录入、无价格，
	 * --------创建中，正在人工核价中，只更新ebs_no，下次继续获取结果处理 3、已取消、无价格， --------创建失败，直接返回给商城
	 * 4、被删除，人工核价中， -------创建失败，直接返回给商城
	 * 
	 * @return
	 */
	private List<ReceiveOrderCreateInfoDtoReq> converseResponseListToDtoReqList(
			List<EbsResponseCreateResult> responseList) {

		Map<String, ReceiveOrderCreateInfoDtoReq> dtoMoneyfeedIdReqMap = Maps.newHashMap();

		List<ReceiveOrderCreateInfoDtoReq> createdHaveResultDtoList = Lists.newArrayList();

		for (EbsResponseCreateResult response : responseList) {
			if (null == dtoMoneyfeedIdReqMap.get(response.getMoneyfeedOrderId())) {
				ReceiveOrderCreateInfoDtoReq dtoReq = new ReceiveOrderCreateInfoDtoReq();

				dtoReq.setEbsorderNo(response.getOrderNumber());
				dtoReq.setMoneyfeedOrderId(response.getMoneyfeedOrderId());
				dtoReq.setCompanyShortCode(response.getCompanyShortCode());
				dtoReq.setTotalDiscountAmount(response.getTotalDiscountAmount());
				dtoReq.setTotalOrginalAmount(response.getTotalOrginalAmount());
				dtoReq.setTotalPayAmount(response.getTotalPayAmount());

				if (EbsOrderStatusEnum.ENTERED.name().equals(response.getStatus())
						&& null != dtoReq.getTotalPayAmount()) {
					dtoReq.setResult(true);

					createdHaveResultDtoList.add(dtoReq);
				} else if (EbsOrderStatusEnum.ENTERED.name().equals(response.getStatus())
						&& null == dtoReq.getTotalPayAmount()) {
					logger.info("订单正在人工核价中，商城订单号：" + response.getMoneyfeedOrderId());

					// 人工核价中的订单，只更新ebs_no
					EbsOrderExModel ebsOrderWithDetail = ebsOrderService
							.getEbsOrderWithDetailByMoneyfeedOrderId(response.getMoneyfeedOrderId());

					EbsOrderModel newEbsOrder = new EbsOrderModel();
					newEbsOrder.setEbsOrderNo(response.getOrderNumber());

					ebsOrderService.update(ebsOrderWithDetail, newEbsOrder);
				} else if (EbsOrderStatusEnum.CANCELLED.name().equals(response.getStatus())) {
					logger.info("订单已经在EBS中被取消，商城订单号：" + response.getMoneyfeedOrderId());

					dtoReq.setResult(false);
					dtoReq.setRemark(EbsSpecialOperationEnum.CANCEL_ORDER_IN_MANUAL_PRICE.getDesc());

					createdHaveResultDtoList.add(dtoReq);
				} else {
					logger.info("订单在EBS中业务异常，商城订单号：" + response.getMoneyfeedOrderId() + "， EBS订单状态: "
							+ response.getStatus());

					dtoReq.setResult(false);
					dtoReq.setRemark(response.getReturnMsg());

					createdHaveResultDtoList.add(dtoReq);
				}

				dtoReq.setProductList(Lists.newArrayList());
				dtoReq.setPresentFeedList(Lists.newArrayList());

				dtoMoneyfeedIdReqMap.put(dtoReq.getMoneyfeedOrderId(), dtoReq);
			}
		}

		for (EbsResponseCreateResult response : responseList) {
			if (null == response.getUnitSellingPrice()
					|| 0 == BigDecimal.ZERO.compareTo(response.getUnitSellingPrice())) {
				PresentFeedDtoReq presentFeedDtoReq = new PresentFeedDtoReq();

				presentFeedDtoReq.setPresentFeedCount(response.getProductCount().intValue());
				presentFeedDtoReq.setSuppliesCode(response.getItemCode());
				presentFeedDtoReq.setUnit(response.getUnit());

				dtoMoneyfeedIdReqMap.get(response.getMoneyfeedOrderId()).getPresentFeedList().add(presentFeedDtoReq);
			} else {
				ProductDtoReq productDtoReq = new ProductDtoReq();

				productDtoReq.setOrganizationCode(response.getOrganizationCode());
				productDtoReq.setProductCount(response.getProductCount());
				productDtoReq.setSuppliesCode(response.getItemCode());
				productDtoReq.setTotalDiscountAmount(response.getUnitSellingPrice());
				productDtoReq.setTotalOrginalAmount(response.getUnitListPrice());
				productDtoReq.setTotalPayAmount(response.getExtendedPrice());
				productDtoReq.setUnit(response.getUnit());

				dtoMoneyfeedIdReqMap.get(response.getMoneyfeedOrderId()).getProductList().add(productDtoReq);
			}
		}

		return createdHaveResultDtoList;
	}

	/**
	 * 处理每一个创建成功的订单，创建成功具体指的是订单里面有订单总价
	 *
	 * @param dtoReq
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean operateOneCreateResult(ReceiveOrderCreateInfoDtoReq dtoReq) {
		logger.info("处理每一个有结果的创建结果：" + JSONObject.toJSONString(dtoReq));

		try {
			// 更新中间表
			EbsOrderExModel ebsOrderWithDetail = ebsOrderService
					.getEbsOrderWithDetailByMoneyfeedOrderId(dtoReq.getMoneyfeedOrderId());

			EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();

			EbsOrderFromEbsModel ebsOrderFromEbs = new EbsOrderFromEbsModel();

			if (dtoReq.isResult()) {
				// ebs order
				ebsOrderBuilder.orderCreateStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name())
						.ebsOrderNo(dtoReq.getEbsorderNo());
				ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());

				// ebs order detail
				EbsOrderDetailModelBuilder ebsOrderDetailBuilder = EbsOrderDetailModelBuilder.anEbsOrderDetailModel();
				ebsOrderDetailBuilder.ebsOrderStatus(EbsOrderStatusEnum.ENTERED.name()).orderCreateDate(new Date());
				ebsOrderDetailService.update(ebsOrderWithDetail.getEbsOrderDetail(), ebsOrderDetailBuilder.build());

				// ebs order from ebs
				EbsOrderFromEbsModelBuilder ebsOrderFromEbsBuilder = EbsOrderFromEbsModelBuilder
						.anEbsOrderFromEbsModel();
				ebsOrderFromEbsBuilder.orderId(ebsOrderWithDetail.getId())
						.planTotalAmount(dtoReq.getTotalOrginalAmount()).realPayAmount(dtoReq.getTotalPayAmount())
						.discountAmount(dtoReq.getTotalDiscountAmount()).ebsOrderJosn(JSON.toJSONString(dtoReq));

				ebsOrderFromEbs = ebsOrderFromEbsBuilder.build();

				ebsOrderFromEbsService.save(Lists.newArrayList(ebsOrderFromEbs));

				// ebs order from ebs items
				List<EbsOrderFromEbsItemsModel> ebsOrderFromEbsItemsList = Lists.newArrayList();
				for (ProductDtoReq product : dtoReq.getProductList()) {
					EbsOrderFromEbsItemsModelBuilder itemsBuilder = EbsOrderFromEbsItemsModelBuilder
							.anEbsOrderFromEbsItemsModel();
					itemsBuilder.orderId(ebsOrderWithDetail.getId()).orderFromEbsId(ebsOrderFromEbs.getId())
							.materielNo(product.getSuppliesCode()).quantity(product.getProductCount())
							.planAmount(product.getTotalOrginalAmount())
							.discountAmount(product.getTotalDiscountAmount()).realPayAmount(product.getTotalPayAmount())
							.unit(product.getUnit()).ebsOrderItemJson(JSON.toJSONString(product))
							.itemType(EbsOrderProductTypeEnum.NORMAL_PRODUCT.name());

					ebsOrderFromEbsItemsList.add(itemsBuilder.build());
				}

				for (PresentFeedDtoReq present : dtoReq.getPresentFeedList()) {
					EbsOrderFromEbsItemsModelBuilder itemsBuilder = EbsOrderFromEbsItemsModelBuilder
							.anEbsOrderFromEbsItemsModel();
					itemsBuilder.orderId(ebsOrderWithDetail.getId()).orderFromEbsId(ebsOrderFromEbs.getId())
							.materielNo(present.getSuppliesCode())
							.quantity(BigDecimal.valueOf(present.getPresentFeedCount())).planAmount(BigDecimal.ZERO)
							.discountAmount(BigDecimal.ZERO).realPayAmount(BigDecimal.ZERO).unit(present.getUnit())
							.ebsOrderItemJson(JSON.toJSONString(present))
							.itemType(EbsOrderProductTypeEnum.PRESENT_PRODUCT.name());

					ebsOrderFromEbsItemsList.add(itemsBuilder.build());
				}

				ebsOrderFromEbsItemsService.save(ebsOrderFromEbsItemsList);
			} else {

				// ebs order
				ebsOrderBuilder.orderCreateStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL.name());

				ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());

			}

			// 记录日志
			commonService.saveFulllog(null, ebsOrderFromEbs.getRealPayAmount(),
					ebsOrderWithDetail.getOrderCreateStatus(), ebsOrderBuilder.build().getOrderCreateStatus(), null,
					dtoReq, ebsOrderWithDetail.getEbsOrderNo(), new Date(),
					Long.valueOf(ebsOrderWithDetail.getSaleOrderId()), null,
					Long.valueOf(ebsOrderWithDetail.getSaleOrderId()), UserOperEventType.ORDER,
					UserOperationEnums.INT_FIND_EBS_CREATE_RESULT,
					"获取EBS系统创建订单结果，商城订单号" + ebsOrderWithDetail.getSaleOrderId() + " , Ebs返回的信息: " + dtoReq.getRemark(),
					UserOperSourceType.MEDIUM_CENTER);

			// 通知商城更新
			Boolean returnToOrderCenterResult = moneyFeedOrderCenterService.returnToOrderCreateInfoNoRecordLog(dtoReq);

			if (!returnToOrderCenterResult) {
				throw new Exception("通知商城订单创建结果失败");
			}
		} catch (Exception e) {
			CommonService.formatExceptionMsg(this.getClass(), e);

			logger.error("通知订单中心订单创建结果失败，商城订单号 " + dtoReq.getMoneyfeedOrderId() + " 原因: " + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			return false;
		}

		return true;
	}

	@Override
	public DtoResult ebsToMoneyfeedRetrySendFailCreateResult() {
		DtoResult result = commonService.getSuccessDtoResult();

		EbsOrderLogModelBuilder ebsOrderLogBuilder = EbsOrderLogModelBuilder.anEbsOrderLogModel();
		ebsOrderLogBuilder.logType(EbsOrderLogTypeEnum.TO_MONEYFEED_SEND_CREATE_FAIL_RESULT_IN_FIRST_TIME.name())
				.operationResult((byte) 0);

		List<EbsOrderLogModel> needRetrySendCreateFailResultEbsOrderLogList = ebsOrderLogService
				.query(ebsOrderLogBuilder.build());

		int needRetryQuantity = 0;

		if (null != needRetrySendCreateFailResultEbsOrderLogList) {
			needRetryQuantity = needRetrySendCreateFailResultEbsOrderLogList.size();
		}

		if (needRetryQuantity > 0) {
			logger.info("开始需要向订单中心重发，创建失败的订单有 " + needRetryQuantity + " 个");
		}

		for (EbsOrderLogModel ebsOrderLog : needRetrySendCreateFailResultEbsOrderLogList) {
			ReceiveOrderCreateInfoDtoReq toMoneyfeedDtoReq = new ReceiveOrderCreateInfoDtoReq();
			toMoneyfeedDtoReq.setMoneyfeedOrderId(ebsOrderLog.getSaleOrderId());
			toMoneyfeedDtoReq.setResult(false);
			toMoneyfeedDtoReq.setRemark("请求EBS系统失败，或者EBS系统中出现业务异常，无法创建订单");

			Boolean orderResult = moneyFeedOrderCenterService.returnToOrderCreateInfoNoRecordLog(toMoneyfeedDtoReq);

			if (orderResult) {
				EbsOrderLogModel newEbsOrderLog = new EbsOrderLogModel();
				newEbsOrderLog.setOperationResult((byte) 1);

				ebsOrderLogService.update(ebsOrderLog, newEbsOrderLog);
			}
		}

		return result;
	}

	@Override
	public DtoResult ebsToMoneyfeedOrdersPayResult() {
		DtoResult dtoResult = commonService.getSuccessDtoResult();

		List<EbsOrderModel> payingEbsOrderList = ebsOrderService.getPayingEbsOrderList();

		if (CollectionUtils.isEmpty(payingEbsOrderList)) {
			return dtoResult;
		}

		List<EbsResponsePayOrChargeResult> payResultList = Lists.newArrayList();

		List<EbsOrderForPayOrChargeForResultReq> payReqList = converseEbsOrderToPayReqList(payingEbsOrderList);
		try {
			payResultList = transferWithEbsService.getEbsOrderPayResultList(payReqList);
		} catch (Exception e) {
			CommonService.formatExceptionMsg(EbsToMoneyfeedOrderServiceImpl.class, e);

			logger.error("请求EBS系统订单创建结果报错: " + e.getMessage());

			dtoResult.setCode(ResultCode.FAIL.getCode());

			return dtoResult;
		}

		for (EbsResponsePayOrChargeResult payResult : payResultList) {
			operateOnePayResult(payResult);
		}

		return dtoResult;
	}

	private List<EbsOrderForPayOrChargeForResultReq> converseEbsOrderToPayReqList(
			List<EbsOrderModel> payingEbsOrderList) {
		List<EbsOrderForPayOrChargeForResultReq> payResultList = Lists.newArrayList();

		if (CollectionUtils.isEmpty(payingEbsOrderList)) {
			return payResultList;
		}

		for (EbsOrderModel ebsOrder : payingEbsOrderList) {
			EbsOrderForPayOrChargeForResultReq req = new EbsOrderForPayOrChargeForResultReq();

			req.setAccNo(ebsOrder.getBankPayNo());
			req.setCustomerNo(ebsOrder.getEbsCustomerNo());
			req.setEbsOrderNo(ebsOrder.getEbsOrderNo());
			req.setPayOrChargeType(ebsOrder.getPayType());
			req.setOrgId(ebsOrder.getEbsOrgId());

			payResultList.add(req);
		}

		return payResultList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean operateOnePayResult(EbsResponsePayOrChargeResult payResult) {
		EbsOrderExModel ebsOrderWithDetail = ebsOrderService
				.getEbsOrderWithDetailByEbsOrderNo(payResult.getEbsOrderNo());

		String ebsResult = payResult.getReturnCode();
		if ("S".equals(ebsResult)) {
			// ebs order
			EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
			ebsOrderBuilder.orderPayStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name());

			ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());
		} else {
			// ebs order
			EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
			ebsOrderBuilder.orderPayStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL.name());

			ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());

			// 删除支付表中的数据
			EbsOrderPaymentModelBuilder paymentModelBuilder = EbsOrderPaymentModelBuilder.anEbsOrderPaymentModel();
			paymentModelBuilder.orderId(ebsOrderWithDetail.getId());

			ebsOrderPaymentService.remove(paymentModelBuilder.build());
		}

		// 记录日志
		String comment = "获取EBS系统支付订单结果，商城订单号" + ebsOrderWithDetail.getSaleOrderId() + " , Ebs返回的信息: "
				+ payResult.getReturnCode() + ", " + payResult.getReturnMsg();

		commonService.saveFulllog(ebsOrderWithDetail.getTotalAmount(), ebsOrderWithDetail.getTotalAmount(),
				ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
				ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(), ebsOrderWithDetail, ebsOrderWithDetail,
				ebsOrderWithDetail.getEbsOrderNo(), new Date(), Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
				ebsOrderWithDetail.getBankPayNo(), Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
				UserOperEventType.ORDER, UserOperationEnums.INT_RECEIVE_PAY_RESULT_OF_EBS, comment,
				UserOperSourceType.MEDIUM_CENTER);

		// 通知商城支付结果
		commonService.sendToOrderPayOrChargeMqResult(payResult.getEbsOrderNo(), payResult.getPayOrChargeType(),
				"S".equals(payResult.getReturnCode()) ? true : false, payResult.getReturnMsg(), payResult.getOrgId(),
				payResult.getMoneyfeedPayNo(), MQMsgKindEnum.pay);

		return true;
	}

	@Override
	public DtoResult ebsToMoneyfeedOrdersCancelResult() {
		DtoResult dtoResult = commonService.getSuccessDtoResult();

		// 查询取消状态为处理中
		List<EbsOrderModel> cancelingEbsOrderList = ebsOrderService.getCancelingEbsOrderList();

		if (CollectionUtils.isEmpty(cancelingEbsOrderList)) {
			return dtoResult;
		}

		Map<String, List<String>> orgIdEbsNoListMap = ebsOrderService
				.getOrgIdEbsOrderNoListMapFromEbsOrderList(cancelingEbsOrderList);

		for (Map.Entry<String, List<String>> entry : orgIdEbsNoListMap.entrySet()) {
			EbsResponseCancelResultList cancelResultList = new EbsResponseCancelResultList();
			// 封装ebs请求对象
			List<EbsOrderCancelReq> reqList = new ArrayList<EbsOrderCancelReq>();
			for (String ebsOrderNo : entry.getValue()) {
				EbsOrderCancelReq dtoReq = new EbsOrderCancelReq();
				dtoReq.setEbsOrderNo(ebsOrderNo);
				dtoReq.setOrg_id(entry.getKey());
				reqList.add(dtoReq);
			}
			try {
				cancelResultList = transferWithEbsService.getEbsOrderCancelResultList(reqList);
				EbsResponseCancelResult ebsResponseCancelResult = new EbsResponseCancelResult();
				if (EBSInterfaceUtil.EBS_SUCCESS
						.equals(cancelResultList.getEbsResponseCancelResult().getReturnCode())) {
					ebsResponseCancelResult.setReturnCode("true");
				} else {
					ebsResponseCancelResult.setReturnCode("false");
				}
				ebsResponseCancelResult.setReturnMsg(cancelResultList.getEbsResponseCancelResult().getReturnMsg());
				cancelResultList.setEbsResponseCancelResult(ebsResponseCancelResult);

			} catch (Exception e) {
				CommonService.formatExceptionMsg(EbsToMoneyfeedOrderServiceImpl.class, e);
				logger.error("取消订单请求EBS接口报错", "reqList：" + JSONObject.toJSONString(reqList) + " , errorMSG：" + e);
				continue;
			}

			if (cancelResultList != null && cancelResultList.getEbsResponseCancelResult() != null) {
				// TODO 每一个订单取消的结果
				for (EbsOrderCancelReq dtoReq : reqList) {
					operateOneCancelResult(dtoReq.getEbsOrderNo(), dtoReq.getOrg_id(), cancelResultList);
				}
			}
		}

		return dtoResult;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean operateOneCancelResult(String ebsOrderNo, String org_id,
			EbsResponseCancelResultList cancelResultList) {
		Boolean ebsResult = Boolean.valueOf(cancelResultList.getEbsResponseCancelResult().getReturnCode());
		// TODO 通过object转为对象获取ebs订单号
		EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(ebsOrderNo);

		EbsCompanyModel queryEbsCompanyByOrgId = ebsCompanyService.queryEbsCompanyByOrgId(org_id);

		// 查询实际支持金额
		BigDecimal realPayAmount = BigDecimal.ZERO;
		EbsOrderFromEbsModel model = new EbsOrderFromEbsModel();
		model.setOrderId(ebsOrderWithDetail.getId());
		List<EbsOrderFromEbsModel> ebsOrderFromEbsModelList = ebsOrderFromEbsService.query(model);
		if (!CollectionUtils.isEmpty(ebsOrderFromEbsModelList)) {
			realPayAmount = ebsOrderFromEbsModelList.get(0).getRealPayAmount();
		}

		CancelOrderResultDtoReq dtoReq = new CancelOrderResultDtoReq();
		dtoReq.setEbsOrderNo(ebsOrderNo);
		dtoReq.setCompanyShortCode(queryEbsCompanyByOrgId.getShortCode());
		dtoReq.setCancelFlag(ebsResult);
		dtoReq.setMsg(cancelResultList.getEbsResponseCancelResult().getReturnMsg());

		EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
		// TODO 更新中间表
		if (ebsResult) {
			// ebs order
			ebsOrderBuilder.orderCancelStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name());

			// 如果订单还在创建中就被取消了，则创建结果直接设置为EBS端业务创建失败
			if (MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderCreateStatus())) {
				ebsOrderBuilder.orderCreateStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL.name());
			}

			ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());

			// ebs order detail
			EbsOrderDetailModelBuilder ebsOrderDetailBuilder = EbsOrderDetailModelBuilder.anEbsOrderDetailModel();
			ebsOrderDetailBuilder.ebsOrderStatus(EbsOrderStatusEnum.CANCELLED.name()).cancelOrderTime(new Date());
			ebsOrderDetailService.update(ebsOrderWithDetail.getEbsOrderDetail(), ebsOrderDetailBuilder.build());

		} else {
			// ebs order
			ebsOrderBuilder.orderCancelStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL.name());

			ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());

		}

		// 记录日志
		commonService.saveFulllog(realPayAmount, BigDecimal.ZERO, ebsOrderWithDetail.getOrderCancelStatus(),
				ebsOrderBuilder.build().getOrderCancelStatus(), JSON.toJSONString(ebsOrderWithDetail),
				JSON.toJSONString(ebsOrderBuilder.build()), ebsOrderWithDetail.getEbsOrderNo(), new Date(),
				ebsOrderWithDetail.getId(), null, Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
				UserOperEventType.ORDER, UserOperationEnums.INT_FIND_EBS_CANCAL_ORDER_RESULT,
				"获取EBS系统取消订单结果，商城订单号" + ebsOrderWithDetail.getSaleOrderId(), UserOperSourceType.MEDIUM_CENTER);

		// 通知商城更新
		try {
			moneyFeedOrderCenterService.returnToOrderCancelInfoNoRecordLog(dtoReq);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return true;
	}

	@Override
	public DtoResult ebsToMoneyfeedOrderLockWarehouseResult(LockWarehouseReq req) {
		DtoResult dtoResult = commonService.getSuccessDtoResult();

		// 1.0 获取EBS锁库成功销售订单
		Date date = new Date();
		req.setRequestDate(DateUtil.getDateStr(date, DateUtil.YYYY_MM_DD));

		List<EBSResponseLockWarehouseResult> ebsOrderList = new ArrayList<EBSResponseLockWarehouseResult>();

		logger.info("*****开始获取EBS锁库成功订单号，公司ID:" + req.getOrgId() + "******需求日期:" + req.getRequestDate());

		try {
			ebsOrderList = transferWithEbsService.getEbsOrderLockResultList(req);
		} catch (Exception e) {
			logger.error("请求EBS接口报错:" + e);

			dtoResult.setCode(ResultCode.FAIL.getCode());
			dtoResult.setMsg(e.getMessage());
			return dtoResult;
		}

		// 2.0获取需要判断是否锁库状态的订单 当天 EBS订单状态为BOOKED 锁库状态为null 或为 1的

		logger.info("******获取中台需要判断锁库状态的订单;公司ID：" + req.getOrgId() + "***计划日期:" + req.getRequestDate());
		List<EbsOrderDetailExModel> lockWarehouseList = ebsOrderDetailService
				.queryLockWarehouseDetailList(req.getOrgId(), date);
		logger.info("******订单数量:" + lockWarehouseList.size());

		// 3.0 处理中台订单锁库状态 锁库成功(false) 锁库失败(true)
		// 3.1 中台锁库状态为 null EBS锁库成功 中台 stock_can_use改成0 否则改成1
		// 3.2 中台锁库状态为1 EBS锁库成功 中台 stock_can_use改成0 否则不变
		if (lockWarehouseList.size() > 0) {
			for (EbsOrderDetailExModel obj : lockWarehouseList) {

				EbsOrderFromEbsItemsModel queryModel = new EbsOrderFromEbsItemsModel();
				queryModel.setOrderId(obj.getOrderId());
				List<EbsOrderFromEbsItemsModel> modelList = ebsOrderFromEbsItemsService.query(queryModel);

				logger.info("******判断EBS锁库成功行数,EBS订单号：" + obj.getEbsOrderNo());
				Boolean lockFlag = false;
				int successCount = 0;
				if (ebsOrderList != null && ebsOrderList.size() > 0) {
					for (EBSResponseLockWarehouseResult lockObj : ebsOrderList) {
						if (lockObj.getEbsOrderNo().equals(obj.getEbsOrderNo())) {
							successCount = successCount + 1;
						}
					}
				}
				logger.info("******EBS订单号：" + obj.getEbsOrderNo() + "；EBS锁库成功行数:" + successCount + ";订单总行数："
						+ modelList.size());

				if (modelList.size() == successCount) {
					lockFlag = true;
				}

				if (null == obj.getStockCanUse() && lockFlag) {
					// 锁库成功
					dtoResult = operateOneLockResult(obj.getId(), false);
					if (!dtoResult.getCode().equals(ResultCode.SUCCESS.getCode())) {
						return dtoResult;
					}
				} else if (null == obj.getStockCanUse() && !lockFlag) {
					// 锁库失败
					dtoResult = operateOneLockResult(obj.getId(), true);
					if (!dtoResult.getCode().equals(ResultCode.SUCCESS.getCode())) {
						return dtoResult;
					}
				} else if (obj.getStockCanUse() && lockFlag) {
					// 锁库成功
					dtoResult = operateOneLockResult(obj.getId(), false);
					if (!dtoResult.getCode().equals(ResultCode.SUCCESS.getCode())) {
						return dtoResult;
					}
				}
			}
		}

		return dtoResult;
	}

	/**
	 * 处理每一个锁库结果
	 *
	 * @param ebsOrderDetailId
	 * @param flag
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public DtoResult operateOneLockResult(Long ebsOrderDetailId, Boolean flag) {
		logger.info("******start**更新锁库状态**ebs_order_detail id:" + ebsOrderDetailId + "**锁库状态:" + String.valueOf(flag));
		DtoResult dtoResult = commonService.getSuccessDtoResult();
		try {
			logger.info("**start**更新中台数据****");
			// 修改中台订单锁库状态
			EbsOrderDetailModel queryModel = new EbsOrderDetailModel();
			queryModel.setId(ebsOrderDetailId);

			EbsOrderDetailModel oldModel = ebsOrderDetailService.query(queryModel).get(0);

			EbsOrderDetailModel newModel = new EbsOrderDetailModel();
			newModel.setStockCanUse(flag);

			ebsOrderDetailService.update(oldModel, newModel);

			newModel = ebsOrderDetailService.query(queryModel).get(0);

			EbsOrderModel orderModel = new EbsOrderModel();
			orderModel.setId(oldModel.getOrderId());
			orderModel = ebsOrderService.query(orderModel).get(0);

			logger.info("****end***更新中台数据****");

			logger.info("****start***记录日志****");

			// 保存日志
			commonService.saveFulllog(orderModel.getTotalAmount(), orderModel.getTotalAmount(),
					String.valueOf(oldModel.getStockCanUse()), String.valueOf(newModel.getStockCanUse()), oldModel,
					newModel, orderModel.getEbsOrderNo(), new Date(), oldModel.getId(), "",
					Long.valueOf(orderModel.getSaleOrderId()), UserOperEventType.ORDER,
					UserOperationEnums.INT_UPDATE_INVENTORY_SUCCESS, "更新订单锁库状态,EBS订单号" + orderModel.getEbsOrderNo(),
					UserOperSourceType.MEDIUM_CENTER);

			logger.info("****end***记录日志****");

			logger.info("****start***通知订单中心 发送MQ****");

			// 通知商城更新 发送MQ
			RepositoryLowDtoReq dtoReq = new RepositoryLowDtoReq();
			dtoReq.setMoneyfeedOrderId(Long.valueOf(orderModel.getSaleOrderId()));
			dtoReq.setBeforeRepositoryLowFlag(oldModel.getStockCanUse());
			dtoReq.setRepositoryLowFlag(flag);

			SendMQCommonModel mqModel = new SendMQCommonModel();
			mqModel.setData(dtoReq);
			mqModel.setMqMsgKind(MQMsgKindEnum.lock_library);
			mqModel.setMqSendToClient(MQSendToClientEnum.order);

			mqService.sendMq(mqModel);

			logger.info("****end***通知订单中心  发送MQ****");
		} catch (Exception e) {
			dtoResult.setCode(ResultCode.FAIL.getCode());
			dtoResult.setMsg(e.getMessage());
		}
		logger.info("******end**更新锁库状态**ebs_order_detail id:" + ebsOrderDetailId + "**锁库状态:" + String.valueOf(flag));
		return dtoResult;
	}

	/**
	 * 重新发送上次取消订单信息而未成功发送到商城结果，发送给商城
	 */
	public DtoResult ebsToMoneyfeedRetrySendFailCancelResult() {
		DtoResult result = commonService.getSuccessDtoResult();

		EbsOrderLogModelBuilder ebsOrderLogBuilder = EbsOrderLogModelBuilder.anEbsOrderLogModel();
		ebsOrderLogBuilder.logType(EbsOrderLogTypeEnum.TO_MONEYFEED_SEND_CANCEL_FAIL_RESULT_IN_FIRST_TIME.name())
				.operationResult((byte) 0);

		List<EbsOrderLogModel> needRetrySendCreateFailResultEbsOrderLogList = ebsOrderLogService
				.query(ebsOrderLogBuilder.build());

		int needRetryQuantity = 0;
		int failRetryQuantity = 0;

		if (null != needRetrySendCreateFailResultEbsOrderLogList) {
			needRetryQuantity = needRetrySendCreateFailResultEbsOrderLogList.size();
		}

		if (needRetryQuantity > 0) {
			logger.info("开始需要向订单中心重发，取消订单结果发送给商城失败的有 " + needRetryQuantity + " 个");
		}

		for (EbsOrderLogModel ebsOrderLog : needRetrySendCreateFailResultEbsOrderLogList) {
			String msgJson = ebsOrderLog.getMsgJson();
			if (!StringUtils.isEmpty(msgJson)) {
				try {
					CancelOrderResultDtoReq dtoReq = JSONObject.parseObject(msgJson, CancelOrderResultDtoReq.class);

					Boolean orderResult = moneyFeedOrderCenterService.returnToOrderCancelInfoNoRecordLog(dtoReq);

					if (orderResult) {
						EbsOrderLogModel newEbsOrderLog = new EbsOrderLogModel();
						newEbsOrderLog.setOperationResult((byte) 1);

						ebsOrderLogService.update(ebsOrderLog, newEbsOrderLog);
					} else {
						failRetryQuantity++;
					}
				} catch (Exception e) {
					logger.error("需要向订单中心重发，取消订单结果发送给商城异常 ： " + e);
					failRetryQuantity++;
					CommonService.formatExceptionMsg(this.getClass(), e);
				}
			}
		}

		return result;
	}

	@Override
	public DtoResult ebsToMoneyfeedSendUpdateInfoResult() {
		DtoResult dtoResult = commonService.getSuccessDtoResult();

		List<EbsOrderModel> ebsOrderListHavedUpdateResult = ebsOrderService.getEbsOrderListHavedUpdateResult();

		for (EbsOrderModel ebsOrderModel : ebsOrderListHavedUpdateResult) {
			operateOneEbsOrderHavedUpdateResult(ebsOrderModel);
		}

		return dtoResult;
	}

	private boolean operateOneEbsOrderHavedUpdateResult(EbsOrderModel ebsOrderModel) {
		String orderUpdateStatus = ebsOrderModel.getOrderUpdateStatus();
		Boolean operateResult = true;

		TransportInfoToEbsResultDtoReq toMoneyfeedDtoReq = new TransportInfoToEbsResultDtoReq();
		toMoneyfeedDtoReq.setEbsOrderNo(ebsOrderModel.getEbsOrderNo());

		EbsOrderLogModel ebsOrderLogSearch = new EbsOrderLogModel();
		ebsOrderLogSearch.setOrderId(ebsOrderModel.getId());
		ebsOrderLogSearch.setLogType(EbsOrderLogTypeEnum.TO_EBS_GET_UPDATE_RESULT_REQUEST.name());

		EbsOrderLogModel latestUpdateEbsOrderLog = ebsOrderLogService
				.queryEbsOrderLogLatestLogByOrderIdAndLogType(ebsOrderLogSearch);
		MoneyfeedToEbsOrderUpdateInfoDtoReq moneyfeedToEbsOrderUpdateInfoDtoReq = JSONObject
				.parseObject(latestUpdateEbsOrderLog.getMsgJson(), MoneyfeedToEbsOrderUpdateInfoDtoReq.class);

		if (MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name().equals(orderUpdateStatus)) {
			toMoneyfeedDtoReq.setModifyFlag(true);
		} else if (MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL.name().equals(orderUpdateStatus)) {
			toMoneyfeedDtoReq.setModifyFlag(false);
			toMoneyfeedDtoReq.setMsg("更新EBS订单信息失败，原因：EBS系统业务异常，" + latestUpdateEbsOrderLog.getErrorMsg());
		} else if (MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL.name().equals(orderUpdateStatus)) {
			toMoneyfeedDtoReq.setModifyFlag(false);
			toMoneyfeedDtoReq.setMsg("更新EBS订单信息失败，原因：EBS系统异常，连接不上");
		} else if (MoneyfeedToEbsOperationStatusEnum.WAIT_FOR_EBS_TIMEOUT.name().equals(orderUpdateStatus)
				&& latestUpdateEbsOrderLog.getFailCount() < 2) {

			// 连接超时的情况，再发一次即可，如果第二次还超时，则直接认为EBS业务失败
			List<EbsOrderUpdateReq> reqList = converseDtoToRequestForUpdate(moneyfeedToEbsOrderUpdateInfoDtoReq);

			MoneyfeedToEbsOperationStatusEnum result = null;
			try {
				// 请求EBS
				EbsResponseUpdateSimple response = transferWithEbsService.sendEbsOrderUpdateRequest(reqList);

				if (null != response && null != response.getReturnCode() &&
						MoneyfeedToEbsUpdateOrderInfoThread.EBS_SUCCESS_CODE.equals(response.getReturnCode())) {
					result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS;
				} else {
					throw new Exception("更新订单车牌号和计划拉货日期失败，" + response.getReturnMsg());
				}
			} catch (Exception e) {
				logger.error("向EBS发送更新订单请求, EBS订单编码: " + ebsOrderModel.getEbsOrderNo() + " 报错:" + e.getMessage());

				if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
					// 连接EBS失败，导致无法创建订单
					result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
				} else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
					result = MoneyfeedToEbsOperationStatusEnum.WAIT_FOR_EBS_TIMEOUT;

					EbsOrderLogModel newEbsOrderLog = new EbsOrderLogModel();
					newEbsOrderLog.setFailCount(newEbsOrderLog.getFailCount() + 1);

					ebsOrderLogService.update(latestUpdateEbsOrderLog, newEbsOrderLog);
				} else {
					// EBS业务异常，直接返回失败
					result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
				}
			}

			EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
			ebsOrderBuilder.orderUpdateStatus(result.name());

			ebsOrderService.update(ebsOrderModel, ebsOrderBuilder.build());

			return false;
		} else if (MoneyfeedToEbsOperationStatusEnum.WAIT_FOR_EBS_TIMEOUT.name().equals(orderUpdateStatus)
				&& latestUpdateEbsOrderLog.getFailCount() >= 2) {
			toMoneyfeedDtoReq.setModifyFlag(false);
			toMoneyfeedDtoReq.setMsg("更新EBS订单信息失败，原因：连续2次等待EBS系统返回结果都超时失败");
		}

		Boolean toMoneyfeedResult = moneyFeedOrderCenterService.returnToOrderUpdateInfoNoRecordLog(toMoneyfeedDtoReq);

		if (toMoneyfeedResult) {

			// 从更新日志中获取商城传过来的更新信息，更新ebs_order_from_sale
			if (null != latestUpdateEbsOrderLog) {

				// 更新 ebs_order_from_sale
				EbsOrderFromSaleModel ebsOrderFromSale = ebsOrderFromSaleService
						.queryOrderFromSaleByEbsOrderId(ebsOrderModel.getId());

				EbsOrderFromSaleModel newEbsOrderFromSale = new EbsOrderFromSaleModel();
				newEbsOrderFromSale.setPlanPickupDate(moneyfeedToEbsOrderUpdateInfoDtoReq.getPlanTransportTimeDate());
				newEbsOrderFromSale.setCarNo(moneyfeedToEbsOrderUpdateInfoDtoReq.getCarNo());

				ebsOrderFromSaleService.update(ebsOrderFromSale, newEbsOrderFromSale);

				// 更新 ebs_order_detail
				EbsOrderExModel ebsOrderWithDetail = ebsOrderService
						.getEbsOrderWithDetailByEbsOrderNo(ebsOrderModel.getEbsOrderNo());

				EbsOrderDetailModel newEbsOrderDetail = new EbsOrderDetailModel();
				newEbsOrderDetail.setCarNo(moneyfeedToEbsOrderUpdateInfoDtoReq.getCarNo());
				newEbsOrderDetail.setPlanTransportTime(moneyfeedToEbsOrderUpdateInfoDtoReq.getPlanTransportTimeDate());

				ebsOrderDetailService.update(ebsOrderWithDetail.getEbsOrderDetail(), newEbsOrderDetail);

				EbsOrderFromEbsModel ebsOrderFromEbs = ebsOrderFromEbsService
						.getEbsOrderFromEbsByEbsOrderId(ebsOrderWithDetail.getId());

				// 记录日志
				commonService.saveFulllog(ebsOrderFromEbs.getRealPayAmount(), ebsOrderFromEbs.getRealPayAmount(),
						ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(),
						ebsOrderWithDetail.getEbsOrderDetail().getEbsOrderStatus(), ebsOrderFromSale,
						newEbsOrderFromSale, ebsOrderWithDetail.getEbsOrderNo(), new Date(),
						Long.valueOf(ebsOrderWithDetail.getSaleOrderId()), ebsOrderWithDetail.getBankPayNo(),
						Long.valueOf(ebsOrderWithDetail.getSaleOrderId()), UserOperEventType.ORDER,
						UserOperationEnums.INT_CALL_EBS_UPDATE_CAR_OR_DATE,
						"从EBS获取更新订单的车牌号和拉料日期的请求，EBS订单编码" + ebsOrderWithDetail.getEbsOrderNo(),
						UserOperSourceType.MEDIUM_CENTER);
			}

			// 更新ebs_order表中的order_update_status
			EbsOrderModel newEbsOrder = new EbsOrderModel();
			newEbsOrder.setOrderUpdateStatus(MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name());

			ebsOrderService.update(ebsOrderModel, newEbsOrder);

			operateResult = true;
		} else {
			operateResult = false;
		}

		return operateResult;
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

	public DtoResult ebsToMoneyfeedOrdersRechargeResult() {
		DtoResult dtoResult = commonService.getSuccessDtoResult();

		List<EbsRechargeModel> payingEbsRechargeList = ebsRechargeService.getPayingEbsRechargeList();
		if (CollectionUtils.isEmpty(payingEbsRechargeList)) {
			return dtoResult;
		}

		List<EbsResponsePayOrChargeResult> payResultList = Lists.newArrayList();
		List<EbsOrderForPayOrChargeForResultReq> payReqList = new ArrayList<EbsOrderForPayOrChargeForResultReq>();
		try {
			payReqList = converseEbsRechargeReqList(payingEbsRechargeList);
			payResultList = transferWithEbsService.getEbsOrderPayResultList(payReqList);
		} catch (Exception e) {
			CommonService.formatExceptionMsg(EbsToMoneyfeedOrderServiceImpl.class, e);

			logger.error("获取EBS系统充值结果报错: payReqList：" + JSONObject.toJSONString(payReqList) + " , errorMSG：" + e);

			dtoResult.setCode(ResultCode.FAIL.getCode());

			return dtoResult;
		}

		// 组装 key：组织+客户编码+支付流水号
		Map<String, EbsRechargeModel> keyToModelMap = new HashMap<String, EbsRechargeModel>();
		for (EbsRechargeModel ebsRechargeModel : payingEbsRechargeList) {
			keyToModelMap.put(ebsRechargeModel.getOrgId() + ebsRechargeModel.getCustomerNo()
					+ ebsRechargeModel.getMoneyfeedPayNo(), ebsRechargeModel);
		}

		for (EbsResponsePayOrChargeResult payResult : payResultList) {
			String key = payResult.getOrgId() + payResult.getCustomerNo() + payResult.getMoneyfeedPayNo(); // key：组织+客户编码+支付流水号
			EbsRechargeModel ebsRechargeModel = keyToModelMap.get(key);
			if (ebsRechargeModel != null) {
				operateOneRechargeResult(payResult, ebsRechargeModel);
			}
		}

		return dtoResult;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean operateOneRechargeResult(EbsResponsePayOrChargeResult payResult, EbsRechargeModel ebsRechargeModel) {

		String ebsResult = payResult.getReturnCode();
		EbsRechargeModel newEbsRechargeModel = null;
		if ("S".equals(ebsResult)) {
			// ebs recharge
			EbsOrderRechargeModelBuilder ebsOrderRechargeBuilder = EbsOrderRechargeModelBuilder
					.anEbsOrderRechargeModel();
			ebsOrderRechargeBuilder.payStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name());

			newEbsRechargeModel = ebsOrderRechargeBuilder.build();
			ebsRechargeService.update(ebsRechargeModel, newEbsRechargeModel);
		} else {
			// ebs recharge
			EbsOrderRechargeModelBuilder ebsOrderRechargeBuilder = EbsOrderRechargeModelBuilder
					.anEbsOrderRechargeModel();
			ebsOrderRechargeBuilder.payStatus(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL.name());

			newEbsRechargeModel = ebsOrderRechargeBuilder.build();
			ebsRechargeService.update(ebsRechargeModel, newEbsRechargeModel);

		}

		// 记录日志
		String comment = "获取EBS系统充值结果，ebs订单编码：" + payResult.getEbsOrderNo() + " ，充值方式：" + payResult.getPayOrChargeType()
				+ "，orgId：" + payResult.getOrgId() + " ，客户编号：" + payResult.getCustomerNo() + " ，银行支付流水号："
				+ payResult.getAccNo() + " , Ebs返回的信息: " + payResult.getReturnCode() + ", " + payResult.getReturnMsg();

		String ebsOrderNo = null;
		Long saleOrderId = null;
		if (ebsRechargeModel.getOrderId() != null) {
			EbsOrderModel ebsOrderModel = ebsOrderService.getEbsOrderModelById(ebsRechargeModel.getOrderId());
			ebsOrderNo = ebsOrderModel.getEbsOrderNo();
			saleOrderId = org.apache.commons.lang3.StringUtils.isEmpty(ebsOrderModel.getSaleOrderId()) ? null
					: Long.parseLong(ebsOrderModel.getSaleOrderId());
		}

		commonService.saveFulllog(ebsRechargeModel.getTotalPayAmount(), ebsRechargeModel.getTotalPayAmount(),
				ebsRechargeModel.getPayStatus(), newEbsRechargeModel.getPayStatus(), ebsRechargeModel,
				newEbsRechargeModel, ebsOrderNo, new Date(), ebsRechargeModel.getId(), null, saleOrderId,
				UserOperEventType.RECHARGE, UserOperationEnums.INT_RECEIVE_RECHARGE_RESULT_OF_EBS, comment,
				UserOperSourceType.MEDIUM_CENTER);

		// TODO 通知商城，订单支付结果
		commonService.sendToOrderPayOrChargeMqResult(payResult.getEbsOrderNo(), ebsRechargeModel.getPayType(),
				"S".equals(payResult.getReturnCode()) ? true : false, payResult.getReturnMsg(), payResult.getOrgId(),
				payResult.getMoneyfeedPayNo(), MQMsgKindEnum.recharge);

		return true;
	}

	private List<EbsOrderForPayOrChargeForResultReq> converseEbsRechargeReqList(
			List<EbsRechargeModel> payingEbsRechargeList) {
		List<EbsOrderForPayOrChargeForResultReq> rechargeResultList = Lists.newArrayList();

		if (CollectionUtils.isEmpty(payingEbsRechargeList)) {
			return rechargeResultList;
		}
		List<Long> orderIdList = payingEbsRechargeList.stream().filter(x -> x.getOrderId() != null)
				.map(EbsRechargeModel::getOrderId).collect(Collectors.toList());
		Map<Long, List<EbsOrderModel>> orderIdToModelListMap = null;
		if (!CollectionUtils.isEmpty(orderIdList)) {
			List<EbsOrderModel> ebsOrderListByIdList = ebsOrderService.getEbsOrderListByIdList(orderIdList);
			if (!CollectionUtils.isEmpty(ebsOrderListByIdList)) {
				orderIdToModelListMap = ebsOrderListByIdList.stream()
						.collect(Collectors.groupingBy(EbsOrderModel::getId));
			}
		}

		for (EbsRechargeModel ebsRechargeModel : payingEbsRechargeList) {
			EbsOrderForPayOrChargeForResultReq req = new EbsOrderForPayOrChargeForResultReq();
			String ebsOrderNo = null;
			if (ebsRechargeModel.getOrderId() != null) {
				List<EbsOrderModel> list = orderIdToModelListMap.get(ebsRechargeModel.getOrderId());
				EbsOrderModel ebsOrderModel = list.get(0);
				ebsOrderNo = ebsOrderModel.getEbsOrderNo();
			}
			req.setAccNo(ebsRechargeModel.getAccNo());
			req.setCustomerNo(ebsRechargeModel.getCustomerNo()); // 客户编码
			req.setEbsOrderNo(ebsOrderNo);
			req.setPayOrChargeType(ebsRechargeModel.getPayType());
			req.setOrgId(ebsRechargeModel.getOrgId());

			rechargeResultList.add(req);
		}

		return rechargeResultList;
	}
}
