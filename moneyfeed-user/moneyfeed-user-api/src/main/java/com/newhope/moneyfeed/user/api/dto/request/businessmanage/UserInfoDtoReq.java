package com.newhope.moneyfeed.user.api.dto.request.businessmanage;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserInfoDtoReq {
	
	@NotNull(message="主键为空")
	private Long userId;
	
	@NotBlank(message="手机号为空")
	private String mobile;
	
	private List<Long> roleIds;
	
	public UserInfoDtoReq() {
		super();
	}

	public UserInfoDtoReq(Long userId, String mobile, List<Long> roleIds) {
		super();
		this.userId = userId;
		this.mobile = mobile;
		this.roleIds = roleIds;
	}

	public Long getUserId() {
		return userId;
	}

	public String getMobile() {
		return mobile;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

}
