package com.newhope.moneyfeed.user.web.controller.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmResourceModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserLoginDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserLoginDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerExDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UcClientUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.ResourceDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.ShopStatusEnums;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.moneyfeed.user.api.rest.client.ClientUserAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.UcClientShopService;
import com.newhope.user.user.biz.service.client.UcClientUserRoleService;
import com.newhope.user.user.biz.service.client.UcClientUserService;
import com.newhope.user.user.biz.service.client.UcClientUserThirdAccountService;
import com.newhope.user.user.biz.service.client.UcCustomerClientUserMappingService;
import com.newhope.user.user.biz.service.client.UcCustomerService;
import com.newhope.user.user.biz.service.platform.UcPmResourceService;
import com.newhope.user.user.biz.service.platform.UcPmRoleService;

@RestController
public class ClientUserController extends AbstractController implements ClientUserAPI {

	@Autowired
	UcClientUserService clientUserService;

	@Autowired
	UcCustomerClientUserMappingService customerUserMappingServie;

	@Autowired
	UcClientUserThirdAccountService clientUserThirdAccountService;
	
	@Autowired
	UcCustomerService ucCustomerService;
	
	@Autowired
	private UcClientShopService shopService;
	
	@Autowired
	private UcPmRoleService ucPmRoleService;
	
	@Autowired
	UcClientUserRoleService ucClientUserRoleService;
	
