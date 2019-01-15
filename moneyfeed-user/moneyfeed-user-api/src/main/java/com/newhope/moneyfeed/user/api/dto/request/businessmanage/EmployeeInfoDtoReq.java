package com.newhope.moneyfeed.user.api.dto.request.businessmanage;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class EmployeeInfoDtoReq extends PageDtoReq implements Serializable {

	private static final long serialVersionUID = 1770048332570997885L;
	@ApiModelProperty(name="userId", notes="用户id", required=true)
	private Long userId;

	@ApiModelProperty(name="name", notes="姓名", required=true)
	private String name;

	@ApiModelProperty(name="mobile", notes="手机号", required=true)
	private String mobile;

	@ApiModelProperty(name="status", notes="状态", required=true)
	private Boolean status;

	@ApiModelProperty(name="roleIds", notes="角色Id", required=true)
	private List<Long> roleIds;

	private List<Long> userIds;
	@ApiModelProperty(name="shopId", notes="商户Id", required=true)
	private Long shopId;

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
}
