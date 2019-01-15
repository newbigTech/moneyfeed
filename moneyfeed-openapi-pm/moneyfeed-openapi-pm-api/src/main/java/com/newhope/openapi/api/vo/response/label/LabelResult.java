package com.newhope.openapi.api.vo.response.label;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class LabelResult extends Result {

	private static final long serialVersionUID = -8417508782389487982L;

	@ApiModelProperty(name="id", notes="主键id")
    private Long id;
	
    @ApiModelProperty(name="name", notes="标签名称")
    private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
