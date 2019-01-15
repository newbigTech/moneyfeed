package com.newhope.moneyfeed.user.api.dto.request.businessmanage;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ModifyEmployeeInfoDtoReq implements Serializable {

	private static final long serialVersionUID = 7573570362876360437L;
	@ApiModelProperty(name="id", notes="用户id", required=true)
	private Long id;
	@NotBlank(message="用户姓名为空")
	@ApiModelProperty(name="name", notes="姓名", required=true)
	private String name;
	@NotBlank(message="手机号为空")
	@ApiModelProperty(name="mobile", notes="手机号", required=true)
	private String mobile;
	@ApiModelProperty(name="status", notes="状态", required=true)
	private Boolean status;
	@NotNull
	@ApiModelProperty(name="roleId", notes="角色Id", required=true)
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
