package com.newhope.moneyfeed.api.rest.wechat;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatDtoReq;
import com.newhope.moneyfeed.api.dto.response.wechat.WechatDtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.request.wechat.JsConfigReq;
import com.newhope.moneyfeed.api.vo.response.wechat.JsConfigResult;
import com.newhope.moneyfeed.api.vo.response.wechat.RedirectResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value="Wechat", description="微信API", protocols="http")
public interface WechatAPI {
	
	@ApiOperation(value="微信token认证接入点", notes="微信token认证接入点，用于微信服务回调验证公众号", protocols="http")
	@RequestMapping(value = { "/wechat/accesspoint/moneyfeed/join" }, method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<WechatDtoResult> coreJoinGetJubaozhu(@RequestBody WechatDtoReq wechatDtoReq);
	
	@ApiOperation(value="微信接入POST消息交互接入点", notes="微信接入POST消息交互接入点", protocols="http")
	@RequestMapping(value = { "/wechat/accesspoint/moneyfeed"}, method = RequestMethod.POST)
	public BaseResponse<WechatDtoResult> accessPointJubaozhuPost(@RequestBody WechatDtoReq wechatDtoReq);
	
	@ApiOperation(value="获取JSSDK配置", notes="获取JSSDK配置", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="获取JSSDK配置请求信息(json格式)", required=true, paramType="body", dataType="JsConfigReq")
	})
	@RequestMapping(value = "/wechat/jsconfig", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<JsConfigResult> getJsConfig(@RequestBody JsConfigReq requestBody);
	
	@ApiOperation(value="OAuth", notes="授权接口", protocols="http")
	@RequestMapping(value = "/wechat/oauth", method = RequestMethod.GET)
	public BaseResponse<RedirectResult> oauth();


}