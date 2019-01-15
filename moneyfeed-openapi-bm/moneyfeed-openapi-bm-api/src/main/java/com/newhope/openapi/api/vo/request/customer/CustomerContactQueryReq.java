package com.newhope.openapi.api.vo.request.customer;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class CustomerContactQueryReq extends PageReq {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3117950101211421683L;

	@ApiModelProperty(notes = "店铺Id")
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

	public String getContactNobile() {
		return contactNobile;
	}

	public void setContactNobile(String contactNobile) {
		this.contactNobile = contactNobile;
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

}
