package com.newhope.user.user.biz.service.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserModel;
import com.newhope.moneyfeed.user.api.bean.client.*;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.dto.request.user.UserLoginDtoReq;
import com.newhope.moneyfeed.api.dto.response.LoginDtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserLoginDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserLoginDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.ThirdSourceEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcClientUserDao;
import com.newhope.user.user.biz.rpc.feign.base.BaseUserFeignClient;
import com.newhope.user.user.biz.service.BaseService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UcClientUserService extends BaseService<UcClientUserModel> {
	
	private static Logger logger = LoggerFactory.getLogger(UcClientUserService.class);
	
	@Autowired
	UcClientUserDao ucClientUserDao;
	
	@Autowired
	UcClientUserThirdAccountService ucClientUserThirdAccountService;
	
	@Autowired
	UcCustomerClientUserMappingService customerUserMappingServie;
	
	@Autowired
	RSmsCache rSmsCache;

    @Autowired
    RSessionCache rSessionCache;
	
	@Autowired
	BaseUserFeignClient baseUserFeignClient;
	
	@Autowired
	UcCustomerService ucCustomerService;
	
	@Override
	protected BaseDao<UcClientUserModel> getDao() {
		return ucClientUserDao;
	}
	
	/**
	 * 用户短信验证码登录
	 * @param request
	 * @return
	 */
	public ClientUserLoginDtoResult loginBySmscode(ClientUserLoginDtoReq request) {
		ClientUserLoginDtoResult result = new ClientUserLoginDtoResult();
		String smsCode = "";
		try {
			smsCode = rSmsCache.getSmsCode(request.getMobile());
		} catch (Exception e) {
			logger.error("[ClientUserService.loginBySmscode]获取短信验证码缓存异常", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
			return result;
		}
		if (!request.getSmscode().equals(smsCode)) {
			result.setCode(ResultCode.MSG_SMS_CODE_ERROR.getCode());
			result.setMsg(ResultCode.MSG_SMS_CODE_ERROR.getDesc());
			return result;
		}
		UcClientUserModel req = new UcClientUserModel();
		req.setMobile(request.getMobile());
		List<UcClientUserModel> userList = query(req);
		if (CollectionUtils.isEmpty(userList)) {	//用户未注册
			//注册用户
			UcClientUserModel newUser = new UcClientUserModel();
			newUser.setMobile(request.getMobile());
			newUser.setEnable(true);
			save(Arrays.asList(newUser));
			result.setUserId(newUser.getId());
		}else{
			//验证对应的客户是否被禁用
			UcCustomerClientUserMappingModel mappingParam = new UcCustomerClientUserMappingModel();
			mappingParam.setClientUserId(userList.get(0).getId());
			mappingParam.setEnable(true);
			List<UcCustomerClientUserMappingModel> mappingList = customerUserMappingServie.query(mappingParam);
			if(CollectionUtils.isNotEmpty(mappingList)){
				UcCustomerModel ucCustomerModel = new UcCustomerModel();
				ucCustomerModel.setId(mappingList.get(0).getCustomerId());
				ucCustomerModel.setEnable(true);
				List<UcCustomerModel> customerList = ucCustomerService.query(ucCustomerModel);
				if(customerList.isEmpty()) {
					result.setCode(ResultCode.CUSTOMER_FORBIDDEN.getCode());
					result.setMsg(ResultCode.CUSTOMER_FORBIDDEN.getDesc());
					return result;
				}
			}
			
			
			if(userList.get(0).getLastLoginTime()==null){
				result.setFirstLogin(true);
			}
			UcClientUserModel oldUser = new UcClientUserModel();
			oldUser.setId(userList.get(0).getId());
			UcClientUserModel newUser = new UcClientUserModel();
			newUser.setLastLoginTime(new Date());
			update(oldUser, newUser);
			result.setUserId(userList.get(0).getId());
		}
		handleUserCustomerMapping(result.getUserId());
		result.setCode(ResultCode.SUCCESS.getCode());
		//如果第三方账号不为空 绑定三方账号
		if(StringUtils.isNotBlank(request.getThirdAccount())&&result.getUserId()!=null){
			UcClientUserThirdAccountModel queryModel = new UcClientUserThirdAccountModel();
			queryModel.setThirdAccount(request.getThirdAccount());
			List<UcClientUserThirdAccountModel>  thirdAcctList = ucClientUserThirdAccountService.query(queryModel);
			if (CollectionUtils.isNotEmpty(thirdAcctList)) {
				if(thirdAcctList.get(0).getUserId().equals(result.getUserId())){
					result.setUserIsSelf(true);
				}
				return result;
			}
			queryModel = new UcClientUserThirdAccountModel();
			queryModel.setThirdSource(request.getThirdSource());
			queryModel.setUserId(result.getUserId());
			thirdAcctList = ucClientUserThirdAccountService.query(queryModel);
			if (CollectionUtils.isNotEmpty(thirdAcctList)) {
				return result;
			}
			UcClientUserThirdAccountModel model = new UcClientUserThirdAccountModel();
			model.setEnable(true);
			model.setNickName(request.getThirdNickName());
			model.setThirdAccount(request.getThirdAccount());
			model.setThirdSource(request.getThirdSource());
			model.setUserId(result.getUserId());
			model.setImgUrl(request.getThirdImg());
			ucClientUserThirdAccountService.save(Arrays.asList(model));
		}
		
		return result;
	}
	
	/**
	 * 绑定用户客户关系
	 * @param userId
	 */
	public void handleUserCustomerMapping(Long userId){
		UcClientUserModel userParam = new UcClientUserModel();
		userParam.setId(userId);
		List<UcClientUserModel> userList = query(userParam);
		if(CollectionUtils.isNotEmpty(userList)){
			//查询是否有用户手机号存在的mapping
			UcCustomerClientUserMappingModel mappingParam = new UcCustomerClientUserMappingModel();
			mappingParam.setClientUserMobile(userList.get(0).getMobile());
			List<UcCustomerClientUserMappingModel> mappingList = customerUserMappingServie.query(mappingParam);
			if(CollectionUtils.isNotEmpty(mappingList)){
				UcClientUserModel newUser = new UcClientUserModel();
				newUser.setName(mappingList.get(0).getClientUserName());
				newUser.setSource(mappingList.get(0).getSource());
				UcClientUserModel oldeUser = new UcClientUserModel();
				oldeUser.setId(userId);
				update(oldeUser, newUser);
				UcCustomerClientUserMappingModel newMapping = new UcCustomerClientUserMappingModel();
				newMapping.setClientUserId(userList.get(0).getId());
				UcCustomerClientUserMappingModel oldMapping = new UcCustomerClientUserMappingModel();
				oldMapping.setClientUserMobile(userList.get(0).getMobile());
				customerUserMappingServie.update(oldMapping, newMapping);
			}
		}
	}
	/**
	 * OAUTH授权方式登录
	 * @param code	授权码
	 * @return
	 */
	public ClientUserLoginDtoResult loginByOauth(ClientUserLoginDtoReq request) {
		ClientUserLoginDtoResult result = new ClientUserLoginDtoResult();
		//根据thirdSource调用不同三方工具获取对应的openid
		if (ThirdSourceEnums.WECHAT.name().equals(request.getThirdSource())) {
			UserLoginDtoReq userLogin = new UserLoginDtoReq();
			userLogin.setAuthcode(request.getAuthcode());
			userLogin.setThirdSource(request.getThirdSource());
			BaseResponse<LoginDtoResult> feignResult = baseUserFeignClient.login(userLogin);
			if(feignResult.isSuccess()&&feignResult.getData()!=null&&StringUtils.isNotBlank(feignResult.getData().getThirdAccount())){
				result.setSubscribeFlag(feignResult.getData().getSubscribeFlag());
				result.setThirdAccount(feignResult.getData().getThirdAccount());
				result.setThirdImg(feignResult.getData().getThirdImg());
				result.setThirdNickName(feignResult.getData().getThirdNickName());
			}else{
				result.setCode(ResultCode.THIRD_AUTH_FAIL.getCode());
				result.setMsg(ResultCode.THIRD_AUTH_FAIL.getDesc());
				return result;
			}
		}
		//根据openId查询数据库判断，如果数据库能查询出对应用户,则返回用户userid
		UcClientUserThirdAccountModel model = new UcClientUserThirdAccountModel();
		model.setThirdSource(request.getThirdSource());
		model.setThirdAccount(result.getThirdAccount());
		List<UcClientUserThirdAccountModel> thirdAcctList = ucClientUserThirdAccountService.query(model);
		if (CollectionUtils.isNotEmpty(thirdAcctList)) {	//存在第三方账户信息
			//验证对应的客户是否被禁用
			UcCustomerClientUserMappingModel mappingParam = new UcCustomerClientUserMappingModel();
			mappingParam.setClientUserId(thirdAcctList.get(0).getUserId());
			mappingParam.setEnable(true);
			List<UcCustomerClientUserMappingModel> mappingList = customerUserMappingServie.query(mappingParam);
			if(CollectionUtils.isNotEmpty(mappingList)){
				UcCustomerModel ucCustomerModel = new UcCustomerModel();
				ucCustomerModel.setId(mappingList.get(0).getCustomerId());
				ucCustomerModel.setEnable(true);
				List<UcCustomerModel> customerList = ucCustomerService.query(ucCustomerModel);
				if(customerList.isEmpty()) {
					result.setCode(ResultCode.CUSTOMER_FORBIDDEN.getCode());
					result.setMsg(ResultCode.CUSTOMER_FORBIDDEN.getDesc());
					return result;
				}
			}
			
			UcClientUserModel oldUser = new UcClientUserModel();
			oldUser.setId(thirdAcctList.get(0).getUserId());
			UcClientUserModel newUser = new UcClientUserModel();
			newUser.setLastLoginTime(new Date());
			update(oldUser, newUser);
			result.setUserId(thirdAcctList.get(0).getUserId());
			handleUserCustomerMapping(result.getUserId());
		} 
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		logger.info("[UcClientUserService.loginByOauth]三方登录返回", JSON.toJSONString(result));
		return result;
	}
	
	public List<UcClientUserModel> searchClientUser(ClientUserQueryDtoReq queryParam) {
		Long start = null;
		if (queryParam.getPage() != null && queryParam.getPageSize() != null) {
			start = (queryParam.getPage() - 1) * queryParam.getPageSize();
		}
		return ucClientUserDao.searchClientUser(queryParam, start, queryParam.getPageSize());
	}

	public Long searchClientUserCount(ClientUserQueryDtoReq queryParam) {
		return ucClientUserDao.searchClientUserCount(queryParam);
	}
	
	public List<UcClientUserModel> queryCustomerMappingUsers(Long customerId) {
		UcCustomerClientUserMappingModel mappingParam = new UcCustomerClientUserMappingModel();
		mappingParam.setCustomerId(customerId);
		mappingParam.setEnable(true);
		List<UcCustomerClientUserMappingModel> mappingList = customerUserMappingServie.query(mappingParam);
		if (CollectionUtils.isNotEmpty(mappingList)) {
			List<Long> userIds = new ArrayList<Long>();
			for (UcCustomerClientUserMappingModel mapping : mappingList) {
				userIds.add(mapping.getClientUserId());
			}
			ClientUserQueryDtoReq userDtoReq = new ClientUserQueryDtoReq();
			userDtoReq.setIds(userIds);
			List<UcClientUserModel> userList = searchClientUser(userDtoReq);
			return userList;
		}
		return null;
	}

	@Transactional
	public DtoResult modifyUserInfo(ModifyUserInfoBySmscodeDtoReq requestBody) {
		DtoResult result = new DtoResult();

        ClientUserCacheDtoResult userCache = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (userCache == null) {
            result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
            result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
            return result;
        }
        UcClientUserModel userInfo = userCache.getUser();
        UcClientUserModel oldModelReq = new UcClientUserModel();
		oldModelReq.setId(userInfo.getId());
		//判断原来的手机号是否和现在修改的一致
        UcClientUserModel oldModel = query(oldModelReq).get(0);
		if(!oldModel.getMobile().equals(requestBody.getMobile())) {
            UcClientUserModel req = new UcClientUserModel();
			req.setMobile(requestBody.getMobile());
			req.setEnable(true);
			if (CollectionUtils.isNotEmpty(query(req))) {    //手机号已注册
				result.setCode(ResultCode.USER_MOBILE_EXIST.getCode());
				result.setMsg(ResultCode.USER_MOBILE_EXIST.getDesc());
				return result;
			}
			String smsCode = "";
			try {
				smsCode = rSmsCache.getSmsCode(requestBody.getMobile());
			} catch (Exception e) {
				logger.error("[ClientUserService.modifyUserInfo]获取短信验证码缓存异常", e);
				result.setCode(ResultCode.FAIL.getCode());
				result.setMsg(ResultCode.FAIL.getDesc());
				return result;
			}
			if (!requestBody.getSmscode().equals(smsCode)) {
				result.setCode(ResultCode.USER_LOGIN_SMSCODE_ERROR.getCode());
				result.setMsg(ResultCode.USER_LOGIN_SMSCODE_ERROR.getDesc());
				return result;
			}
		}
        UcClientUserModel newModel = new UcClientUserModel();
		BeanUtils.copyProperties(requestBody,newModel);
		try {
			boolean r = this.update(oldModel, newModel);
			//修改客户员工关系表中的员工冗余信息
			UcCustomerClientUserMappingModel oldUcCustomerClientUserMappingModel =
					new UcCustomerClientUserMappingModel();
			oldUcCustomerClientUserMappingModel.setClientUserId(oldModel.getId());
			oldUcCustomerClientUserMappingModel.setClientUserMobile(oldModel.getMobile());
			oldUcCustomerClientUserMappingModel.setEnable(true);
			UcCustomerClientUserMappingModel newUcCustomerClientUserMappingModel =
					new UcCustomerClientUserMappingModel();
			newUcCustomerClientUserMappingModel.setClientUserMobile(requestBody.getMobile());
			newUcCustomerClientUserMappingModel.setClientUserName(requestBody.getName());
			boolean updateMappingB = this.customerUserMappingServie.
					update(oldUcCustomerClientUserMappingModel,
							newUcCustomerClientUserMappingModel);

			if(r && updateMappingB) {
				result.setCode(ResultCode.SUCCESS.getCode());
				result.setMsg(ResultCode.SUCCESS.getDesc());
			}else {
				result.setCode(ResultCode.USER_INFO_MODIFY_FAIL.getCode());
				result.setMsg(ResultCode.USER_INFO_MODIFY_FAIL.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return result;
	}
}