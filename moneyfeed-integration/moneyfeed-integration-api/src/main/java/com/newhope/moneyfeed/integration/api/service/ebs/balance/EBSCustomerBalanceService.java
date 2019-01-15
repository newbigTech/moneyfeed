package com.newhope.moneyfeed.integration.api.service.ebs.balance;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.balance.EbsBalanceDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDtoResult;

public interface EBSCustomerBalanceService {

	/**
	 * 客户余额查询
	 * */
	EbsBalanceDtoResult ebsBalanceQuery(EbsBalanceDtoReq req);
	
}
