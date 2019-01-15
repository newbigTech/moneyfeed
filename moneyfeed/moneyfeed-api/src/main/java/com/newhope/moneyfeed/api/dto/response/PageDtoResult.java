package com.newhope.moneyfeed.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageDtoResult extends DtoResult implements Serializable {
	
	private static final long serialVersionUID = -2557457192133618747L;
	
	@ApiModelProperty(name = "page", notes = "当前页")
	private Long page;
	@ApiModelProperty(name = "totalPage", notes = "总页数")
	private Long totalPage;
	@ApiModelProperty(name = "totalCount", notes = "总条数")
	private Long totalCount;
	
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

}
