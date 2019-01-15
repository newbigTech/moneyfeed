package com.newhope.moneyfeed.order.api.dto.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author heping  
 * @date 2019年1月10日
 */
public class OrderInfoQueryBaseDtoReq implements Serializable {
	
	private static final long serialVersionUID = 5667238644766785630L;
	
	@ApiModelProperty(name = "statusList", notes = "订单状态集合")
	private List<String> statusList;
	
	@ApiModelProperty(name = "payFlag", notes = "是否已经支付成功")
    private Boolean payFlag;
	
	@ApiModelProperty(name = "payLimitEndTime", notes = "支付限制时间")
	private Date payLimitEndTime;
	
	@ApiModelProperty(name = "notOrderPayType", notes = "排除支付方式")
	private String notOrderPayType ;

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}

	public Date getPayLimitEndTime() {
		return payLimitEndTime;
	}

	public void setPayLimitEndTime(Date payLimitEndTime) {
		this.payLimitEndTime = payLimitEndTime;
	}

	public String getNotOrderPayType() {
		return notOrderPayType;
	}

	public void setNotOrderPayType(String notOrderPayType) {
		this.notOrderPayType = notOrderPayType;
	}
	
}
