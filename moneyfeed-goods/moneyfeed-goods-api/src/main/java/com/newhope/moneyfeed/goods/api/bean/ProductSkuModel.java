package com.newhope.moneyfeed.goods.api.bean;

import java.math.BigDecimal;

public class ProductSkuModel extends BaseModel {
    private Long shopId;

    private BigDecimal price;

    private Integer quantity;

    /** 商品code */
    private String productCode;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}