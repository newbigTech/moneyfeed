package com.newhope.moneyfeed.integration.web.controller.ebs.balance;

import com.alibaba.druid.util.StringUtils;
import com.ctc.wstx.util.StringUtil;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.balance.EbsBalanceDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDtoResult;
import com.newhope.moneyfeed.integration.api.rest.ebs.balance.EbsCustomerBalanceAPI;
import com.newhope.moneyfeed.integration.api.service.ebs.balance.EBSCustomerBalanceService;

@RestController
public class EbsCustomerBalanceController extends AbstractController implements EbsCustomerBalanceAPI {
	

	@Autowired
	EBSCustomerBalanceService balanceService;
	/*
	 * 获取客户余额
	 **/
	@Override
	public BaseResponse<EbsBalanceDtoResult> ebsBalanceQuery(@RequestBody EbsBalanceDtoReq req) {

		EbsBalanceDtoResult result=new EbsBalanceDtoResult();
		
		if(StringUtils.isEmpty(req.getCustomerCode()))
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("客户编码为空");
			return buildJson(result);
		}
		
		if(StringUtils.isEmpty(req.getOrgCode()))
		{
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("公司ID为空");
			return buildJson(result);
		}
		
	    result = balanceService.ebsBalanceQuery(req);

		return buildJson(result);
	}

}
