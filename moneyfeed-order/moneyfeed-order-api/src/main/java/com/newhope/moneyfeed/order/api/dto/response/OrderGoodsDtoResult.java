package com.newhope.moneyfeed.order.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Create by yyq on 2018/11/22
 */
public class OrderGoodsDtoResult implements Serializable {

    private static final long serialVersionUID = 1149554782453951045L;

    @ApiModelProperty(name = "productName", notes = "商品名称")
    private String productName;
    @ApiModelProperty(name = "skuParams", notes = "规格")
    private String skuParams;
    @ApiModelProperty(name = "quantity", notes = "数量")
    private Integer quantity;
    @ApiModelProperty(name = "primaryUom", notes = "主单位")
    private String primaryUom;
    @ApiModelProperty(name = "secondaryUom", notes = "辅助单位")
    private String secondaryUom;
    @ApiModelProperty(name = "total", notes = "总计")
    private BigDecimal total;
    @ApiModelProperty(name = "price", notes = "出厂价")
    private BigDecimal price;

    @ApiModelProperty(name = "skuId", notes = "商品id")
    private Long skuId;
    @ApiModelProperty(name = "skuParamsValue", notes = "规格值")
    private String skuParamsValue;


    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuParams() {
        return skuParams;
    }

    public void setSkuParams(String skuParams) {
        this.skuParams = skuParams;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuParamsValue() {
        return skuParamsValue;
    }

    public void setSkuParamsValue(String skuParamsValue) {
        this.skuParamsValue = skuParamsValue;
    }
}
