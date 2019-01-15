package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class ModifyCustomerDtoReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7017132740342402048L;

	@NotNull
	@ApiModelProperty(notes = "客户id")
	private Long id;

	@ApiModelProperty(notes = "备注")
    private String comment;
	
	@ApiModelProperty("标签Id集合")
    private List<Long> labelIds;
	
	@ApiModelProperty("状态：true-启用；false-禁用")
    private Boolean enable;

	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Long> getLabelIds() {
		return labelIds;
	}

	public void setLabelIds(List<Long> labelIds) {
		this.labelIds = labelIds;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
