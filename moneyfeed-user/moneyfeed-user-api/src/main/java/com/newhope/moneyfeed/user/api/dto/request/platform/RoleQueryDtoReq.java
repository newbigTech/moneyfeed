package com.newhope.moneyfeed.user.api.dto.request.platform;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

public class RoleQueryDtoReq extends PageDtoReq{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7994267595422523367L;

	/** 是否可用 */
    private Boolean enable;

    /** 来源类型（运营端/商户端/WeChat） */
    private String sourceType;

    private String name;
    
    private Long id;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
    
}
