package com.newhope.pay.biz.service.impl;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.pay.api.bank.Config;
import com.newhope.moneyfeed.pay.api.bank.Merchant;
import com.newhope.moneyfeed.pay.api.bank.QueryResponseEntity;
import com.newhope.moneyfeed.pay.api.bank.SignUtil;
import com.newhope.moneyfeed.pay.api.dto.req.bank.BankOrderQueryDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.bank.BankOrderQueryDtoResult;
import com.newhope.pay.biz.service.PayBankService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 本模块与银行支付系统调用service
 * @author heping  
 * @date 2019年1月11日
 */
@Service
public class PayBankServiceImpl implements PayBankService {
	private final Logger logger = LoggerFactory.getLogger(PayBankServiceImpl.class);
	
	@Override
	public BankOrderQueryDtoResult queryBankOrder(BankOrderQueryDtoReq req) {
		
		BankOrderQueryDtoResult bankOrderQueryDtoResult = new BankOrderQueryDtoResult();
		bankOrderQueryDtoResult.setCode(ResultCode.SUCCESS.getCode());
		bankOrderQueryDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		
		try {
			// 签名初始化
			SignUtil.init();
			// 准备输入数据
			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("apiName", Config.APINAME_QUERY);
			requestMap.put("apiVersion", Config.API_VERSION);
			requestMap.put("platformID", Config.PLATFORM_ID);
			requestMap.put("merchNo", Config.MERCHANT_ACC);
			
			requestMap.put("orderNo", req.getOrderNo());
			requestMap.put("tradeDate", DateUtils.date2Str(req.getTradeDate(), DateUtils.yyyyMMdd));
			requestMap.put("amt", req.getAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
			String paramsStr = Merchant.generateQueryRequest(requestMap);
			String signStr = SignUtil.signData(paramsStr);
			paramsStr = paramsStr + "&signMsg=" + signStr;
			 
			// 发送请求并接收返回
			String responseMsg = Merchant.transact(paramsStr, Config.PAY_GETWAY);
			
			logger.info(responseMsg);
			QueryResponseEntity queryResponseEntity = new QueryResponseEntity();
			queryResponseEntity.parse(responseMsg);
			bankOrderQueryDtoResult.setData(queryResponseEntity);
		} catch (Exception e) {
			logger.error("查询支付中心出错:{}", e.getMessage());
			bankOrderQueryDtoResult.setCode(ResultCode.PAY_STATUS_CODE_ERROR.getCode());
		}
		return bankOrderQueryDtoResult;
	}

    
}
