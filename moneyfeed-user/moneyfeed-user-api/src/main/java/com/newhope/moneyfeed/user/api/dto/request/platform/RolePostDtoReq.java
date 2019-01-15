package com.newhope.moneyfeed.user.api.dto.request.platform;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class RolePostDtoReq implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1206960416474677269L;

	@ApiModelProperty(name="id", notes="id", required=false)
	private Long id;
	
	@NotBlank(message="角色名称为空")
	@ApiModelProperty(name="name", notes="角色名称", required=true)
    private String name;

	@NotBlank(message="来源类型为空")
	@ApiModelProperty(name="sourceType", notes="来源类型", required=true)
    private String sourceType;

	@ApiModelProperty(name="comment", notes="角色简介", required=false)
    private String comment;

	@ApiModelProperty(name="resourceIdList", notes="资源id集合", required=false)
	private List<Long> resourceIdList;

	@ApiModelProperty(name="enable", notes="是否可用", required=false)
    private Boolean enable;
    
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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

	public List<Long> getResourceIdList() {
		return resourceIdList;
	}

	public void setResourceIdList(List<Long> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}	
	
}
