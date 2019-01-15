package com.newhope.moneyfeed.user.api.rest.platform;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.LoginByPassDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ResetPassWordDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.UserInfoDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UserMenuDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "PmUser", description = "平台中心REST API", protocols = "http")
public interface PmUserAPI {

	@ApiOperation(value = "商户系统菜单获取", notes = "商户系统菜单获取", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户Id", required = true, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/pm/user/menu", method = RequestMethod.POST)
	public BaseResponse<UserMenuDtoResult> getUserMenu(@Valid @RequestBody UserInfoDtoReq userInfoDtoReq);

	@ApiOperation(value = "商户密码登录", notes = "商户密码登录", protocols = "http")
	@RequestMapping(value = "/pm/user/login/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<UcPmUserDtoResult> loginByPass(@Valid @RequestBody LoginByPassDtoReq requestBody);


	@ApiOperation(value = "重置商户密码登录", notes = "重置商户密码登录", protocols = "http")
	@RequestMapping(value = "/pm/user/reset/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> resetPassWord(@Valid @RequestBody ResetPassWordDtoReq requestBody);

	@ApiOperation(value = "商户个人信息修改", notes = "商户个人信息修改", protocols = "http")
	@RequestMapping(value = "/pm/user/info/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> modifyPmUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeDtoReq requestBody);

	@ApiOperation(value = "新建标签", notes = "新建标签", protocols = "http")
	@RequestMapping(value = "/pm/user/label/save", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> createLabel(@Valid @RequestBody LabelDtoReq requestBody);

	@ApiOperation(value = "标签列表查询", notes = "标签列表查询", protocols = "http")
	@RequestMapping(value = "/pm/user/label/list", method = RequestMethod.POST)
	public BaseResponse<UcPmLabelListDtoResult> labelList(@RequestBody LabelPageDtoReq requestBody);

	@ApiOperation(value = "更新标签", notes = "更新标签", protocols = "http")
	@RequestMapping(value = "/pm/user/label/modify", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> modifyLabel(@Valid @RequestBody LabelModifyDtoReq requestBody);

	@ApiOperation(value = "根据角色Id获取对应的userId", notes = "根据角色Id获取对应的userId", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色Id", required = true, paramType = "query", dataType = "Long")
	})
	@RequestMapping(value = "/pm/user/roleid", method = RequestMethod.GET)
	public BaseResponse<UcPmUserRoleListDtoResult> getUserIdByRole(@RequestParam(name="roleId",required=true)Long roleId);

	@ApiOperation(value = "获取有效商户个人信息", notes = "获取有效商户个人信息", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/pm/user/getEnablePmUserInfoByMobile", method = RequestMethod.GET)
	public BaseResponse<DtoResult> getEnablePmUserInfoByMobile(@RequestParam(name="mobile",required=true)String mobile);
}
