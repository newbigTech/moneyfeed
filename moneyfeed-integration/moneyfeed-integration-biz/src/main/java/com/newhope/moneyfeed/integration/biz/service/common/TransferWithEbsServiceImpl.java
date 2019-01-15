package com.newhope.moneyfeed.integration.biz.service.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.common.config.HttpMessageSender;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.common.vo.ebs.resp.EBSResponse;
import com.newhope.moneyfeed.integration.api.enums.common.EBSInterfaceIfcodeEnum;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.*;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult.EbsOrderForCreateForResultReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult.EbsOrderForPayOrChargeForResultReq;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs.EbsResponseCreateSimpleData;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs.EbsResponseUpdateSimple;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs.EbsResponseUpdateSimpleData;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.*;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsToMoneyfeedOrderServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 和EBS传输相关服务
 *
 * Created by yuyanlin on 2018/11/21
 */
@Service
public class TransferWithEbsServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(EbsToMoneyfeedOrderServiceImpl.class);

	@Value("${ebs.integration.url}")
	private String ebsInterfaceUrl;

	@Autowired
	private HttpMessageSender httpMessageSender;

	/**
	 * EBS的业务异常会在 convertEBSResponseXMLtoObject 中进行统一捕获
	 *
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public boolean sendEbsOrderCreateRequest(List<EbsOrderCreateReq> req) throws Exception {

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil
				.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_ORDER_CREATE_IFCODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsResponseCreateSimpleData response = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg,
				EbsResponseCreateSimpleData.class);

		if (null != response) {
			return true;
		}

		return false;
	}

	public List<EbsResponseCreateResult> getEbsOrderCreateResultList(List<EbsOrderForCreateForResultReq> reqList)
			throws Exception {

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(
				EBSInterfaceIfcodeEnum.EBS_ORDER_QUERY_CREATE_RESULT_IFCODE.getIfcode(), reqList);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsResponseCreateResultList createResultListData = EBSInterfaceUtil
				.convertEBSResponseXMLtoObject(ebsResponseMsg, EbsResponseCreateResultList.class);

		if (null != createResultListData && CollectionUtils.isNotEmpty(createResultListData.getResultList())) {
			return createResultListData.getResultList();
		}

		return Lists.newArrayList();
	}

	/**
	 * 从EBS获取订单支付结果
	 *
	 * @param reqList
	 * @return
	 * @throws Exception
	 */
	public List<EbsResponsePayOrChargeResult> getEbsOrderPayResultList(List<EbsOrderForPayOrChargeForResultReq> reqList)
			throws Exception {
		logger.info("请求参数：" + JSONObject.toJSONString(reqList));

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(
				EBSInterfaceIfcodeEnum.EBS_RETURN_ORDER_PAY_OR_CHARGE_RESULTS_IFCODE.getIfcode(), reqList);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsResponsePayOrChargeResultList payResultListData = EBSInterfaceUtil
				.convertEBSResponseXMLtoObject(ebsResponseMsg, EbsResponsePayOrChargeResultList.class);

		if (null != payResultListData && CollectionUtils.isNotEmpty(payResultListData.getResultList())) {
			return payResultListData.getResultList();
		}

		return Lists.newArrayList();
	}

	// TODO 需要统一一下EBS返回的结果
	public EbsResponseCancelResultList getEbsOrderCancelResultList(List<EbsOrderCancelReq> reqList) throws Exception {

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil
				.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_ORDER_CANCEL_IFCODE.getIfcode(), reqList);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		EbsResponseCancelResultList ebsResponseCancelResultList = new EbsResponseCancelResultList();
		EbsResponseCancelResult cancelResult = new EbsResponseCancelResult();

		EBSResponse convertEBSResponseXMLtoObject = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg,
				EBSResponse.class);
		if (convertEBSResponseXMLtoObject != null && convertEBSResponseXMLtoObject.getEbsResponseBody() != null
				&& convertEBSResponseXMLtoObject.getEbsResponseBody().getEbsResponeseMsg() != null) {

			cancelResult.setReturnCode(
					convertEBSResponseXMLtoObject.getEbsResponseBody().getEbsResponeseMsg().getReturnCode());
			cancelResult.setReturnMsg(
					convertEBSResponseXMLtoObject.getEbsResponseBody().getEbsResponeseMsg().getReturnDesc());

		}

		ebsResponseCancelResultList.setEbsResponseCancelResult(cancelResult);

		return ebsResponseCancelResultList;
	}

	// TODO 需要统一一下EBS返回的结果
	public List<EBSResponseLockWarehouseResult> getEbsOrderLockResultList(LockWarehouseReq req) throws Exception {

		logger.info("******start 获取EBS当天锁库成功订单,公司ID:" + req.getOrgId() + ";需求日期:" + req.getRequestDate());

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil
				.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_QUERY_LOCK_SUCCESS_IFCODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EBSResponseLockWarehouseResultList dataList = EBSInterfaceUtil
				.convertEBSResponseXMLtoObject(ebsResponseMsg, EBSResponseLockWarehouseResultList.class);

		logger.info("******end 获取EBS当天锁库成功订单,公司ID:" + req.getOrgId() + ";需求日期:" + req.getRequestDate());

		return dataList.getDataList();
	}

	public EbsResponseUpdateSimple sendEbsOrderUpdateRequest(List<EbsOrderUpdateReq> req) throws Exception {
		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil
				.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_ORDER_UPDATE_IFCODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsResponseUpdateSimpleData response = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg, EbsResponseUpdateSimpleData.class);

		return response.getEbsResponseUpdateSimple();
	}

	/**
	 * 向EBS发送余额支付的请求
	 *
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public boolean sendEbsOrderPayOrChargeRequest(EbsOrderPayOrChargeReq req) throws Exception {

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil
				.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_ORDER_PAY_OR_CHARGE_IFCODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);
		logger.info("支付或者充值请求ebs接口返回参数：" + ebsResponseMsg);

		// 解析请求
		EBSResponse response = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg, EBSResponse.class);

		if (null != response) {
			return true;
		}

		return false;
	}

}
