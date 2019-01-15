package com.newhope.openapi.api.rest.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.newhope.openapi.api.vo.request.user.*;
import com.newhope.openapi.api.vo.response.label.LabelListResult;
import com.newhope.openapi.api.vo.response.user.PmUserListResult;
import com.newhope.openapi.api.vo.response.user.UcPmLabelListResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.response.user.PmUserMenuResult;
import com.newhope.openapi.api.vo.response.user.PmUserResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@Api(value = "user", description = "商户用户中心", protocols = "http")
public interface PmUserOpenAPI {
	
	@ApiOperation(value = "商户系统菜单获取", notes = "商户系统菜单获取", protocols = "http")
	@RequestMapping(value = "/user/menu", method = RequestMethod.GET)
	public BaseResponse<PmUserMenuResult> getUserMenu(HttpServletRequest request);
	
	@ApiOperation(value = "商户密码登录", notes = "商户密码登录", protocols = "http")
	@RequestMapping(value = "/user/login/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<PmUserResult> loginByPass(@Valid @RequestBody LoginByPassReq requestBody);
	 
	@ApiOperation(value = "重置商户密码登录", notes = "重置商户密码登录", protocols = "http")
	@RequestMapping(value = "/user/reset/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> resetPassWord(@Valid @RequestBody ResetPassWordReq requestBody);	
	
	@ApiOperation(value = "商户个人信息修改", notes = "商户个人信息修改", protocols = "http")
	@RequestMapping(value = "/user/info/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeReq requestBody);


	@ApiOperation(value = "新建标签", notes = "新建标签", protocols = "http")
	@RequestMapping(value = "/pm/user/label/save", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> createLabel(@Valid @RequestBody LabelReq requestBody);

	@ApiOperation(value = "标签列表查询", notes = "标签列表查询", protocols = "http")
	@RequestMapping(value = "/pm/user/label/list", method = RequestMethod.GET)
	public BaseResponse<UcPmLabelListResult> labelList(LabelPageReq requestBody);

	@ApiOperation(value = "更新标签", notes = "更新标签", protocols = "http")
	@RequestMapping(value = "/pm/user/label/modify", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyLabel(@Valid @RequestBody LabelModifyReq requestBody);

    @ApiOperation(value = "手动标签列表查询", notes = "手动标签列表查询", protocols = "http")
    @RequestMapping(value = "/pm/user/label/manual/list", method = RequestMethod.GET)
    public BaseResponse<LabelListResult> manualLabelList();

	@ApiOperation(value = "标签列表导出", notes = "标签列表导出", protocols = "http")
	@RequestMapping(value = "/pm/user/label/export", method = RequestMethod.GET)
	public BaseResponse<Result> exportLabel(LabelPageReq requestBody, HttpServletResponse response);

	@ApiOperation(value = "商户员工信息查询", notes = "商户员工信息查询", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "用户姓名", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "mobile", value = "手机号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "Boolean"),
	})
	@RequestMapping(value = "/pm/employee/list", method = RequestMethod.POST)
	public BaseResponse<PmUserListResult> queryPmEmployeeInfoList(@RequestBody EmployeeInfoReq userInfoDtoReq);

	@ApiOperation(value = "根据id查询商户员工信息", notes = "根据id查询商户员工信息", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, paramType = "query", dataType = "Long"),
	})
	@RequestMapping(value = "/pm/employee/info", method = RequestMethod.GET)
	public BaseResponse<PmUserResult> queryPmEmployeeInfo(@RequestParam(value = "userId", required = true) Long userId);

	@ApiOperation(value = "编辑商户员工", notes = "编辑商户员工", protocols = "http")
	@RequestMapping(value = "/pm/employee/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyPmEmployeeInfo(@Valid @RequestBody ModifyEmployeeInfoReq userInfoDtoReq);

	@ApiOperation(value = "商户员工列表导出", notes = "商户员工列表导出", protocols = "http")
	@RequestMapping(value = "/pm/employee/list/export", method = RequestMethod.GET)
	void exportPmEmployeeInfoList(EmployeeInfoReq req, HttpServletResponse response);

}
