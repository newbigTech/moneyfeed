package com.newhope.moneyfeed.user.api.dto.request.client;

import java.util.List;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

public class ShopQueryDtoReq extends PageDtoReq {

	private static final long serialVersionUID = -4706494890299464266L;

	//店铺id
	private Long id;

	//店铺ids
	private List<Long> ids;

	//ebs组织主键id
    private String ebsOrgId;
    
    //是否可用
    private Boolean enable;

    //模糊店铺名称
    private String likeName;
    
	//店铺状态
    private String status;
    
    
	public String getLikeName() {
		return likeName;
	}

	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getEbsOrgId() {
		return ebsOrgId;
	}

	public void setEbsOrgId(String ebsOrgId) {
		this.ebsOrgId = ebsOrgId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
