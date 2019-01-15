package com.newhope.openapi.web.controller.user;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.newhope.openapi.api.vo.request.user.ModifyUserInfoBySmscodeReq;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.user.UserLoginDtoReq;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.response.LoginDtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.log.AliyunLog;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.common.vo.AliyunLogDataBean;
import com.newhope.moneyfeed.common.vo.AliyunLogDataBean.Builder;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserLoginDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserLoginDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UserVisitShopDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.AllowOnlineBusinessEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.moneyfeed.user.api.enums.client.ThirdSourceEnums;
import com.newhope.openapi.api.rest.user.UserOpenAPI;
import com.newhope.openapi.api.vo.request.user.LoginByOauthReq;
import com.newhope.openapi.api.vo.request.user.LoginBySmscodeReq;
import com.newhope.openapi.api.vo.request.user.VerdictUserCustomerReq;
import com.newhope.openapi.api.vo.response.user.LoginByOauthResult;
import com.newhope.openapi.api.vo.response.user.LoginByPasswordResult;
import com.newhope.openapi.api.vo.response.user.ShopResult;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.CustomerFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;
import com.newhope.openapi.biz.rpc.feign.wechat.BaseUserFeignClient;
import com.newhope.openapi.biz.service.user.UserService;

@RestController
public class UserOpenController extends AbstractController implements UserOpenAPI {

	private static Logger logger = LoggerFactory.getLogger(UserOpenController.class);
	@Autowired
	ClientUserFeignClient clientUserFeignClient;

	@Autowired
	ShopFeignClient shopFeignClient;

	@Autowired
	BaseUserFeignClient baseUserFeignClient;
	
	@Autowired
	RSessionCache rSessionCache;

	@Autowired
	UserService userService;
	
	@Autowired
	CustomerFeignClient customerFeignClient;

