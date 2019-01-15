package com.newhope.moneyfeed.api.vo.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageResult extends Result implements Serializable {
	
	private static final long serialVersionUID = 3482414465647451836L;
	
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
