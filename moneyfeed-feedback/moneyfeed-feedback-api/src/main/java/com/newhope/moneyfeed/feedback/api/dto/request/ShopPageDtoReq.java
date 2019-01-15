package com.newhope.moneyfeed.feedback.api.dto.request;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

import io.swagger.annotations.ApiModelProperty;

public class ShopPageDtoReq extends PageDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//店铺id
	@ApiModelProperty(name = "id",notes = "店铺id")
	private Long id;

	//店铺ids
	@ApiModelProperty(name = "ids",notes = "店铺ids")
	private List<Long> ids;

    //是否可用
	@ApiModelProperty(name = "enable",notes = "是否可用")
    private Boolean enable;
	
	 //模糊店铺名称
	@ApiModelProperty(name = "likeName",notes = "模糊店铺名称")
    private String likeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
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
	
}