	@Override
	public BaseResponse<LoginByPasswordResult> loginBySmscode(@RequestBody LoginBySmscodeReq requestBody) {
		LoginByPasswordResult result = new LoginByPasswordResult();
		ClientUserLoginDtoReq dtoReq = new ClientUserLoginDtoReq();
		dtoReq.setMobile(requestBody.getMobile());
		dtoReq.setSmscode(requestBody.getSmscode());
		dtoReq.setThirdAccount(requestBody.getThirdAccount());
		dtoReq.setThirdImg(requestBody.getThirdImg());
		dtoReq.setThirdNickName(requestBody.getThirdNickName());
		dtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
		BaseResponse<ClientUserLoginDtoResult> feignResult = clientUserFeignClient.login(dtoReq);
		if (feignResult.isSuccess() && feignResult.getData().getUserId() != null) {
			if (feignResult.getData().getUserId() != null) {
				result.setFirstLogin(feignResult.getData().getFirstLogin());
				Integer timeout = null;
				if(!feignResult.getData().getUserIsSelf()){
					timeout = 30*60;
				}
				logger.info(feignResult.getData().getUserId()+"cookie过期时间"+timeout);
				ClientUserCacheDtoResult cacheReuslt =  userService.cacheUserAggrInfo(feignResult.getData().getUserId(),
						CommonConstant.HTTP_HEADER_SOURCE_WECHAT,timeout);
				
				//埋点
				Builder builder = new AliyunLogDataBean.Builder();
				builder.userId(feignResult.getData().getUserId() + "");
//				builder.openId(val);
				builder.eventType("C00001");
				builder.createTime(System.currentTimeMillis());
//				builder.clientIp(val);
				builder.successFlag(1);
//				builder.errDesc(val);
//				builder.attr1(val);
//				builder.inParam(val);
				AliyunLog.log(builder);
				
				
				result.setCode(cacheReuslt.getCode());
				result.setMsg(cacheReuslt.getMsg());
				return buildJson(result);
			}
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<LoginByOauthResult> loginByOauth(@RequestBody LoginByOauthReq requestBody) {
		LoginByOauthResult result = new LoginByOauthResult();
		ClientUserLoginDtoReq dtoReq = new ClientUserLoginDtoReq();
		dtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
		dtoReq.setAuthcode(requestBody.getAuthcode());
		BaseResponse<ClientUserLoginDtoResult> feignResult = clientUserFeignClient.login(dtoReq);
		if (feignResult.isSuccess() && feignResult.getData() != null) {
			result.setSubscribeFlag(feignResult.getData().getSubscribeFlag());
			result.setThirdAccount(feignResult.getData().getThirdAccount());
			result.setThirdImg(feignResult.getData().getThirdImg());
			result.setThirdNickName(feignResult.getData().getThirdNickName());
			result.setUserId(feignResult.getData().getUserId());
			if (feignResult.getData().getUserId() != null) {
				ClientUserCacheDtoResult cacheReuslt = userService.cacheUserAggrInfo(feignResult.getData().getUserId(),
						CommonConstant.HTTP_HEADER_SOURCE_WECHAT,null);
				result.setCode(cacheReuslt.getCode());
				result.setMsg(cacheReuslt.getMsg());
				return buildJson(result);
			}
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<ShopResult> visitShop(@RequestParam(value = "shopId", required = true) Long shopId) {
		ShopResult result = new ShopResult();
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		List<UcShopModel> shopList = userInfo.getCustomer().getShopList();
		if(CollectionUtils.isNotEmpty(shopList)){
			for(UcShopModel shop:shopList){
				if(shop.getId().equals(shopId)){
					UserVisitShopDtoResult visitShop = new UserVisitShopDtoResult();
					visitShop.setShop(shop);
					ShopCustomerPropertyQueryDtoReq propertyParam = new ShopCustomerPropertyQueryDtoReq();
					propertyParam.setCustomerId(userInfo.getCustomer().getCustomer().getId());
					propertyParam.setShopId(shopId);
					BaseResponse<ShopCustomerPropertyListResult> propertyResult =  shopFeignClient.queryShopCustomerProperty(propertyParam);
					Map<String,UcShopCustomerPropertyModel> propertysMap = Maps.newHashMap();
					if(propertyResult.isSuccess()){
						if( CollectionUtils.isNotEmpty( propertyResult.getData().getPropertys()) ){
							for( UcShopCustomerPropertyModel property : propertyResult.getData().getPropertys() ){
								if(CustomerPropertyTypeEnums.ALLOW_ONLINE_BUSINESS.getValue().equals(property.getName())){
									if(AllowOnlineBusinessEnums.UN_ALLOW.getValue().equals(property.getValue())){
										result.setName(shop.getName());
										result.setContactPerson(shop.getContactPerson());
										result.setContactTel(shop.getContactTel());
										result.setCode(ResultCode.SHOP_COLSE_CUSTOMER_BUSINESS.getCode());
										result.setMsg(ResultCode.SHOP_COLSE_CUSTOMER_BUSINESS.getDesc());
										return bulidJsonWithData(result);
									}
								}
								propertysMap.put(property.getName(), property);
							}
						}
					}
					visitShop.setShopCustomerRules(propertysMap);
					userInfo.setVisitShop(visitShop);
					rSessionCache.setUserInfo(ContextHolderUtil.getRequest().getHeader(CommonConstant.HTTP_HEADER_TOKEN), userInfo);
				}
			}
		}else{
			result.setCode(ResultCode.DATA_ERROR.getCode());
			result.setMsg(ResultCode.DATA_ERROR.getDesc());
			return buildJson(result);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyUserInfo(@Valid @RequestBody ModifyUserInfoBySmscodeReq requestBody) {
		Result result = new Result();
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		
		result = userService.modifyUserInfo(requestBody);
		if(result.getCode().intValue() == ResultCode.SUCCESS.getCode().intValue()  
				&& !userInfo.getUser().getMobile().equals(requestBody.getMobile())) {
			UserRemoveDtoReq userRemoveDtoReq = new UserRemoveDtoReq();
			userRemoveDtoReq.setUserIds(Arrays.asList(userInfo.getUser().getId()));
			userRemoveDtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
			baseUserFeignClient.removeLoginInfo(userRemoveDtoReq);
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<ClientUserCacheDtoResult> getCurrCacheUserInfo() {
		ClientUserCacheDtoResult result = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (result == null) {
			result = new ClientUserCacheDtoResult();
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> loginOut(HttpServletResponse response) {
		Result result = new Result();
		ClientUserCacheDtoResult user = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (user == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}else {
//			Long userId = user.getUser().getId();
			if (rSessionCache.removeCookie(response)) {
//				clientUserFeignClient.removeUserThirdAccount(userId);
				result.setCode(ResultCode.SUCCESS.getCode());
				result.setMsg(ResultCode.SUCCESS.getDesc());
			} else {
				result.setCode(ResultCode.FAIL.getCode());
				result.setMsg(ResultCode.FAIL.getDesc());
			}
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> verdictUserCustomer(@RequestBody VerdictUserCustomerReq requestBody) {
		Result result = new Result();
		ClientUserCacheDtoResult user = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (user == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return buildJson(result);
		}
		if(!user.getCustomer().getCustomer().getCardNum().equals(requestBody.getCardNo())){
			result.setCode(ResultCode.USER_CARD_ERROR.getCode());
			result.setMsg(ResultCode.USER_CARD_ERROR.getDesc());
			return buildJson(result);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result);
	}

	@Override
	public BaseResponse<LoginByOauthResult> queryByOauth(@RequestBody LoginByOauthReq requestBody) {
		LoginByOauthResult result = new LoginByOauthResult();
		/*ClientUserLoginDtoReq dtoReq = new ClientUserLoginDtoReq();
		dtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
		dtoReq.setAuthcode(requestBody.getAuthcode());
		BaseResponse<ClientUserLoginDtoResult> feignResult = clientUserFeignClient.login(dtoReq);
		if (feignResult.isSuccess() && feignResult.getData() != null) {
			result.setSubscribeFlag(feignResult.getData().getSubscribeFlag());
			result.setThirdAccount(feignResult.getData().getThirdAccount());
			result.setThirdImg(feignResult.getData().getThirdImg());
			result.setThirdNickName(feignResult.getData().getThirdNickName());
			result.setUserId(feignResult.getData().getUserId());
			if(feignResult.getData().getUserId()!=null){
				BaseResponse<ClientUserCacheDtoResult> cacheResult = clientUserFeignClient.assemblyUserCache(feignResult.getData().getUserId());
				if (cacheResult.isSuccess() && cacheResult.getData() != null) {
					if(cacheResult.getData().getCustomer()!=null&&cacheResult.getData().getCustomer().getCustomer()!=null){
						result.setCustomerId(cacheResult.getData().getCustomer().getCustomer().getId());
					}
				}
			}
		}*/
		
		UserLoginDtoReq userLogin = new UserLoginDtoReq();
		userLogin.setAuthcode(requestBody.getAuthcode());
		userLogin.setThirdSource(requestBody.getThirdSource());
		BaseResponse<LoginDtoResult> feignResult = baseUserFeignClient.login(userLogin);
		if(feignResult.isSuccess()&&feignResult.getData()!=null&&StringUtils.isNotBlank(feignResult.getData().getThirdAccount())){
			result.setSubscribeFlag(feignResult.getData().getSubscribeFlag());
			result.setThirdAccount(feignResult.getData().getThirdAccount());
			result.setThirdImg(feignResult.getData().getThirdImg());
			result.setThirdNickName(feignResult.getData().getThirdNickName());
		}else{
			result.setCode(ResultCode.THIRD_AUTH_FAIL.getCode());
			result.setMsg(ResultCode.THIRD_AUTH_FAIL.getDesc());
		}
		//根据openId查询数据库判断，如果数据库能查询出对应用户,则返回用户userid
		BaseResponse<ClientUserThirdAccountDtoResult> thirdAccountFeignResult = clientUserFeignClient.queryUserThirdAccount(null, result.getThirdAccount(), requestBody.getThirdSource());
		if (thirdAccountFeignResult.isSuccess() && null != thirdAccountFeignResult.getData() && null != thirdAccountFeignResult.getData().getAccount()) {	//存在第三方账户信息
			Long userId = thirdAccountFeignResult.getData().getAccount().getUserId();
			result.setUserId(userId);
			//查询该user对应的customer
			BaseResponse<CustomerDtoResult> customerFeignResult = customerFeignClient.getCustomerInfoByUser(userId);
			if(customerFeignResult.isSuccess() && null != customerFeignResult.getData()) {
				result.setCustomerId(customerFeignResult.getData().getId());
			}
		} 
		
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

}
