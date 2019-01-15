package com.newhope.moneyfeed.user.api.rest.client;

import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserLoginDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserLoginDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UcClientUserRoleListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(value="ClientUser", description="用户中心API", protocols="http")
public interface ClientUserAPI {

	@ApiOperation(value="用户统一登录接口", notes="用户统一登录接口", protocols="http")
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<ClientUserLoginDtoResult> login(@RequestBody ClientUserLoginDtoReq requestBody);
	
	@ApiOperation(value="根据条件查询用户基础信息", notes="根据条件查询用户基础信息", protocols="http")
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<ClientUserListDtoResult> queryUserInfos(@RequestBody ClientUserQueryDtoReq userDtoReq);
	
	@ApiOperation(value="组装用户缓存信息", notes="组装用户缓存信息", protocols="http")
	@RequestMapping(value = "/user/info/cache", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<ClientUserCacheDtoResult> assemblyUserCache(@RequestParam(value = "userId", required = true) Long userId);

	
	@ApiOperation(value="根据客户查询关联的用户", notes="根据客户查询关联的用户", protocols="http")
	@RequestMapping(value = "/customer/user", method = RequestMethod.GET,produces={"application/json"})
	public BaseResponse<ClientUserListDtoResult> queryCustomerMappingUsers(@RequestParam(value = "customerId", required = true) Long customerId);
	
	
	@ApiOperation(value="根据条件查询用户关联三方账号", notes="根据条件查询用户关联三方账号", protocols="http")
	@RequestMapping(value = "/user/thirdaccount", method = RequestMethod.GET,produces={"application/json"})
	public BaseResponse<ClientUserThirdAccountDtoResult> queryUserThirdAccount(
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "thirdAccount", required = false) String thirdAccount,
			@RequestParam(value = "thirdSource", required = true) String thirdSource);
	
	@ApiOperation(value="根据userI删除用户关联三方账号", notes="根据userI删除用户关联三方账号", protocols="http")
	@RequestMapping(value = "/user/thirdaccount", method = RequestMethod.DELETE,produces={"application/json"})
	public BaseResponse<DtoResult> removeUserThirdAccount(
			@RequestParam(value = "userId", required = true) Long userId);

	@ApiOperation(value = "个人信息修改", notes = "个人信息修改", protocols = "http")
	@RequestMapping(value = "/user/info/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> modifyUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeDtoReq requestBody);

	@ApiOperation(value = "根据角色Id获取对应的userId", notes = "根据角色Id获取对应的userId", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色Id", required = true, paramType = "query", dataType = "Long")
	})
	@RequestMapping(value = "/user/roleid", method = RequestMethod.GET)
	public BaseResponse<UcClientUserRoleListDtoResult> getUserIdByRole(@RequestParam(name="roleId",required=true)Long roleId);

	
	
}
