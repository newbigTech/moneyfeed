package com.newhope.openapi.api.vo.request.order.product;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ProductItemReq implements Serializable {

	private static final long serialVersionUID = 247845970236170626L;

	@NotNull(message = "商品编号错误")
	@ApiModelProperty(name = "skuid", notes = "商品skuid")
	private Long skuid;

	@Min(value = 1, message = "数量最小值为1")
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
