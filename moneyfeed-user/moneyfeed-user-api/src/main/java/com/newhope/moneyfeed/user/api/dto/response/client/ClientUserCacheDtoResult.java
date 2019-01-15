package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;
import com.newhope.moneyfeed.user.api.dto.response.platform.ResourceDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;

public class ClientUserCacheDtoResult extends DtoResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6814400521974893982L;
	
	/**
	 * 用户model
	 */
	private UcClientUserModel user;

	/**
	 * 用户所属客户model
	 */
	private CustomerExDtoResult customer;
	
	/**
	 * 用户当前访问商户model
	 */
	private UserVisitShopDtoResult visitShop;
	
	/**
	 * 用户关联三方账户
	 */
	private List<UcClientUserThirdAccountModel> userThirdAccounts;
	
	/**
	 * 用户所属角色
	 */
	private UcPmRoleDtoResult role;
	
	/**
	 * 用户权限
	 */
	private List<ResourceDtoResult> resourceList;
	
	public UserVisitShopDtoResult getVisitShop() {
		return visitShop;
	}

	public void setVisitShop(UserVisitShopDtoResult visitShop) {
		this.visitShop = visitShop;
	}

	public UcClientUserModel getUser() {
		return user;
	}

	public void setUser(UcClientUserModel user) {
		this.user = user;
	}

	public CustomerExDtoResult getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerExDtoResult customer) {
		this.customer = customer;
	}

	public List<UcClientUserThirdAccountModel> getUserThirdAccounts() {
		return userThirdAccounts;
	}

	public void setUserThirdAccounts(List<UcClientUserThirdAccountModel> userThirdAccounts) {
		this.userThirdAccounts = userThirdAccounts;
	}

	public UcPmRoleDtoResult getRole() {
		return role;
	}

	public void setRole(UcPmRoleDtoResult role) {
		this.role = role;
	}

	public List<ResourceDtoResult> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ResourceDtoResult> resourceList) {
		this.resourceList = resourceList;
	}

}