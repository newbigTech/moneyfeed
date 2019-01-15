package com.newhope.openapi.api.vo.request.customer;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class CustomerClientUserUpdateReq {
	
	@NotNull(message="主键Id不能为空")
	@ApiModelProperty("主键Id")
	private Long customerClientUserId;
	
	@NotNull(message="用户Id不能为空")
	@ApiModelProperty("联系人用户主键Id")
	private Long clientUserId;
	
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
	
    @ApiModelProperty("true-正常，false-禁用")
    private Boolean enable;
    
    @ApiModelProperty("短信验证码")
    private String smsCode;
    
	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Long getCustomerClientUserId() {
		return customerClientUserId;
	}

	public void setCustomerClientUserId(Long customerClientUserId) {
		this.customerClientUserId = customerClientUserId;
	}

	public Long getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

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

}
