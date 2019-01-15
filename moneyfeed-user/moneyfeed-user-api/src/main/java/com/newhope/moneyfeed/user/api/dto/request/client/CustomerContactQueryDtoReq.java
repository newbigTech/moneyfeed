package com.newhope.moneyfeed.user.api.dto.request.client;

import org.springframework.validation.annotation.Validated;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class CustomerContactQueryDtoReq extends PageDtoReq {
	
	private static final long serialVersionUID = -2562011746682283564L;
	
	//"主键Id"
	private Long customerClientUserId;

	private Long shopId;
	
	/** 是否可用 */
    private Boolean enable;
    
    /**
     * 联系人
     */
    private String contactName;
    
    private String customerName;
    
    private String beginDatetime;
    
    private String endDatetime;
    
    private String contactNobile;
    
    private Long customerId;

    private Long roleId;
    
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Boolean getEnable() {
		return enable;
	}

	public String getContactName() {
		return contactName;
	}

	public String getBeginDatetime() {
		return beginDatetime;
	}

	public String getEndDatetime() {
		return endDatetime;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setBeginDatetime(String beginDatetime) {
		this.beginDatetime = beginDatetime;
	}

	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactNobile() {
		return contactNobile;
	}

	public void setContactNobile(String contactNobile) {
		this.contactNobile = contactNobile;
	}

	public String getActivated() {
		return activated;
	}

	public Long getCustomerClientUserId() {
		return customerClientUserId;
	}

	public void setCustomerClientUserId(Long customerClientUserId) {
		this.customerClientUserId = customerClientUserId;
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
