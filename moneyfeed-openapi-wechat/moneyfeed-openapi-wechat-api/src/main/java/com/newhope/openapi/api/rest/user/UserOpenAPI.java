package com.newhope.openapi.api.rest.user;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.newhope.openapi.api.vo.request.user.ModifyUserInfoBySmscodeReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.vo.request.user.LoginByOauthReq;
import com.newhope.openapi.api.vo.request.user.LoginBySmscodeReq;
import com.newhope.openapi.api.vo.request.user.VerdictUserCustomerReq;
import com.newhope.openapi.api.vo.response.user.LoginByOauthResult;
import com.newhope.openapi.api.vo.response.user.LoginByPasswordResult;
import com.newhope.openapi.api.vo.response.user.ShopResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="UserOpenAPI", description="用户中心REST API", protocols="http")
public interface UserOpenAPI {

	
	@ApiOperation(value="用户短信验证码登录", notes="短信验证码错误:MSG_SMS_CODE_ERROR;用户不存在:USER_NOT_EXIST", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="用户注册信息(json格式)", required=true, paramType="body", dataType="LoginBySmscodeReq")
	})
	@RequestMapping(value = "/login/smscode", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<LoginByPasswordResult> loginBySmscode( @RequestBody LoginBySmscodeReq requestBody);

	@ApiOperation(value="三方授权登录登录", notes="授权失败:THIRD_AUTH_FAIL;若在微信客户端使用微信授权登录未关注公众号:THIRD_AUTH_FAIL;三方账户未绑定:THIRD_ACCT_NOT_EXIST", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="用户注册信息(json格式)", required=true, paramType="body", dataType="LoginByOauthReq")
	})
	@RequestMapping(value = "/login/oauth", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<LoginByOauthResult> loginByOauth( @RequestBody LoginByOauthReq requestBody);

	
	@ApiOperation(value="通过微信查询三方账号", notes="授权失败:THIRD_AUTH_FAIL;若在微信客户端使用微信授权登录未关注公众号:THIRD_AUTH_FAIL;三方账户未绑定:THIRD_ACCT_NOT_EXIST", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="用户注册信息(json格式)", required=true, paramType="body", dataType="LoginByOauthReq")
	})
	@RequestMapping(value = "/query/oauth", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<LoginByOauthResult> queryByOauth( @RequestBody LoginByOauthReq requestBody);

	
	@ApiOperation(value="用户登出", notes="用户登出", protocols="http")
	@RequestMapping(value = "/login/out", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<Result> loginOut(HttpServletResponse response);

	
	@ApiOperation(value="获取当前用户缓存信息", notes="获取当前用户缓存信息", protocols="http")
	@RequestMapping(value = "/curr/user/cache", method = RequestMethod.GET, produces={"application/json"})
	public BaseResponse<ClientUserCacheDtoResult> getCurrCacheUserInfo();

	@ApiOperation(value="验证当前用户客户信息", notes="验证当前用户客户信息", protocols="http")
	@RequestMapping(value = "/user/customer/verdict", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<Result> verdictUserCustomer(@RequestBody VerdictUserCustomerReq requestBody );

	
	@ApiOperation(value="选择访问商户", notes="选择访问商户", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="shopId", value="商户id", required=true, paramType="query", dataType="Long")
	})
	@RequestMapping(value = "/user/visit/shop", method = RequestMethod.GET,produces={"application/json"})
	public BaseResponse<ShopResult> visitShop(@RequestParam(value = "shopId", required = true) Long shopId);

	@ApiOperation(value = "用户个人信息修改", notes = "用户个人信息修改", protocols = "http")
	@RequestMapping(value = "/user/info/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeReq requestBody);

}
