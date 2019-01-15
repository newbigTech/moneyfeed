package com.newhope.openapi.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newhope.openapi.api.vo.request.user.*;
import com.newhope.openapi.api.vo.response.label.LabelListResult;
import com.newhope.openapi.api.vo.response.user.PmUserListResult;
import com.newhope.openapi.api.vo.response.user.UcPmLabelListResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserDtoResult;
import com.newhope.openapi.api.rest.user.PmUserOpenAPI;
import com.newhope.openapi.api.vo.response.user.PmUserMenuResult;
import com.newhope.openapi.api.vo.response.user.PmUserResult;
import com.newhope.openapi.biz.service.user.PmUserService;

@RestController
public class PmUserOpenController extends AbstractController implements PmUserOpenAPI {
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	PmUserService userService;


	@Override
	public BaseResponse<PmUserMenuResult> getUserMenu(HttpServletRequest request) {
		PmUserMenuResult result = new PmUserMenuResult();
		Object obj = rSessionCache.getUserInfo(request);
		if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		UcPmUserDtoResult user = (UcPmUserDtoResult) obj;
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
	public BaseResponse<PmUserResult> loginByPass(@RequestBody LoginByPassReq requestBody) {
		PmUserResult result = new PmUserResult();
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
	public BaseResponse<Result> createLabel(@RequestBody LabelReq requestBody) {
		Result result = userService.createLabel(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<UcPmLabelListResult> labelList(LabelPageReq requestBody) {
		UcPmLabelListResult result = userService.labelList(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyLabel(@RequestBody LabelModifyReq requestBody) {
		Result result = userService.modifyLabel(requestBody);
		return buildJson(result);
	}

    @Override
    public BaseResponse<LabelListResult> manualLabelList() {
        LabelListResult result = userService.manualLabelList();
        return buildJson(result);
    }

	@Override
	public BaseResponse<Result> exportLabel(LabelPageReq requestBody, HttpServletResponse response) {
		Result result = userService.exportLabel(requestBody,response);
		return buildJson(result);
	}

	@Override
	public BaseResponse<PmUserListResult> queryPmEmployeeInfoList(@RequestBody EmployeeInfoReq userInfoDtoReq) {
		PmUserListResult result = userService.queryPmEmployeeInfoList(userInfoDtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<PmUserResult> queryPmEmployeeInfo(Long userId) {
		PmUserResult result = userService.queryPmEmployeeInfo(userId);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyPmEmployeeInfo(@RequestBody ModifyEmployeeInfoReq userInfoDtoReq) {
		Result result = userService.modifyPmEmployeeInfo(userInfoDtoReq);
		return buildJson(result);
	}

	@Override
	public void exportPmEmployeeInfoList(EmployeeInfoReq req, HttpServletResponse response) {
		userService.exportPmEmployeeInfoList(req,response);
	}

}
