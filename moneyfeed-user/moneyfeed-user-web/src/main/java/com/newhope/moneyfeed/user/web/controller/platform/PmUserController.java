package com.newhope.moneyfeed.user.web.controller.platform;

import java.util.List;

import com.newhope.moneyfeed.user.api.dto.request.platform.LabelDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelListDtoResult;
import com.newhope.user.user.biz.service.platform.PmLabelService;
import com.newhope.user.user.biz.service.platform.UcPmUserRoleService;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.LoginByPassDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ResetPassWordDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.UserInfoDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UserMenuDtoResult;
import com.newhope.moneyfeed.user.api.rest.platform.PmUserAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.platform.UcPmUserService;

@RestController
public class PmUserController extends AbstractController implements PmUserAPI {
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	private UcPmUserService pmUserService;

	@Autowired
	PmLabelService pmLabelService;
	
	@Autowired
	UcPmUserRoleService ucPmUserRoleService;
	
	@Override
	public BaseResponse<UserMenuDtoResult> getUserMenu(@RequestBody UserInfoDtoReq userInfoDtoReq) {
		UserMenuDtoResult result = new UserMenuDtoResult();
		
		UcPmUserModel model = new UcPmUserModel();
		model.setEnable(true);
		model.setId(userInfoDtoReq.getUserId());
		List<UcPmUserModel> userList = pmUserService.query(model);
		if(CollectionUtils.isEmpty(userList)) {
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return buildJson(result.getCode(), result.getMsg(), result);
		}
		UcPmUserModel user = userList.get(0);
		if(!user.getMobile().equals(userInfoDtoReq.getMobile())) {
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return buildJson(result.getCode(), result.getMsg(), result); 
		}
		result = pmUserService.getUserMenu(userInfoDtoReq.getRoleIds());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcPmUserDtoResult> loginByPass(@RequestBody LoginByPassDtoReq requestBody) {
		UcPmUserDtoResult result = pmUserService.loginByPass(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> resetPassWord(@RequestBody ResetPassWordDtoReq requestBody) {
		DtoResult result = pmUserService.resetPassWord(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> createLabel(@RequestBody LabelDtoReq requestBody) {
		DtoResult result = pmLabelService.createLabel(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcPmLabelListDtoResult> labelList(@RequestBody LabelPageDtoReq requestBody) {
		UcPmLabelListDtoResult result = pmLabelService.labelList(requestBody);

		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> modifyLabel(@RequestBody LabelModifyDtoReq requestBody) {
		DtoResult result = pmLabelService.modifyLabel(requestBody);

		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> modifyPmUserInfo(@RequestBody ModifyUserInfoBySmscodeDtoReq requestBody) {
		DtoResult result = pmUserService.modifyPmUserInfo(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcPmUserRoleListDtoResult> getUserIdByRole(@RequestParam(name="roleId",required=true)Long roleId) {
		UcPmUserRoleListDtoResult result = new UcPmUserRoleListDtoResult();
		UcPmUserRoleModel ucPmUserRole = new UcPmUserRoleModel();
		ucPmUserRole.setRoleId(roleId);
		ucPmUserRole.setEnable(true);
		List<UcPmUserRoleModel> list = ucPmUserRoleService.query(ucPmUserRole);
		if(!list.isEmpty()) {
			result.setUserRoleList(list);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> getEnablePmUserInfoByMobile(String mobile) {
		DtoResult result = this.pmUserService.getEnablePmUserInfoByMobile(mobile);
		return buildJson(result.getCode(), result.getMsg(), result);
	}
}
