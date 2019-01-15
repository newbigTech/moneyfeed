package com.newhope.moneyfeed.goods.api.bean;

public class BrandModel extends BaseModel {

    /** 品牌名称 */
    private String brandName;

    /** 品牌描述 */
    private String brandDesc;

    private Boolean enabled;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}