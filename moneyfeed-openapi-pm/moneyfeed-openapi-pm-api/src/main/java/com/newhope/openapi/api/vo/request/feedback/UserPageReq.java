package com.newhope.openapi.api.vo.request.feedback;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

import io.swagger.annotations.ApiModelProperty;

public class UserPageReq extends PageDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "id",notes = "用户id")
	private Long id;

	//用户手机号
	@ApiModelProperty(name = "id",notes = "用户手机号")
	private String mobile;
	
	//是否可用
	@ApiModelProperty(name = "id",notes = "是否可用")
    private Boolean enable;

    //模糊用户名称
	@ApiModelProperty(name = "id",notes = "模糊用户名称")
    private String likeName;
    
    //模糊用户名称
	@ApiModelProperty(name = "id",notes = "模糊用户/手机号名称")
    private String likeMobile;
    
    //用户id集合
	@ApiModelProperty(name = "ids",notes = "用户id集合")
    private List<Long> ids;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getLikeName() {
		return likeName;
	}

	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}

	public String getLikeMobile() {
		return likeMobile;
	}

	public void setLikeMobile(String likeMobile) {
		this.likeMobile = likeMobile;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
    
}
