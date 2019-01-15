package com.newhope.moneyfeed.user.api.dto.response.client;


import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


public class CustomerClientUserMappingDtoResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7155881795682017189L;

	@ApiModelProperty("主键Id")
	private Long customerClientUserId;
	
	@ApiModelProperty(" 客户名称")
    private String customerName;

    @ApiModelProperty("联系人名称")
    private String contactName;

    @ApiModelProperty("联系人电话，即帐号")
    private String contactPhone;
    
    @ApiModelProperty("true-正常，false-禁用")
    private Boolean enable;
    
    @ApiModelProperty("来源")
    private String source;
    
    @ApiModelProperty("创建时间")
    private Date gmtCreated;
    
    @ApiModelProperty("所属商户")
    private String shopName;
    
    @ApiModelProperty("联系人用户主键Id")
	private Long clientUserId;
    
    @ApiModelProperty("客户id")
    private Long customerId;
    
    @ApiModelProperty("客户编号")
    private String customerNum;
    
    @ApiModelProperty("用户所属的角色ID")
    private Long roleId;
    
    @ApiModelProperty("用户所属的角色名称")
    private String roleName;
    
    @ApiModelProperty("激活状态")
    private String activated;
    
    @ApiModelProperty("状态:NORMAL-正常；NOT_CONFIRMED-待确认")
    private String status;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public Boolean getEnable() {
		return enable;
	}

	public String getSource() {
		return source;
	}

	public Long getCustomerClientUserId() {
		return customerClientUserId;
	}

	public void setCustomerClientUserId(Long customerClientUserId) {
		this.customerClientUserId = customerClientUserId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}