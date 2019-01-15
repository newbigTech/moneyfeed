package com.newhope.moneyfeed.order.api.dto.request;

import com.newhope.moneyfeed.order.api.constant.CommonConstant;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageDtoReq implements Serializable {

	private static final long serialVersionUID = 2404902482976922378L;
	
	@ApiModelProperty(name = "page", value = "页数(第几页),默认第一页", notes = "页数(第几页),默认第一页")
	private Long page = 1L;
	@ApiModelProperty(name = "pageSize", value = "每页展示条数,默认10条", notes = "每页展示条数,默认10条")
	private Integer pageSize = CommonConstant.PAGE_SIZE;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}
	
}
