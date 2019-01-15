package com.newhope.openapi.api.vo.response.order.rule;

import java.util.Date;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("拉料日期规则")
public class TransportDateRuleResult extends Result {

	private static final long serialVersionUID = 2006484866820130788L;
	@ApiModelProperty(name = "beginDate", notes = "最早拉料日期")
	private Date beginDate;
	@ApiModelProperty(name = "endDate", notes = "最迟拉料日期")
	private Date endDate;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
