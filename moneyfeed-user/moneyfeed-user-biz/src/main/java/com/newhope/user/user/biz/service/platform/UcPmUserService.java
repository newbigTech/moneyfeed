package com.newhope.user.user.biz.service.platform;

import java.util.Iterator;
import java.util.List;

import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.commons.lang.MD5Utils;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmResourceModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserModel;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.LoginByPassDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ResetPassWordDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UserMenuDtoResult;
import com.newhope.moneyfeed.user.api.enums.ResourceTypeEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmUserDao;
import com.newhope.user.user.biz.service.BaseService;
import com.newhope.user.user.biz.service.client.UcClientShopService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UcPmUserService extends BaseService<UcPmUserModel> {

	public static Logger logger = LoggerFactory.getLogger(UcPmUserService.class);
	
	@Autowired
	UcPmUserDao ucPmUserDao;
	
	@Autowired
	RSmsCache rSmsCache;
	
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	UcPmUserRoleService bmUserRoleService;


	@Autowired
	UcPmResourceService pmResourceService;
	
	@Autowired
	UcClientShopService ucClientShopService;
	
	@Override
	protected BaseDao<UcPmUserModel> getDao() {
		return ucPmUserDao;
	}

	/**
	 * 密码登录
	 * @param request
	 */
	public UcPmUserDtoResult loginByPass(LoginByPassDtoReq request) {
		UcPmUserDtoResult result = new UcPmUserDtoResult();
		
		UcPmUserModel req = new UcPmUserModel();
		req.setMobile(request.getMobile());
		req.setEnable(true);
		List<UcPmUserModel> userList = query(req);
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
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}

	public UserMenuDtoResult getUserMenu(List<Long> roleIds) {
		UserMenuDtoResult result = new UserMenuDtoResult();
		if(!roleIds.isEmpty()) {
			result.setName("根目录");
			result.setId(0L);
			ResourceQueryDtoReq resourceQueryReq = new ResourceQueryDtoReq();
			resourceQueryReq.setEnable(true);
			resourceQueryReq.setType(ResourceTypeEnums.MENU.name());
			resourceQueryReq.setRoleIds(roleIds);
			List<UcPmResourceModel> menuList = pmResourceService.searchResource(resourceQueryReq);
			result = assemblyUserMenu(result, menuList);
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
		
	}
	
	public UserMenuDtoResult assemblyUserMenu(UserMenuDtoResult menuResult, List<UcPmResourceModel> menuList) {
		Iterator<UcPmResourceModel> iterator = menuList.iterator();
		while (iterator.hasNext()) {
			UcPmResourceModel menu = iterator.next();
			if (menuResult.getId().equals(menu.getParentId())) {
				UserMenuDtoResult subMenu = new UserMenuDtoResult();
				subMenu.setName(menu.getName());
				subMenu.setMenuCode(menu.getCode());
				subMenu.setSort(menu.getSort());
				subMenu.setUrl(menu.getUrl());
				subMenu.setId(menu.getId());
				subMenu.setIcon(menu.getIcon());
				menuResult.getSubMenuList().add(subMenu);
				assemblyUserMenu(subMenu, menuList);
			}
		}
		return menuResult;
	}
	
	public DtoResult resetPassWord(ResetPassWordDtoReq requestBody) {
		DtoResult result = new DtoResult();
		
		UcPmUserModel req = new UcPmUserModel();
		req.setMobile(requestBody.getMobile());
		req.setEnable(true);
		List<UcPmUserModel> userList = query(req);
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
		
		UcPmUserModel newModel = new UcPmUserModel();
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

	@Transactional
    public DtoResult modifyPmUserInfo(ModifyUserInfoBySmscodeDtoReq requestBody) {
		DtoResult result = new DtoResult();

		UcPmUserDtoResult userInfo = (UcPmUserDtoResult)rSessionCache.getUserInfo();
		UcPmUserModel oldModelReq = new UcPmUserModel();
		oldModelReq.setId(userInfo.getId());
		//判断原来的手机号是否和现在修改的一致
		UcPmUserModel oldModel = query(oldModelReq).get(0);
		if(!oldModel.getMobile().equals(requestBody.getMobile())) {
			UcPmUserModel req = new UcPmUserModel();
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

		UcPmUserModel newModel = new UcPmUserModel();
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

	/**
	 * 根据手机号得到有效用户
	 * @param mobile
	 * @return
	 */
	public DtoResult getEnablePmUserInfoByMobile(String mobile) {
		DtoResult result = DtoResult.success();
		UcPmUserModel req = new UcPmUserModel();
		req.setMobile(mobile);
		req.setEnable(true);
		List<UcPmUserModel> userList = query(req);
		if (CollectionUtils.isEmpty(userList)) {	//用户未注册
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return result;
		}
		result.setData(userList.get(0));
		return result;
	}
}
