package com.newhope.openapi.api.vo.response.user;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class PmResourceResult extends Result {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5301503635428579083L;

	@ApiModelProperty(notes="菜单id")
	private Long id;
	
	@ApiModelProperty(notes="权限名称")
    private String name;

	@ApiModelProperty(notes="权限Code")
    private String resourceCode;
	
	@ApiModelProperty(notes="权限类型")
    private String type;

	@ApiModelProperty(notes="父集角色id ")
    private Long parentId;

	@ApiModelProperty(notes="是否选中")
	private boolean selected;
	
	@ApiModelProperty(notes="子集合")
	private List<PmResourceResult> subList = new ArrayList<PmResourceResult>();

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<PmResourceResult> getSubList() {
		return subList;
	}

	public void setSubList(List<PmResourceResult> subList) {
		this.subList = subList;
	}
	
}
