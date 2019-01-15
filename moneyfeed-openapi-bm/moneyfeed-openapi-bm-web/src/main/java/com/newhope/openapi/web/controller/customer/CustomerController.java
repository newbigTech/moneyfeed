package com.newhope.openapi.web.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.openapi.api.rest.customer.CustomerAPI;
import com.newhope.openapi.api.vo.request.customer.AllotIntentionCustomerReq;
import com.newhope.openapi.api.vo.request.customer.CustomerContactQueryReq;
import com.newhope.openapi.api.vo.request.customer.CustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.IntentionCustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.ModifyIntentionCustomerMsg;
import com.newhope.openapi.api.vo.request.customer.UpdateCustomerPropertyReq;
import com.newhope.openapi.api.vo.response.customer.CustomerContactListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerPropertyResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerListResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerResult;
import com.newhope.openapi.biz.service.customer.CustomerService;

@RestController
public class CustomerController extends AbstractController implements CustomerAPI {
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	CustomerService customerService;

	@Override
	public BaseResponse<CustomerContactListResult> queryCustomerContactList(CustomerContactQueryReq customerQueryReq,
			HttpServletRequest request) {
		CustomerContactListResult result = new CustomerContactListResult();
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		
		result = customerService.queryCustomerContactList(customerQueryReq);
		return buildJson(result);
	}
	
	@Override
	public void exportCustomerContact(CustomerContactQueryReq customerQueryReq, HttpServletResponse response,
			HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}
		customerService.exportCustomerContact(customerQueryReq, response);
	}

	@Override
	public BaseResponse<IntentionCustomerListResult> queryIntentionCustomerList(
			IntentionCustomerQueryReq intentionCustomerQueryReq,
			HttpServletRequest request) {
		IntentionCustomerListResult result = new IntentionCustomerListResult();
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		result = customerService.queryIntentionCustomerList(intentionCustomerQueryReq);
		return buildJson(result);
	}

	@Override
	public void exportIntentionCustomer(IntentionCustomerQueryReq intentionCustomerQueryReq, 
			HttpServletResponse response,HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}
		
		customerService.exportIntentionCustomer(intentionCustomerQueryReq, response);
	}
	
	@Override
	public BaseResponse<IntentionCustomerResult> queryIntentionCustomerInfo(@RequestParam(name="applyId",required=true)Long applyId) {
		return buildJson(customerService.queryIntentionCustomerInfo(applyId));
	}

	@Override
	public BaseResponse<Result> allotIntentionCustomer(
			@RequestBody AllotIntentionCustomerReq allotIntentionCustomerReq) {
		Result result =  customerService.allotIntentionCustomer(allotIntentionCustomerReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyIntentionCustomerShopDealMsg(
			@RequestBody ModifyIntentionCustomerMsg modifyIntentionCustomerMsg) {
		Result result =  customerService.modifyIntentionCustomerShopDealMsg(modifyIntentionCustomerMsg);
		return buildJson(result);
	}

	@Override
	public BaseResponse<CustomerListResult> queryCustomerList(CustomerQueryReq customerQueryReq, HttpServletRequest request) {
		CustomerListResult result = new CustomerListResult();
		
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}

		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
		customerQueryReq.setShopId(user.getShopId());
		return buildJson(customerService.queryCustomerList(customerQueryReq));
	}
	
	@Override
	public void exportCustomer(CustomerQueryReq customerQueryReq, HttpServletResponse response,
			HttpServletRequest request) {
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
		}

		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
		customerQueryReq.setShopId(user.getShopId());
		customerService.exportCustomer(customerQueryReq, response);
	}
	
	@Override
	public BaseResponse<CustomerListResult> queryAllCustomerList(CustomerQueryReq customerQueryReq, HttpServletRequest request) {
		CustomerListResult result = new CustomerListResult();
		
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}

		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
		customerQueryReq.setShopId(user.getShopId());
		customerQueryReq.setPage(null);
		customerQueryReq.setPageSize(null);
		return buildJson(customerService.queryCustomerList(customerQueryReq));
	}

	@Override
	public BaseResponse<CustomerPropertyResult> queryCustomerPropertyInfo(Long customerId, @RequestParam(name="shopId",required=true)Long shopId,
			HttpServletRequest request) {
		CustomerPropertyResult result = new CustomerPropertyResult();
		
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		
		return buildJson(customerService.queryCustomerPropertyInfo(customerId, shopId));
	}

	@Override
	public BaseResponse<Result> modifyCustomerPropertyInfo(@RequestBody UpdateCustomerPropertyReq updateCustomerPropertyReq,
			HttpServletRequest request) {
		Result result = new Result();
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		return buildJson(customerService.modifyCustomerPropertyInfo(updateCustomerPropertyReq));
	}

	@Override
	public BaseResponse<CustomerContactListResult> queryCustomerClientUser(CustomerContactQueryReq customerQueryReq,
			HttpServletRequest request) {
		CustomerContactListResult result = new CustomerContactListResult();
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		
		result = customerService.queryCustomerClientUser(customerQueryReq);
		return buildJson(result);
	}

}
