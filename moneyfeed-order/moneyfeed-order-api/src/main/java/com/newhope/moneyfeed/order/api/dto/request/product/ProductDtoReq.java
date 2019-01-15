package com.newhope.moneyfeed.order.api.dto.request.product;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "订单商品信息")
public class ProductDtoReq implements Serializable {

	private static final long serialVersionUID = -4452690093674805723L;

	@ApiModelProperty(name = "skuid", notes = "商品skuid")
	private Long skuid;

	@ApiModelProperty(name = "count", notes = "商品数量")
	private Integer count;

	public Long getSkuid() {
		return skuid;
	}

	public void setSkuid(Long skuid) {
		this.skuid = skuid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
