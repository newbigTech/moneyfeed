package com.newhope.openapi.api.rest.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "user", description = "商户用户中心", protocols = "http")
public interface BmUserOpenAPI {
	
	@ApiOperation(value = "商户短信验证码登录，登录成功后返回用户信息", notes = "商户短信验证码登录，登录成功后返回用户信息", protocols = "http")
	@RequestMapping(value = "/user/login/smscode", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<BmUserResult> loginBySmscode(@Valid @RequestBody LoginBySmscodeReq requestBody);
	
	@ApiOperation(value = "商户系统菜单获取", notes = "商户系统菜单获取", protocols = "http")
	@RequestMapping(value = "/user/menu", method = RequestMethod.GET)
	public BaseResponse<BmUserMenuResult> getUserMenu(HttpServletRequest request);
	
	@ApiOperation(value = "商户密码登录", notes = "商户密码登录", protocols = "http")
	@RequestMapping(value = "/user/login/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<BmUserResult> loginByPass(@Valid @RequestBody LoginByPassReq requestBody);
	 
	@ApiOperation(value = "重置商户密码登录", notes = "重置商户密码登录", protocols = "http")
	@RequestMapping(value = "/user/reset/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> resetPassWord(@Valid @RequestBody ResetPassWordReq requestBody);

	@ApiOperation(value = "商户个人信息修改", notes = "商户个人信息修改", protocols = "http")
	@RequestMapping(value = "/user/info/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeReq requestBody);

	@ApiOperation(value = "商户员工信息查询", notes = "商户员工信息查询", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "用户姓名", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "mobile", value = "手机号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "Boolean"),
	})
	@RequestMapping(value = "/bm/employee/list", method = RequestMethod.POST)
	public BaseResponse<BmUserListResult> queryBmEmployeeInfoList(@RequestBody EmployeeInfoReq userInfoDtoReq);

	@ApiOperation(value = "根据id查询商户员工信息", notes = "根据id查询商户员工信息", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, paramType = "query", dataType = "Long"),
	})
	@RequestMapping(value = "/bm/employee/info", method = RequestMethod.GET)
	public BaseResponse<BmUserResult> queryBmEmployeeInfo(@RequestParam(value = "userId", required = true) Long userId);

	@ApiOperation(value = "新增商户员工", notes = "新增商户员工", protocols = "http")
	@RequestMapping(value = "/bm/employee/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> addBmEmployeeInfo(@Valid @RequestBody ModifyEmployeeInfoReq userInfoDtoReq);

	@ApiOperation(value = "编辑商户员工", notes = "编辑商户员工", protocols = "http")
	@RequestMapping(value = "/bm/employee/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyBmEmployeeInfo(@Valid @RequestBody ModifyEmployeeInfoReq userInfoDtoReq);

	@ApiOperation(value = "商户员工列表导出", notes = "商户员工列表导出", protocols = "http")
	@RequestMapping(value = "/bm/employee/list/export", method = RequestMethod.GET)
	void exportBmEmployeeInfoList(EmployeeInfoReq req, HttpServletResponse response);

	
	@ApiOperation(value = "获取商户端角色列表", notes = "获取商户端角色列表", protocols = "http")
	@RequestMapping(value = "/bm/role", method = RequestMethod.GET, produces = {"application/json"})
	public BaseResponse<ClientRoleListResult> getClientRole();
	
	
	@ApiOperation(value = "查询商户端角色对应资源列表", notes = "查询商户端角色对应资源列表", protocols = "http")
	@RequestMapping(value = "/bm/resource/query", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<PmResourceResult> getRoleResource(@RequestParam(name="roleId",required=true) Long roleId);
}
