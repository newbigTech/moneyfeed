package com.newhope.pay.biz.service;

import com.newhope.moneyfeed.pay.api.bank.QueryResponseEntity;
import com.newhope.moneyfeed.pay.api.dto.req.bank.BankOrderQueryDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.bank.BankOrderQueryDtoResult;

/**
 * 本模块与银行支付系统调用service
 * @author heping  
 * @date 2019年1月11日
 */
public interface PayBankService{
	
	BankOrderQueryDtoResult queryBankOrder(BankOrderQueryDtoReq req);
	
}
