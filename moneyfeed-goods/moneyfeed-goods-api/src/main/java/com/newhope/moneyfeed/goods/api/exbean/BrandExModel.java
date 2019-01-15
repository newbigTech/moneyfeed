package com.newhope.moneyfeed.goods.api.exbean;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:47
 */
public class BrandExModel implements Serializable {
    private static final long serialVersionUID = 3515981316055951900L;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌标记商品数量
     */
    private Integer productCount;

    /**
     * 商户
     */
    private Integer shopCount;

    /**
     * 商户名称
     */
    private String shopNames;

    public String getShopNames() {
        return shopNames;
    }

    public void setShopNames(String shopNames) {
        this.shopNames = shopNames;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getShopCount() {
        return shopCount;
    }

    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }
}
