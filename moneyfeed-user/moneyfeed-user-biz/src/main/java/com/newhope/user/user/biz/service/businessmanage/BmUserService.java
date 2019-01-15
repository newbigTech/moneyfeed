package com.newhope.user.user.biz.service.businessmanage;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.newhope.commons.lang.MD5Utils;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserModel;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.*;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.businessmanage.UcBmUserDao;
import com.newhope.moneyfeed.user.dal.dao.businessmanage.UcBmUserRoleDao;
import com.newhope.user.user.biz.rpc.feign.base.BaseUserFeignClient;
import com.newhope.user.user.biz.rpc.feign.base.SmsFeignClient;
import com.newhope.user.user.biz.service.BaseService;
import com.newhope.user.user.biz.service.client.UcClientShopService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class BmUserService extends BaseService<UcBmUserModel> {

	public static Logger logger = LoggerFactory.getLogger(BmUserService.class);
	
	@Autowired
	UcBmUserDao ucBmUserDao;
	
	@Autowired
	RSmsCache rSmsCache;
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	BmUserRoleService bmUserRoleService;
	
	@Autowired
	UcClientShopService ucClientShopService;

	@Autowired
	UcBmUserRoleDao userRoleDao;
	@Autowired
	BaseUserFeignClient baseUserFeignClient;

	@Autowired
	SmsFeignClient smsFeignClient;
	
	@Override
	protected BaseDao<UcBmUserModel> getDao() {
		return ucBmUserDao;
	}

	/**
	 * 短信验证码登录
	 * @param request
	 */
	public UcBmUserDtoResult loginBySmscode(LoginBySmscodeDtoReq request) {
		UcBmUserDtoResult result = new UcBmUserDtoResult();
		
		UcBmUserModel req = new UcBmUserModel();
		req.setMobile(request.getMobile());
		req.setEnable(true);
		List<UcBmUserModel> userList = query(req);
		if (CollectionUtils.isEmpty(userList)) {	//用户未注册
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return result;
		}
		String smsCode = "";
		try {
			smsCode = rSmsCache.getSmsCode(request.getMobile());
		} catch (Exception e) {
			logger.error("[BmUserService.loginBySmscode]获取短信验证码缓存异常", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
			return result;
		}
		if (!request.getSmscode().equals(smsCode)) {
			result.setCode(ResultCode.USER_LOGIN_SMSCODE_ERROR.getCode());
			result.setMsg(ResultCode.USER_LOGIN_SMSCODE_ERROR.getDesc());
			return result;
		}
		BeanUtils.copyProperties(userList.get(0), result);
		//获取对应的角色信息
		List<UcPmRoleModel> roleModelList = bmUserRoleService.queryRoleInfoByUserId(userList.get(0).getId());
		if(CollectionUtils.isNotEmpty(roleModelList)) {
			UcPmRoleDtoResult roleDto = null;
			for (UcPmRoleModel ucBmRoleModel : roleModelList) {
				roleDto = new UcPmRoleDtoResult();
				BeanUtils.copyProperties(ucBmRoleModel, roleDto);
				result.getRoleDtoList().add(roleDto);
			}
		}

		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}
	/**
	 * 密码登录
	 * @param request
	 */
	public UcBmUserDtoResult loginByPass(LoginByPassDtoReq request) {
		UcBmUserDtoResult result = new UcBmUserDtoResult();
		
		UcBmUserModel req = new UcBmUserModel();
		req.setMobile(request.getMobile());
		req.setEnable(true);
		List<UcBmUserModel> userList = query(req);
		if (CollectionUtils.isEmpty(userList)) {	//用户未注册
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return result;
		}
		//密码验证
		try {
			String pwd = MD5Utils.encodeMD5Hex(MD5Utils.encodeMD5Hex(request.getPassword()));
			if(!userList.get(0).getPassword().equals(pwd)) {
				result.setCode(ResultCode.USER_LOGIN_PASS_ERROR.getCode());
				result.setMsg(ResultCode.USER_LOGIN_PASS_ERROR.getDesc());
				return result;
			}
		} catch (Exception e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
			return result;
		}
		
		BeanUtils.copyProperties(userList.get(0), result);
		//获取对应的角色信息
		List<UcPmRoleModel> roleModelList = bmUserRoleService.queryRoleInfoByUserId(userList.get(0).getId());
		if(CollectionUtils.isNotEmpty(roleModelList)) {
			UcPmRoleDtoResult roleDto = null;
			for (UcPmRoleModel ucBmRoleModel : roleModelList) {
				roleDto = new UcPmRoleDtoResult();
				BeanUtils.copyProperties(ucBmRoleModel, roleDto);
				result.getRoleDtoList().add(roleDto);
			}
		}
		//获取shopName
		if(null != result.getShopId()) {//平台管理员登陆没有shopId
			UcShopModel shopModel = new UcShopModel();
			shopModel.setId(result.getShopId());
			shopModel.setEnable(true);
			List<UcShopModel> shopList = ucClientShopService.query(shopModel);
			if(CollectionUtils.isNotEmpty(shopList)) {
				result.setShopName(shopList.get(0).getName());
				result.setEbsOrgId(shopList.get(0).getEbsOrgId());
			}
		}
		
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}

	public DtoResult resetPassWord(ResetPassWordDtoReq requestBody) {
		DtoResult result = new DtoResult();
		
		UcBmUserModel req = new UcBmUserModel();
		req.setMobile(requestBody.getMobile());
		req.setEnable(true);
		List<UcBmUserModel> userList = query(req);
		if (CollectionUtils.isEmpty(userList)) {	//用户未注册
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return result;
		}
		
		String smsCode = "";
		try {
			smsCode = rSmsCache.getSmsCode(requestBody.getMobile());
		} catch (Exception e) {
			logger.error("[BmUserService.loginBySmscode]获取短信验证码缓存异常", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
			return result;
		}
		if (!requestBody.getSmscode().equals(smsCode)) {
			result.setCode(ResultCode.USER_LOGIN_SMSCODE_ERROR.getCode());
			result.setMsg(ResultCode.USER_LOGIN_SMSCODE_ERROR.getDesc());
			return result;
		}
		
		UcBmUserModel newModel = new UcBmUserModel();
		try {
			String pwd = MD5Utils.encodeMD5Hex(requestBody.getPassWord());
			newModel.setPassword(MD5Utils.encodeMD5Hex(pwd));
			boolean r = this.update(userList.get(0), newModel);
			if(r) {
				result.setCode(ResultCode.SUCCESS.getCode());
				result.setMsg(ResultCode.USER_RESET_PASS_SUCCESS.getDesc());
			}else {
				result.setCode(ResultCode.USER_RESET_PASS_FAIL.getCode());
				result.setMsg(ResultCode.USER_RESET_PASS_FAIL.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
	
		return result;
	}
	
	public UcBmUserListDtoResult queryBmUserByShopId(UserShopQueryDtoReq userShopQueryDtoReq) {
		UcBmUserListDtoResult result = new UcBmUserListDtoResult();
		List<UcBmUserModel> list = ucBmUserDao.queryBmUserByShopId(userShopQueryDtoReq);
		List<UcBmUserDtoResult> userList = new ArrayList<UcBmUserDtoResult>();
		UcBmUserDtoResult dtoResult = null;
		if(CollectionUtils.isNotEmpty(list)) {
			for (UcBmUserModel ucBmUserModel : list) {
				dtoResult = new UcBmUserDtoResult();
				BeanUtils.copyProperties(ucBmUserModel, dtoResult);
				userList.add(dtoResult);
			}
		}
		result.setUserList(userList);
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}


	@Transactional
	public DtoResult modifyBmUserInfo(ModifyUserInfoBySmscodeDtoReq requestBody) {
		DtoResult result = new DtoResult();

		UcBmUserDtoResult userInfo = (UcBmUserDtoResult)rSessionCache.getUserInfo();
		UcBmUserModel oldModelReq = new UcBmUserModel();
		oldModelReq.setId(userInfo.getId());
		//判断原来的手机号是否和现在修改的一致
		UcBmUserModel oldModel = query(oldModelReq).get(0);
		if(!oldModel.getMobile().equals(requestBody.getMobile())) {
			UcBmUserModel req = new UcBmUserModel();
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
				logger.error("[BmUserService.modifyUserInfo]获取短信验证码缓存异常", e);
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
		UcBmUserModel newModel = new UcBmUserModel();
		BeanUtils.copyProperties(requestBody,newModel);
		try {
			boolean r = this.update(oldModel, newModel);
			if(r) {
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

    public UcBmUserListDtoResult queryBmEmployeeInfoList(EmployeeInfoDtoReq employeeInfoDtoReq) {
		UcBmUserListDtoResult result = new UcBmUserListDtoResult();
		List<Long> roleIds = employeeInfoDtoReq.getRoleIds();
		if(CollectionUtils.isNotEmpty(roleIds)) {
			List<Long> userIds = bmUserRoleService.queryUserIdsByRoleIds(roleIds);
			employeeInfoDtoReq.setUserIds(userIds);
		}
		PageBounds pageBounds;
		if (employeeInfoDtoReq.getPage() == null || employeeInfoDtoReq.getPageSize() == null) {
			pageBounds = new PageBounds();
		} else {
			pageBounds = new PageBounds(employeeInfoDtoReq.getPage().intValue(), employeeInfoDtoReq.getPageSize());
		}
		if(employeeInfoDtoReq.getStatus()==null){
            employeeInfoDtoReq.setStatus(true);
        }
		PageList<UcBmUserDtoResult> list = ucBmUserDao.queryBmEmployeeInfoList(employeeInfoDtoReq,pageBounds);
		if (CollectionUtils.isNotEmpty(list)) {
			result.setUserList(list);
		}
		if (list.getPaginator()!= null) {
			result.setPage((long) list.getPaginator().getPage());
			result.setTotalCount((long) list.getPaginator().getTotalCount());
			result.setTotalPage((long) list.getPaginator().getTotalPages());
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
    }

    public UcBmUserDtoResult queryBmEmployeeInfo(Long userId) {
        UcBmUserDtoResult result = new UcBmUserDtoResult();
        EmployeeInfoDtoReq req = new EmployeeInfoDtoReq();
        req.setUserId(userId);
		PageBounds pageBounds = new PageBounds();
        List<UcBmUserDtoResult> list = ucBmUserDao.queryBmEmployeeInfoList(req,pageBounds);
        if(CollectionUtils.isEmpty(list)){
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
            return result;
        }
        result = list.get(0);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
    }

    @Transactional
    public DtoResult addBmEmployeeInfo(ModifyEmployeeInfoDtoReq userInfoDtoReq) {
		DtoResult result = new DtoResult();

		UcBmUserModel req = new UcBmUserModel();
		req.setMobile(userInfoDtoReq.getMobile());
		req.setEnable(true);
		if (CollectionUtils.isNotEmpty(query(req))) {	//手机号已注册
			result.setCode(ResultCode.USER_MOBILE_EXIST.getCode());
			result.setMsg(ResultCode.USER_MOBILE_EXIST.getDesc());
			return result;
		}

		try {
			UcBmUserModel ucBmUserModel = new UcBmUserModel();
			BeanUtils.copyProperties(userInfoDtoReq,ucBmUserModel);
			ucBmUserModel.setEnable(true);
			UcBmUserDtoResult userInfo = (UcBmUserDtoResult)rSessionCache.getUserInfo();
			ucBmUserModel.setShopId(userInfo.getShopId());
			//初始化密码
			String initPassword = getStringRandom(8);
			ucBmUserModel.setPassword(MD5Utils.encodeMD5Hex(MD5Utils.encodeMD5Hex(initPassword)));
			ucBmUserDao.insert(Lists.newArrayList(ucBmUserModel));

			UcBmUserRoleModel ucBmUserRoleModel = new UcBmUserRoleModel();
			ucBmUserRoleModel.setRoleId(userInfoDtoReq.getRoleId());
			ucBmUserRoleModel.setUserId(query(req).get(0).getId());
			ucBmUserRoleModel.setEnable(true);
			userRoleDao.insert(Lists.newArrayList(ucBmUserRoleModel));

			//发送短信
			SmsSendDtoReq dtoReq = new SmsSendDtoReq();
			dtoReq.setMobile(userInfoDtoReq.getMobile());
			dtoReq.setTemplateId(SmsTemplateEnums.SMS_154587033.getTemplateId());
			Map<String, String> paramMap = null == dtoReq.getParamMap() ? new HashMap<>() : dtoReq.getParamMap();
			paramMap.put("password", initPassword);
			dtoReq.setParamMap(paramMap);
			smsFeignClient.sendSms(dtoReq);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("[BmUserService.addBmEmployeeInfo]新增员工信息失败", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
			return result;
		}

		return result;
	}

	@Transactional
	public DtoResult modifyBmEmployeeInfo(ModifyEmployeeInfoDtoReq userInfoDtoReq) {
		DtoResult result = new DtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		UcBmUserModel oldUserModel = new UcBmUserModel();
		oldUserModel.setId(userInfoDtoReq.getId());
		//判断原来的手机号是否和现在修改的一致
		if(!query(oldUserModel).get(0).getMobile().equals(userInfoDtoReq.getMobile())){
			UcBmUserModel req = new UcBmUserModel();
			req.setMobile(userInfoDtoReq.getMobile());
			req.setEnable(true);
			if (CollectionUtils.isNotEmpty(query(req))) {	//手机号已注册
				result.setCode(ResultCode.USER_MOBILE_EXIST.getCode());
				result.setMsg(ResultCode.USER_MOBILE_EXIST.getDesc());
				return result;
			}
		}
		UcBmUserModel oldModel = new UcBmUserModel();
		oldModel.setId(userInfoDtoReq.getId());
		UcBmUserModel newModel = new UcBmUserModel();
		BeanUtils.copyProperties(userInfoDtoReq,newModel);
		//离职状态
		try{
			if(!userInfoDtoReq.getStatus()){
				newModel.setShopId(0L);
				newModel.setEnable(false);
				ucBmUserDao.update(oldModel,newModel);
				UcBmUserRoleModel oldRoleModel = new UcBmUserRoleModel();
				oldRoleModel.setUserId(userInfoDtoReq.getId());
				UcBmUserRoleModel newRoleModel = new UcBmUserRoleModel();
				newRoleModel.setRoleId(userInfoDtoReq.getRoleId());
				newRoleModel.setEnable(false);
				userRoleDao.update(oldRoleModel, newRoleModel);
				//清空登录缓存
				UserRemoveDtoReq removeDtoReq = new UserRemoveDtoReq();
				removeDtoReq.setUserIds(Lists.newArrayList(userInfoDtoReq.getId()));
				removeDtoReq.setThirdSource(SysSourceTypeEnums.BUSINESS.name());
				baseUserFeignClient.removeLoginInfo(removeDtoReq);
				return result;
			}
			ucBmUserDao.update(oldModel,newModel);

			UcBmUserRoleModel roleModelReq = new UcBmUserRoleModel();
			roleModelReq.setUserId(userInfoDtoReq.getId());
			UcBmUserRoleModel ucBmUserRoleModel = userRoleDao.select(roleModelReq).get(0);
			if(!ucBmUserRoleModel.getRoleId().equals(userInfoDtoReq.getRoleId())) {
				UcBmUserRoleModel oldRoleModel = new UcBmUserRoleModel();
				oldRoleModel.setUserId(userInfoDtoReq.getId());
				UcBmUserRoleModel newRoleModel = new UcBmUserRoleModel();
				newRoleModel.setRoleId(userInfoDtoReq.getRoleId());
				userRoleDao.update(oldRoleModel, newRoleModel);
				//清空登录缓存
				UserRemoveDtoReq removeDtoReq = new UserRemoveDtoReq();
				removeDtoReq.setUserIds(Lists.newArrayList(userInfoDtoReq.getId()));
				removeDtoReq.setThirdSource(SysSourceTypeEnums.BUSINESS.name());
				baseUserFeignClient.removeLoginInfo(removeDtoReq);
			}

		} catch (Exception e) {
			logger.error("[BmUserService.modifyBmEmployeeInfo]修改员工信息失败", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
			return result;
		}
		return result;

	}

	//生成随机数字和字母,
	public String getStringRandom(int length) {

		String val = "";
		Random random = new Random();
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
			//输出是小写字母
				int temp = 97;
				val += (char)(random.nextInt(26) + temp);
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
}
