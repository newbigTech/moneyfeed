package com.newhope.openapi.api.vo.request.shop;

import java.util.List;

import com.newhope.moneyfeed.api.vo.request.PageReq;

public class ShopQueryReq extends PageReq {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6569036469600996044L;

	//店铺id
	private Long id;

	//店铺ids
	private List<Long> ids;

	//ebs组织主键id
    private String ebsOrgId;
    
    //是否可用
    private Boolean enable;

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
    
}
