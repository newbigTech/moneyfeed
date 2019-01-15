package com.newhope.openapi.api.vo.response.user;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;


public class ClientRoleResult implements Serializable {


	private static final long serialVersionUID = 4982612966043572255L;

	@ApiModelProperty("主键Id")
	private Long id;
	
	@ApiModelProperty("角色岗位code")
    private String code;

    @ApiModelProperty("角色岗位名称")
    private String name;
    
    @ApiModelProperty("角色简介")
    private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    
}