package com.newhope.moneyfeed.user.api.dto.request.client;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

public class EbsCustomerQueryDtoReq extends PageDtoReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4706494890299464266L;

	private Long id;

	private String orgId;
	
	/** 是否可用 */
    private Boolean enable;
    
    /**
     * 联系人
     */
    private String contactName;
    
    /**
     * 开始时间
     */
    private String beginDatetime;
    

    /**
     * 结束时间
     */
    private String endDatetime;

	public Long getId() {
		return id;
	}

	public String getOrgId() {
		return orgId;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

}
