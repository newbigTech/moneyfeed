package com.newhope.moneyfeed.goods.api.bean;

public class SkuAttributeParmModel extends BaseModel {
    private Long skuId;

    private Long attributeParmId;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getAttributeParmId() {
        return attributeParmId;
    }

    public void setAttributeParmId(Long attributeParmId) {
        this.attributeParmId = attributeParmId;
    }
}