package com.newhope.openapi.biz.service.order;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.vo.request.order.OrderRuleQueryReq;
import com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult;
import com.newhope.openapi.api.vo.response.order.rule.TransportDateRuleResult;
import com.newhope.openapi.biz.rpc.feign.order.OrderRuleFeignClient;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 19:00
 */
@Service
public class OrderRuleService {
	@Autowired
	OrderRuleFeignClient orderRuleFeignClient;
	@Autowired
	RSessionCache rSessionCache;

	/**
	 * 
	 *
	 * @author: dongql
	 * @date: 2018/11/21 15:14
	 * @param req
	 * @return: com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult
	 */
	public OrderRuleDetailResult queryOrderRuleDetail(OrderRuleQueryReq req) {
		OrderRuleDetailResult result = new OrderRuleDetailResult();
		OrderRuleQueryDtoReq dtoReq = new OrderRuleQueryDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		/*
		 * if (req.getUcShopId() == null) { Object obj =
		 * rSessionCache.getUserInfo(); if(obj == null ){
		 * result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
		 * result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc()); return
		 * result; } dtoReq.setUcShopId(((UcBmUserDtoResult) obj).getShopId());
		 * }
		 */
		BaseResponse<OrderRuleDetailDtoResult> response = orderRuleFeignClient.queryOrderRuleDetail(dtoReq);
		if (ResultCode.SUCCESS.getCode().equals(response.getCode()) && null != response.getData()) {
			BeanUtils.copyProperties(response.getData(), result);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}

	/**
	 * 获取当前用户访问商户的拉料规则 
	 * @creator : pudongliang 
	 * @dateTime : 2018年11月24日上午10:26:04
	 */
	public TransportDateRuleResult getRuleOfTransportDate() {
		TransportDateRuleResult result = new TransportDateRuleResult();
		/** 登录验证 */
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
		OrderRuleQueryDtoReq dtoReq = new OrderRuleQueryDtoReq();
		dtoReq.setUcShopId(userInfo.getVisitShop().getShop().getId());
		BaseResponse<OrderRuleDetailDtoResult> response = orderRuleFeignClient.queryOrderRuleDetail(dtoReq);
		OrderRuleDetailDtoResult ruleResult = response.getData();
		BeanUtils.copyProperties(response, result);
		if ( response.isSuccess() && ruleResult != null) {
			Date currentDate = DateUtils.trunc(new Date());
			int startDay = ruleResult.getTransportStartDay() == null ? 0 : ruleResult.getTransportStartDay();
			result.setBeginDate(calculateDate(currentDate,Calendar.DAY_OF_YEAR,startDay));
			Integer endDay = ruleResult.getTransportEndDay();
			if( endDay != null && endDay - startDay >= 0 ){
				result.setEndDate(calculateDate(currentDate,Calendar.DAY_OF_YEAR,endDay.intValue()));
			}
		}
		return result;
	}
	
	/**
	 * @param data the original date
	 * @param field the calendar field.
     * @param amount the amount of date or time to be added to the field.
	 * @creator : pudongliang  
	 * @dateTime : 2018年11月24日上午10:49:28
	 */
	private Date calculateDate(Date date, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return c.getTime();
	}
}
