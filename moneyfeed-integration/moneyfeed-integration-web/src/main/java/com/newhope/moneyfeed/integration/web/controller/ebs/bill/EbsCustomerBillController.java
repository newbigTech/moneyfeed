package com.newhope.moneyfeed.integration.web.controller.ebs.bill;

import com.alibaba.druid.util.StringUtils;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.concurrent.ManagedThreadPool;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;
import com.newhope.moneyfeed.integration.api.rest.ebs.bill.EbsCustomerBillAPI;
import com.newhope.moneyfeed.integration.api.service.ebs.bill.EBSCustomerBillService;
import com.newhope.moneyfeed.integration.biz.thread.EbsSyncCustomerBillThread;

@RestController
public class EbsCustomerBillController extends AbstractController implements EbsCustomerBillAPI {

	private final static Logger logger = LoggerFactory.getLogger(EbsCustomerBillController.class);

	@Autowired
	private ManagedThreadPool managedThreadPool;

	@Autowired
	private EBSCustomerBillService billService;

	/*
	 * 查询客户对账单汇总
	 */
	@Override
	public BaseResponse<EbsCustomerBillDtoResult> ebsBillQuery(@RequestBody EbsCustomerBillDtoReq billReq) {

		EbsCustomerBillDtoResult result = new EbsCustomerBillDtoResult();
		
		if(StringUtils.isEmpty(billReq.getOrgId()))
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("公司为空");
			return buildJson(result);
		}
		
		if(StringUtils.isEmpty(billReq.getCustomerNo()))
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("客户编码为空");
			return buildJson(result);
		}
		
		result = billService.ebsBillQuery(billReq);

		return buildJson(result);
	}

	/*
	 * 查询客户对账单明细
	 */
	@Override
	public BaseResponse<EbsCustomerBillDetailPageDtoResult> ebsBillDetailQuery(@RequestBody EbsCustomerBillDetailPageDtoReq billDetailReq) {

		EbsCustomerBillDetailPageDtoResult result=new EbsCustomerBillDetailPageDtoResult();
		
		if(StringUtils.isEmpty(billDetailReq.getOrgId()))
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("公司为空");
			return buildJson(result);
		}
		
		if(StringUtils.isEmpty(billDetailReq.getCustomerNo()))
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("客户编码为空");
			return buildJson(result);
		}
		
		if(billDetailReq.getYear()==null)
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("年份为空");
			return buildJson(result);
		}
		
		if(billDetailReq.getMonth()==null)
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("月份为空");
			return buildJson(result);
		}

		result = billService.ebsBillDetailQuery(billDetailReq);
		
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> syncCustomerBill() {
		DtoResult result = new DtoResult();
		try {
			EbsSyncCustomerBillThread syncThread = new EbsSyncCustomerBillThread();
			managedThreadPool.submit(syncThread);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步EBS客户账单出错：", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return buildJson(result);
	}

}
