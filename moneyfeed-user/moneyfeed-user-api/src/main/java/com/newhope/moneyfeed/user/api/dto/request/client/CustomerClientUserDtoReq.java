package com.newhope.moneyfeed.user.api.dto.request.client;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class CustomerClientUserDtoReq {
	
	@ApiModelProperty("客户id")
	private Long customerId;
	
	@NotBlank(message="联系人名称不能为空")
	@ApiModelProperty("联系人名称")
    private String contactName;

	@NotBlank(message="联系人电话不能为空")
    @ApiModelProperty("联系人电话")
    private String contactPhone;
    
    @ApiModelProperty("用户所属的角色ID")
    private Long roleId;
    
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
    
    
}
