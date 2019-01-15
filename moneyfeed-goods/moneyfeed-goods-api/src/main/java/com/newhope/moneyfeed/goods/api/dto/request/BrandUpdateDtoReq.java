package com.newhope.moneyfeed.goods.api.dto.request;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:31
 */
public class BrandUpdateDtoReq extends PageDtoReq implements Serializable {
    private static final long serialVersionUID = -8383359945172178763L;

    /** 品牌id */
    private Long brandId;

    /** 品牌id */
    private Long newBrandId;

    /** 品牌名称 */
    private String brandName;

    public Long getNewBrandId() {
        return newBrandId;
    }

    public void setNewBrandId(Long newBrandId) {
        this.newBrandId = newBrandId;
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
}
