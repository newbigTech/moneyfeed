package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;

public class UserMenuDtoResult extends DtoResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2918499874166443835L;

	@ApiModelProperty(notes="菜单id")
	private Long id;
	
	@ApiModelProperty(notes="菜单编号")
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
	private List<UserMenuDtoResult> subMenuList = new ArrayList<UserMenuDtoResult>();
	
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

	public List<UserMenuDtoResult> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<UserMenuDtoResult> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}
