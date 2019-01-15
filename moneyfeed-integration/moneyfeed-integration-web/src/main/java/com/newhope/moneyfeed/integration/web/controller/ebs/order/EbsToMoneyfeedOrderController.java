package com.newhope.moneyfeed.integration.web.controller.ebs.order;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.concurrent.ManagedThreadPool;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.integration.api.rest.ebs.order.EbsToMoneyfeedOrderAPI;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CategoryReq;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsToMoneyfeedOrderServiceImpl;
import com.newhope.moneyfeed.integration.biz.thread.EBSToMoneyfeedOrderLockWarehouseThread;
import com.newhope.moneyfeed.integration.biz.thread.EBSyncCategoryThread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuyanlin on 2018/11/21
 */
@RestController
public class EbsToMoneyfeedOrderController extends AbstractController implements EbsToMoneyfeedOrderAPI {

	@Autowired
	private EbsToMoneyfeedOrderServiceImpl ebsToMoneyfeedOrderService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ManagedThreadPool managedThreadPool;

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedOrdersCreateResult() {
		DtoResult result = commonService.getSuccessDtoResult();

		ebsToMoneyfeedOrderService.ebsToMoneyfeedOrdersCreateResult();

		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedRetrySendFailCreateResult() {
		DtoResult result = commonService.getSuccessDtoResult();

		ebsToMoneyfeedOrderService.ebsToMoneyfeedRetrySendFailCreateResult();

		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedOrdersPayResult() {
		DtoResult result = ebsToMoneyfeedOrderService.ebsToMoneyfeedOrdersPayResult();

		return buildJson(result);
	}
	
	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedOrdersRechargeResult() {
		DtoResult result = ebsToMoneyfeedOrderService.ebsToMoneyfeedOrdersRechargeResult();

		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedOrdersCancelResult() {
		DtoResult result = ebsToMoneyfeedOrderService.ebsToMoneyfeedOrdersCancelResult();
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedOrderLockWarehouseResult() {
		/*
		 * DtoResult result =
		 * ebsToMoneyfeedOrderService.ebsToMoneyfeedOrderLockWarehouseResult();
		 * return buildJson(result);
		 */

		DtoResult result = new DtoResult();
		try {
			// 异步执行，防止超时
			EBSToMoneyfeedOrderLockWarehouseThread lockWarehouseThread = new EBSToMoneyfeedOrderLockWarehouseThread();
			managedThreadPool.submit(lockWarehouseThread);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}

		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedSendUpdateInfoResult() {
		DtoResult result = ebsToMoneyfeedOrderService.ebsToMoneyfeedSendUpdateInfoResult();
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> ebsToMoneyfeedRetrySendFailCancelResult() {
		DtoResult result = commonService.getSuccessDtoResult();
		ebsToMoneyfeedOrderService.ebsToMoneyfeedRetrySendFailCancelResult();
		return buildJson(result);
	}

}