	@Autowired
	UcPmResourceService ucPmResourceService;
	
	
	@Override
	public BaseResponse<ClientUserLoginDtoResult> login(@RequestBody ClientUserLoginDtoReq requestBody) {
		ClientUserLoginDtoResult result = null;
		if (StringUtils.isNotBlank(requestBody.getAuthcode())) { // 三方授权登录
			result = clientUserService.loginByOauth(requestBody);
		} else if (StringUtils.isNotBlank(requestBody.getSmscode())) { // 短信验证码登录
			result = clientUserService.loginBySmscode(requestBody);
		} else { // 登录失败
			result = new ClientUserLoginDtoResult();
			result.setCode(ResultCode.USER_LOGIN_FAIL.getCode());
			result.setMsg(ResultCode.USER_LOGIN_FAIL.getDesc());
		}
		//判断是否有客户关联
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<ClientUserListDtoResult> queryUserInfos(@RequestBody ClientUserQueryDtoReq userDtoReq) {
		ClientUserListDtoResult result = new ClientUserListDtoResult();
		List<UcClientUserModel> dataList = clientUserService.searchClientUser(userDtoReq);
		if (CollectionUtils.isNotEmpty(dataList)) {
			result.setUsers(dataList);
			result.setPage(userDtoReq.getPage());
			result.setTotalCount(clientUserService.searchClientUserCount(userDtoReq));
			result.setTotalPage(clientUserService.pages(result.getTotalCount()));
		} else {
			result.setTotalCount(0L);
			result.setTotalPage(0L);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}


	@Override
	public BaseResponse<ClientUserListDtoResult> queryCustomerMappingUsers(@RequestParam(value = "customerId", required = true) Long customerId) {
		ClientUserListDtoResult result = new ClientUserListDtoResult();
		result.setUsers(clientUserService.queryCustomerMappingUsers(customerId));
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<ClientUserCacheDtoResult> assemblyUserCache(@RequestParam(value = "userId", required = true) Long userId) {
		ClientUserCacheDtoResult result = new ClientUserCacheDtoResult();
		ClientUserQueryDtoReq userDtoReq = new ClientUserQueryDtoReq();
		userDtoReq.setId(userId);
		List<UcClientUserModel> dataList = clientUserService.searchClientUser(userDtoReq);
		if (CollectionUtils.isNotEmpty(dataList)) {
			UcClientUserModel user = new UcClientUserModel();
			user.setMobile(dataList.get(0).getMobile());
			user.setId(dataList.get(0).getId());
			user.setName(dataList.get(0).getName());
			user.setSource(dataList.get(0).getSource());
			user.setLastLoginTime(dataList.get(0).getLastLoginTime());
			result.setUser(user);
			
			//获取对应角色
			UcClientUserRoleModel userRoleModel = new UcClientUserRoleModel();
			userRoleModel.setUserId(user.getId());
			userRoleModel.setEnable(true);
			List<UcClientUserRoleModel> userRoleList = ucClientUserRoleService.query(userRoleModel);
			if(CollectionUtils.isNotEmpty(userRoleList)) {
				UcPmRoleModel roleModel = new UcPmRoleModel();
				roleModel.setId(userRoleList.get(0).getRoleId());
				roleModel.setEnable(true);
				roleModel.setSourceType(SysSourceTypeEnums.CLIENT.getValue());
				List<UcPmRoleModel> roleList = ucPmRoleService.query(roleModel);
				if(CollectionUtils.isNotEmpty(roleList)) {
					UcPmRoleDtoResult role = new UcPmRoleDtoResult();
					role.setCode(roleList.get(0).getCode());
					role.setName(roleList.get(0).getName());
					role.setComment(roleList.get(0).getComment());
					role.setId(roleList.get(0).getId());
					
					result.setRole(role);
					
					//获取该角色对应的权限
					List<ResourceDtoResult> resourceList = new ArrayList<>();
					
					ResourceQueryDtoReq resourceQueryReq = new ResourceQueryDtoReq();
					resourceQueryReq.setEnable(true);
					resourceQueryReq.setRoleIds(Arrays.asList(role.getId()));
					List<UcPmResourceModel> list = ucPmResourceService.searchResource(resourceQueryReq);
					
					if(CollectionUtils.isNotEmpty(list)) {
						ResourceDtoResult resourceDtoResult = null;
						for (UcPmResourceModel ucPmResourceModel : list) {
							resourceDtoResult = new ResourceDtoResult();
//							if(ucPmResourceModel.getParentId().intValue() == 0) {//一级菜单
								BeanUtils.copyProperties(ucPmResourceModel, resourceDtoResult);
								resourceDtoResult.setMenuCode(ucPmResourceModel.getCode());
								
								resourceList.add(resourceDtoResult);
								//再循环获取他的子集
//								recursiveResource(resourceDtoResult, list);
//							}
						}
					}
					
					result.setResourceList(resourceList);
				}
			}
			
			UcClientUserThirdAccountModel thirdAccountParam = new UcClientUserThirdAccountModel();
			thirdAccountParam.setUserId(dataList.get(0).getId());
			List<UcClientUserThirdAccountModel> accountList =  clientUserThirdAccountService.query(thirdAccountParam);
			result.setUserThirdAccounts(accountList);
			List<UcCustomerExModel> customers = ucCustomerService.queryUserCustomerMapping(dataList.get(0).getId());
			CustomerExDtoResult customerEx = new CustomerExDtoResult();
			if (CollectionUtils.isNotEmpty(customers)) {
				customerEx.setCustomer(customers.get(0));
				List<UcShopModel> shopList = shopService.queryCustomerMappingShop(customers.get(0).getId(), ShopStatusEnums.NORMAL_BUSINESS.getValue());
				if(CollectionUtils.isNotEmpty(shopList)){
					result.setCode(ResultCode.USER_CUSTOMER_NOT_EXIST.getCode());
					result.setMsg(ResultCode.USER_CUSTOMER_NOT_EXIST.getDesc());
				}
				customerEx.setShopList(shopList);
			}else{
				result.setCode(ResultCode.USER_CUSTOMER_NOT_EXIST.getCode());
				result.setMsg(ResultCode.USER_CUSTOMER_NOT_EXIST.getDesc());
			}
			result.setCustomer(customerEx);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} else {
			result.setCode(ResultCode.USER_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
		}
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
	/**
	 * 递归获取子集
	 * @param resourceDtoResult
	 * @param resourceList
	 */
	private void recursiveResource(ResourceDtoResult resourceDtoResult, List<UcPmResourceModel> resourceList) {
		ResourceDtoResult child = null;
		for (UcPmResourceModel ucPmResourceModel : resourceList) {
			if(resourceDtoResult.getId().intValue() == ucPmResourceModel.getParentId().intValue()) {
				child = new ResourceDtoResult();
				BeanUtils.copyProperties(ucPmResourceModel, child);
				child.setMenuCode(ucPmResourceModel.getCode());
				resourceDtoResult.getSubMenuList().add(child);

				recursiveResource(child, resourceList);
			}
		}
	}

	@Override
	public BaseResponse<ClientUserThirdAccountDtoResult> queryUserThirdAccount(
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "thirdAccount", required = false) String thirdAccount,
			@RequestParam(value = "thirdSource", required = true) String thirdSource) {
		ClientUserThirdAccountDtoResult result = new ClientUserThirdAccountDtoResult();
		UcClientUserThirdAccountModel queryParam = new UcClientUserThirdAccountModel();
		queryParam.setUserId(userId);
		queryParam.setThirdSource(thirdSource);
		queryParam.setThirdAccount(thirdAccount);
		List<UcClientUserThirdAccountModel> accountList = clientUserThirdAccountService.query(queryParam);
		if(CollectionUtils.isNotEmpty(accountList)){
			result.setAccount(accountList.get(0));
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		}else{
			result.setCode(ResultCode.THIRD_ACCT_UNBIND.getCode());
			result.setMsg(ResultCode.THIRD_ACCT_UNBIND.getDesc());
		}
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> removeUserThirdAccount(Long userId) {
		DtoResult result = new DtoResult();
		
		UcClientUserThirdAccountModel model = new UcClientUserThirdAccountModel();
		model.setUserId(userId);
		boolean r = clientUserThirdAccountService.remove(model);
		if(r) {
			result.setCode(ResultCode.SUCCESS.getCode());
		}else {
			result.setCode(ResultCode.FAIL.getCode());
		}
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<DtoResult> modifyUserInfo(@RequestBody ModifyUserInfoBySmscodeDtoReq requestBody) {
			DtoResult result = clientUserService.modifyUserInfo(requestBody);
			return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<UcClientUserRoleListDtoResult> getUserIdByRole(@RequestParam(name="roleId",required=true)Long roleId) {
		UcClientUserRoleListDtoResult result = new UcClientUserRoleListDtoResult();
		UcClientUserRoleModel userRoleModel = new UcClientUserRoleModel();
		userRoleModel.setRoleId(roleId);
		userRoleModel.setEnable(true);
		List<UcClientUserRoleModel> list = ucClientUserRoleService.query(userRoleModel);
		if(!list.isEmpty()) {
			result.setUserRoleList(list);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

}
