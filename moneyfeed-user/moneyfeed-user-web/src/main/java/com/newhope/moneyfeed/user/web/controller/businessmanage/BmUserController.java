package com.newhope.moneyfeed.user.web.controller.businessmanage;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserModel;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.*;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UserMenuDtoResult;
import com.newhope.moneyfeed.user.api.rest.businessmanage.BmUserAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.businessmanage.BmUserRoleService;
import com.newhope.user.user.biz.service.businessmanage.BmUserService;
import com.newhope.user.user.biz.service.platform.UcPmUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BmUserController extends AbstractController implements BmUserAPI {
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	private BmUserService bmUserService;
	
	@Autowired
	private UcPmUserService pmUserService;
	
	@Autowired
	private BmUserRoleService bmUserRoleService;
	
	@Override
	public BaseResponse<UcBmUserDtoResult> loginBySmscode(@RequestBody LoginBySmscodeDtoReq requestBody) {
		UcBmUserDtoResult result = bmUserService.loginBySmscode(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UserMenuDtoResult> getUserMenu(@RequestBody UserInfoDtoReq userInfoDtoReq) {
		UserMenuDtoResult result = new UserMenuDtoResult();
		
		UcBmUserModel model = new UcBmUserModel();
		model.setEnable(true);
		model.setId(userInfoDtoReq.getUserId());
		List<UcBmUserModel> userList = bmUserService.query(model);
		if(CollectionUtils.isEmpty(userList)) {
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return buildJson(result.getCode(), result.getMsg(), result);
		}
		UcBmUserModel user = userList.get(0);
		if(!user.getMobile().equals(userInfoDtoReq.getMobile())) {
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return buildJson(result.getCode(), result.getMsg(), result); 
		}
		result = pmUserService.getUserMenu(userInfoDtoReq.getRoleIds());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcBmUserDtoResult> loginByPass(@RequestBody LoginByPassDtoReq requestBody) {
		UcBmUserDtoResult result = bmUserService.loginByPass(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> resetPassWord(@RequestBody ResetPassWordDtoReq requestBody) {
		DtoResult result = bmUserService.resetPassWord(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> modifyBmUserInfo(@RequestBody ModifyUserInfoBySmscodeDtoReq requestBody) {
		DtoResult result = bmUserService.modifyBmUserInfo(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcBmUserListDtoResult> queryBmEmployeeInfoList(@RequestBody EmployeeInfoDtoReq userInfoDtoReq) {
        UcBmUserListDtoResult result = bmUserService.queryBmEmployeeInfoList(userInfoDtoReq);
        return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcBmUserDtoResult> queryBmEmployeeInfo(Long userId) {
		UcBmUserDtoResult result = bmUserService.queryBmEmployeeInfo(userId);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> addBmEmployeeInfo(@RequestBody ModifyEmployeeInfoDtoReq userInfoDtoReq) {
		DtoResult result = bmUserService.addBmEmployeeInfo(userInfoDtoReq);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> modifyBmEmployeeInfo(@RequestBody ModifyEmployeeInfoDtoReq userInfoDtoReq) {
		DtoResult result = bmUserService.modifyBmEmployeeInfo(userInfoDtoReq);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcBmUserRoleListDtoResult> getUserIdByRole(@RequestParam(name="roleId",required=true)Long roleId) {
		UcBmUserRoleListDtoResult result = new UcBmUserRoleListDtoResult();
		UcBmUserRoleModel userRoleModel = new UcBmUserRoleModel();
		userRoleModel.setRoleId(roleId);
		userRoleModel.setEnable(true);
		List<UcBmUserRoleModel> list = bmUserRoleService.query(userRoleModel);
		if(!list.isEmpty()) {
			result.setUserRoleList(list);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		return buildJson(result.getCode(), result.getMsg(), result);
	}


}
