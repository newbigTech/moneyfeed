package com.newhope.moneyfeed.order.api.dto.response.order;

import java.io.Serializable;

import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.bean.OrderRuleModel;

import io.swagger.annotations.ApiModelProperty;

public class OrderInfoDtoResult implements Serializable {
	
	private static final long serialVersionUID = -4528051612042463519L;
	
	@ApiModelProperty(name="order",notes="订单")
	private OrderInfoModel order;

	@ApiModelProperty(name="rule",notes="订单规则")
	private OrderRuleModel rule;

	public OrderInfoModel getOrder() {
		return order;
	}

	public void setOrder(OrderInfoModel order) {
		this.order = order;
	}

	public OrderRuleModel getRule() {
		return rule;
	}

	public void setRule(OrderRuleModel rule) {
		this.rule = rule;
	}
}
