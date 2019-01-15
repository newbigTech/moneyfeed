package com.newhope.openapi.api.vo.request.customer;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class CustomerUpdateReq {
	
	@NotNull(message="客户Id不能为空")
	@ApiModelProperty("客户Id")
	private Long customerId;
	
	@ApiModelProperty("标签Id集合")
    private List<Long> labelIds;
	
	@ApiModelProperty("备注信息")
	private String comment;
	
	@ApiModelProperty("状态：true-启用；false-禁用")
    private Boolean enable;

	public Long getCustomerId() {
		return customerId;
	}

	public List<Long> getLabelIds() {
		return labelIds;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setLabelIds(List<Long> labelIds) {
		this.labelIds = labelIds;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
}
