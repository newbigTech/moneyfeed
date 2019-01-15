package com.newhope.openapi.api.vo.response.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class PmUserMenuResult extends Result implements Serializable {

	private static final long serialVersionUID = -6330574393329940731L;

	@ApiModelProperty(notes="菜单id")
	private Long id;
	
	@ApiModelProperty(notes="菜单编码")
	private String menuCode;
	
	@ApiModelProperty(notes="菜单名称")
	private String name;
	
	@ApiModelProperty(notes="菜单跳转链接")
	private String url;
	
	@ApiModelProperty(notes="菜单排序")
	private Integer sort;
	
	@ApiModelProperty(notes="图标")
    private String icon;
    
	@ApiModelProperty(notes="子菜单集合")
	private List<PmUserMenuResult> subMenuList = new ArrayList<PmUserMenuResult>();
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<PmUserMenuResult> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<PmUserMenuResult> subMenuList) {
		this.subMenuList = subMenuList;
	}



}
