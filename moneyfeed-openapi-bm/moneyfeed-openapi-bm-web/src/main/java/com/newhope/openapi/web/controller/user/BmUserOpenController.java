package com.newhope.openapi.web.controller.user;

import java.util.ArrayList;
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
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.openapi.api.rest.user.BmUserOpenAPI;
import com.newhope.openapi.api.vo.request.user.EmployeeInfoReq;
import com.newhope.openapi.api.vo.request.user.LoginByPassReq;
import com.newhope.openapi.api.vo.request.user.LoginBySmscodeReq;
import com.newhope.openapi.api.vo.request.user.ModifyEmployeeInfoReq;
import com.newhope.openapi.api.vo.request.user.ModifyUserInfoBySmscodeReq;
import com.newhope.openapi.api.vo.request.user.ResetPassWordReq;
import com.newhope.openapi.api.vo.response.user.BmUserListResult;
import com.newhope.openapi.api.vo.response.user.BmUserMenuResult;
import com.newhope.openapi.api.vo.response.user.BmUserResult;
import com.newhope.openapi.api.vo.response.user.ClientRoleListResult;
import com.newhope.openapi.api.vo.response.user.PmResourceResult;
import com.newhope.openapi.biz.service.user.BmUserService;

@RestController
public class BmUserOpenController extends AbstractController implements BmUserOpenAPI {
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	BmUserService userService;

	@Override
	public BaseResponse<BmUserResult> loginBySmscode(@RequestBody LoginBySmscodeReq requestBody) {
		BmUserResult result = new BmUserResult();
		result = userService.loginBySmscode(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<BmUserMenuResult> getUserMenu(HttpServletRequest request) {
		BmUserMenuResult result = new BmUserMenuResult();
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
		List<Long> roleIds = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(user.getRoleDtoList())) {
			for (UcPmRoleDtoResult role : user.getRoleDtoList()) {
				roleIds.add(role.getId());
			}
		}
		result = userService.getUserMenu(user.getId(), user.getMobile(), roleIds);
		return buildJson(result);
	}

	@Override
	public BaseResponse<BmUserResult> loginByPass(@RequestBody LoginByPassReq requestBody) {
		BmUserResult result = new BmUserResult();
		result = userService.loginByPass(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> resetPassWord(@RequestBody ResetPassWordReq requestBody) {
		Result result = new Result();
		result = userService.resetPassWord(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyUserInfo(@RequestBody ModifyUserInfoBySmscodeReq requestBody) {
		Result result = userService.modifyUserInfo(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<BmUserListResult> queryBmEmployeeInfoList(@RequestBody EmployeeInfoReq userInfoDtoReq) {
		BmUserListResult result = userService.queryBmEmployeeInfoList(userInfoDtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<BmUserResult> queryBmEmployeeInfo(Long userId) {
		BmUserResult result = userService.queryBmEmployeeInfo(userId);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> addBmEmployeeInfo(@RequestBody ModifyEmployeeInfoReq userInfoDtoReq) {
		Result result = userService.addBmEmployeeInfo(userInfoDtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyBmEmployeeInfo(@RequestBody ModifyEmployeeInfoReq userInfoDtoReq) {
		Result result = userService.modifyBmEmployeeInfo(userInfoDtoReq);
		return buildJson(result);
	}

    @Override
    public void exportBmEmployeeInfoList(EmployeeInfoReq req, HttpServletResponse response) {
		userService.exportBmEmployeeInfoList(req,response);
    }

	@Override
	public BaseResponse<ClientRoleListResult> getClientRole() {
		ClientRoleListResult result = userService.getClientRole();
		return buildJson(result);
	}

	@Override
	public BaseResponse<PmResourceResult> getRoleResource(@RequestParam(name="roleId",required=true) Long roleId) {
		PmResourceResult result = userService.getRoleResource( roleId);
		return buildJson(result);
	}

}
