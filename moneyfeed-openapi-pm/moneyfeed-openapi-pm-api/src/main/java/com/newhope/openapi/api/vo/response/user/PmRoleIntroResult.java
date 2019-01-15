package com.newhope.openapi.api.vo.response.user;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class PmRoleIntroResult implements Serializable {

	private static final long serialVersionUID = 5915727756840925435L;

	@ApiModelProperty("id")
    private Long id;
	
	@ApiModelProperty("角色岗位code")
    private String code;
	
	@ApiModelProperty("角色岗位名称")
    private String name;

	@ApiModelProperty("是否可用 ")
    private Boolean enable;

	@ApiModelProperty("来源类型")
    private String sourceType;

	@ApiModelProperty("角色简介")
    private String comment;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}