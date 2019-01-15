package com.newhope.moneyfeed.goods.api.dto.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class SkuExDtoResult extends ProductDtoResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "companyName", notes = "公司名称")
	private String companyName;

	@ApiModelProperty(name = "price", notes = "价格")
	private BigDecimal price;

	@ApiModelProperty(name = "quantity", notes = "数量")
	private Integer quantity;

	@ApiModelProperty(name = "spec", notes = "规格")
	private String spec;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

}
