package com.newhope.openapi.api.vo.request.user;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class EmployeeInfoReq extends PageReq implements Serializable {

	private static final long serialVersionUID = 1770048332570997885L;
	@ApiModelProperty(name="userId", notes="用户id")
	private Long userId;

	@ApiModelProperty(name="name", notes="姓名")
	private String name;

	@ApiModelProperty(name="mobile", notes="手机号")
	private String mobile;

	@ApiModelProperty(name="status", notes="状态")
	private Boolean status;

	@ApiModelProperty(name="roleIds", notes="角色Id")
	private List<Long> roleIds;

	private List<Long> userIds;

	@ApiModelProperty(name="shopId", notes="所属店铺id")
	private Long shopId;

	@ApiModelProperty(name="roleIdStr", notes="导出roleId字符串")
	private String roleIdStr;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getRoleIdStr() {
		return roleIdStr;
	}

	public void setRoleIdStr(String roleIdStr) {
		this.roleIdStr = roleIdStr;
	}
}
