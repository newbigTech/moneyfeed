package com.newhope.moneyfeed.user.api.rest.businessmanage;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.*;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UserMenuDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Validated
@Api(value = "BmUser", description = "商户中心REST API", protocols = "http")
public interface BmUserAPI {
	
	@ApiOperation(value = "商户短信验证码登录", notes = "商户短信验证码登录", protocols = "http")
	@RequestMapping(value = "/bm/user/login/smscode", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<UcBmUserDtoResult> loginBySmscode(@Valid @RequestBody LoginBySmscodeDtoReq requestBody);
	
	@ApiOperation(value = "商户系统菜单获取", notes = "商户系统菜单获取", protocols = "http")
	 @ApiImplicitParams({
         @ApiImplicitParam(name = "userId", value = "用户Id", required = true, paramType = "query", dataType = "Long"),
         @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query", dataType = "String")
	 })
	@RequestMapping(value = "/bm/user/menu", method = RequestMethod.POST)
	public BaseResponse<UserMenuDtoResult> getUserMenu(@Valid @RequestBody UserInfoDtoReq userInfoDtoReq);
	
	@ApiOperation(value = "商户密码登录", notes = "商户密码登录", protocols = "http")
	@RequestMapping(value = "/bm/user/login/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<UcBmUserDtoResult> loginByPass(@Valid @RequestBody LoginByPassDtoReq requestBody);
	 
	@ApiOperation(value = "重置商户密码登录", notes = "重置商户密码登录", protocols = "http")
	@RequestMapping(value = "/bm/user/reset/pass", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> resetPassWord(@Valid @RequestBody ResetPassWordDtoReq requestBody);

	@ApiOperation(value = "商户个人信息修改", notes = "商户个人信息修改", protocols = "http")
	@RequestMapping(value = "/bm/user/info/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> modifyBmUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeDtoReq requestBody);

	@ApiOperation(value = "商户员工信息查询", notes = "商户员工信息查询", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "用户姓名", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "mobile", value = "手机号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "Boolean"),
			@ApiImplicitParam(name = "shopId", value = "商户id", required = false, paramType = "query", dataType = "Long"),
	})
	@RequestMapping(value = "/bm/employee/list", method = RequestMethod.POST)
	public BaseResponse<UcBmUserListDtoResult> queryBmEmployeeInfoList(@RequestBody EmployeeInfoDtoReq userInfoDtoReq);

    @ApiOperation(value = "根据id查询商户员工信息", notes = "根据id查询商户员工信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = false, paramType = "query", dataType = "Long"),
    })
    @RequestMapping(value = "/bm/employee/info", method = RequestMethod.GET)
    public BaseResponse<UcBmUserDtoResult> queryBmEmployeeInfo(@RequestParam(value = "userId", required = true) Long userId);

    @ApiOperation(value = "新增商户员工", notes = "新增商户员工", protocols = "http")
    @RequestMapping(value = "/bm/employee/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> addBmEmployeeInfo(@Valid @RequestBody ModifyEmployeeInfoDtoReq userInfoDtoReq);

    @ApiOperation(value = "编辑商户员工", notes = "编辑商户员工", protocols = "http")
    @RequestMapping(value = "/bm/employee/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> modifyBmEmployeeInfo(@Valid @RequestBody ModifyEmployeeInfoDtoReq userInfoDtoReq);

    @ApiOperation(value = "根据角色Id获取对应的userId", notes = "根据角色Id获取对应的userId", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色Id", required = true, paramType = "query", dataType = "Long")
	})
	@RequestMapping(value = "/bm/user/roleid", method = RequestMethod.GET)
	public BaseResponse<UcBmUserRoleListDtoResult> getUserIdByRole(@RequestParam(name="roleId",required=true)Long roleId);


}
