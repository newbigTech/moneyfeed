package com.newhope.openapi.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.ManagerRoleCodeEnums;
import com.newhope.openapi.api.rest.user.CustomerEmployeeManageOpenAPI;
import com.newhope.openapi.api.vo.request.user.CustomerClientUserReq;
import com.newhope.openapi.api.vo.request.user.CustomerClientUserUpdateReq;
import com.newhope.openapi.api.vo.response.user.ClientRoleListResult;
import com.newhope.openapi.api.vo.response.user.CustomerEmployeeListResult;
import com.newhope.openapi.api.vo.response.user.CustomerEmployeeResult;
import com.newhope.openapi.biz.service.user.CustomerEmployeeService;

@RestController
public class CustomerEmployeeManageController extends AbstractController 
	implements CustomerEmployeeManageOpenAPI {

	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	private CustomerEmployeeService customerEmployeeService;
	
	@Override
	public BaseResponse<CustomerEmployeeListResult> queryCustomerEmployeeList(HttpServletRequest request) {
		CustomerEmployeeListResult result = new CustomerEmployeeListResult();
		ClientUserCacheDtoResult userCache = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userCache == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		if(null == userCache.getRole()) {//判断当前登录人员是否为admin角色
			result.setCode(ResultCode.RESOURCE_AUTH_FAIL.getCode());
			result.setMsg(ResultCode.RESOURCE_AUTH_FAIL.getDesc());
			return buildJson(result);
		}
		result = customerEmployeeService.queryCustomerEmployeeList(userCache.getCustomer().getCustomer().getId());
		
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> updateCustomerEmployee(@RequestBody CustomerClientUserUpdateReq customerClientUserReq) {
		Result result = new Result();
		ClientUserCacheDtoResult userCache = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userCache == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		if(customerClientUserReq.getClientUserId().intValue() == userCache.getUser().getId().intValue()
				&& !customerClientUserReq.getEnable()) {
			result.setCode(ResultCode.USER_ADMIN_DIMISSION_NOT_CHANGE.getCode());
			result.setMsg(ResultCode.USER_ADMIN_DIMISSION_NOT_CHANGE.getDesc());
			return buildJson(result);
		}
		customerClientUserReq.setCustomerId(userCache.getCustomer().getCustomer().getId());
		result = customerEmployeeService.updateCustomerEmployee(customerClientUserReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> saveCustomerEmployee(@RequestBody CustomerClientUserReq customerClientUserReq) {
		Result result = new Result();
		ClientUserCacheDtoResult userCache = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userCache == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		customerClientUserReq.setCustomerId(userCache.getCustomer().getCustomer().getId());
		result = customerEmployeeService.saveCustomerEmployee(customerClientUserReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<ClientRoleListResult> getClientRole() {
		ClientRoleListResult result = customerEmployeeService.getClientRole();
		return buildJson(result);
	}

	@Override
	public BaseResponse<CustomerEmployeeResult> getCustomerEmployee(@RequestParam(name="customerClientUserId",required=true)Long customerClientUserId) {
		CustomerEmployeeResult result = new CustomerEmployeeResult();
		ClientUserCacheDtoResult userCache = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userCache == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		result = customerEmployeeService.getCustomerEmployee(customerClientUserId);
		return buildJson(result);
	}

}
