package com.newhope.openapi.api.vo.response.order;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

/**
 * Create by yyq on 2018/12/04
 */
public class OrderStatusResult extends Result {

    private static final long serialVersionUID = 1901728842228219244L;
    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "id", notes = "订单主键id")
    private Long id;
    @ApiModelProperty(name="payFlag",notes="是否已经支付成功")
    private Boolean payFlag;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}
}
