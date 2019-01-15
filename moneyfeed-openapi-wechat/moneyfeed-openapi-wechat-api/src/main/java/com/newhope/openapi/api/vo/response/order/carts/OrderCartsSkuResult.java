package com.newhope.openapi.api.vo.response.order.carts;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class OrderCartsSkuResult implements Serializable {

	private static final long serialVersionUID = 338668897939567822L;
	@ApiModelProperty(name="productSkuId",notes="skuid")
	private Long productSkuId;
	@ApiModelProperty(name="productName",notes="产品名称")
	private String productName;
	@ApiModelProperty(name="primaryUom",notes="主单位")
	private String primaryUom;
	@ApiModelProperty(name="secondaryUom",notes="次单位")
	private String secondaryUom;
	@ApiModelProperty(name="count",notes="数量")
	private Integer count;
	@ApiModelProperty(name="price",notes="厂价")
	private BigDecimal price;
	@ApiModelProperty(name="weight",notes="重量规则-重量")
	private String weight;

	public Long getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrimaryUom() {
		return primaryUom;
	}

	public void setPrimaryUom(String primaryUom) {
		this.primaryUom = primaryUom;
	}

	public String getSecondaryUom() {
		return secondaryUom;
	}

	public void setSecondaryUom(String secondaryUom) {
		this.secondaryUom = secondaryUom;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
