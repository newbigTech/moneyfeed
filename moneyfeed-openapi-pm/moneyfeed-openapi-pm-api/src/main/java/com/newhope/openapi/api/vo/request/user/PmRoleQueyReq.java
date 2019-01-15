package com.newhope.openapi.api.vo.request.user;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class PmRoleQueyReq extends PageReq {

	private static final long serialVersionUID = 1496742072217120576L;

	@ApiModelProperty(name="id", notes="id", required=false)
    private Long id;
	
	@ApiModelProperty(name="name", notes="角色名称", required=false)
    private String name;

    @ApiModelProperty(name="sourceType", notes="来源类型（运营端/商户端/WeChat）", required=false)
    private String sourceType;

    
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

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
    
}
