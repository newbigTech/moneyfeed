package com.newhope.moneyfeed.api.vo.response.wechat;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class WechatJsticketResult implements Serializable {

	private static final long serialVersionUID = 4359923969966502280L;
	@ApiModelProperty(name = "jsticket", notes = "jsticket")
	private String jsticket;
	@ApiModelProperty(name = "jsticketDate", notes = "jsticket生成时间")
	private Date jsticketDate;

	public String getJsticket() {
		return jsticket;
	}

	public Date getJsticketDate() {
		return jsticketDate;
	}

	public void setJsticket(String jsticket) {
		this.jsticket = jsticket;
	}

	public void setJsticketDate(Date jsticketDate) {
		this.jsticketDate = jsticketDate;
	}

}
