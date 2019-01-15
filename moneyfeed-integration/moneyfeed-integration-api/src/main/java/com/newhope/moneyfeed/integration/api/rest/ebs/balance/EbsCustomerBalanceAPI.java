package com.newhope.moneyfeed.integration.api.rest.ebs.balance;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.balance.EbsBalanceDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDtoResult;
import io.swagger.annotations.ApiOperation;

/**
 * 客户余额
 * */
public interface EbsCustomerBalanceAPI {

	@ApiOperation(value = "客户余额查询", notes = "客户余额查询", protocols = "http")
	@RequestMapping(value = "/ebs/customerbalance/query", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	BaseResponse<EbsBalanceDtoResult> ebsBalanceQuery(@RequestBody EbsBalanceDtoReq req);
	
}
