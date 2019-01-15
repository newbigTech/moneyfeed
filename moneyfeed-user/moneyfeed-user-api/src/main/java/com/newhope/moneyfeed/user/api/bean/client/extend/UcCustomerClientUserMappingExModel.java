package com.newhope.moneyfeed.user.api.bean.client.extend;

import java.util.Date;

import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;

/**
 *   用户中心-客户用户关系扩展表
 */
public class UcCustomerClientUserMappingExModel extends UcCustomerClientUserMappingModel {
	
	private static final long serialVersionUID = 1190893731651738004L;

	private String customerName;
	
	private String source;
	
	private String shopName;
	
	private String ebsCustomerNum;
	
	private Date lastLoginTime;
	
	private String roleName;
	
	private Long roleId;
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getSource() {
		return source;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEbsCustomerNum() {
		return ebsCustomerNum;
	}

	public void setEbsCustomerNum(String ebsCustomerNum) {
		this.ebsCustomerNum = ebsCustomerNum;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}