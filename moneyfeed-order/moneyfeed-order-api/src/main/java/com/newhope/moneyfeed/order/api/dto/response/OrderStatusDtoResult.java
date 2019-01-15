package com.newhope.moneyfeed.order.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/12/04
 */
public class OrderStatusDtoResult extends DtoResult implements Serializable {
    private static final long serialVersionUID = 5189639917038239228L;
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
