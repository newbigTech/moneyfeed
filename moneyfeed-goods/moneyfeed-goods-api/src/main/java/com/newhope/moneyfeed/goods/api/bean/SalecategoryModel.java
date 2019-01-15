package com.newhope.moneyfeed.goods.api.bean;

public class SalecategoryModel extends BaseModel {
    /** 销售目录id */
    private String saleCateId;

    /** 销售目录名称 */
    private String saleCaleName;

    /** 父目录id */
    private String parentSaleCateId;

    /** 层级 */
    private Integer saleCateLevel;

    private Long enabled;

    public String getSaleCateId() {
        return saleCateId;
    }

    public void setSaleCateId(String saleCateId) {
        this.saleCateId = saleCateId;
    }

    public String getSaleCaleName() {
        return saleCaleName;
    }

    public void setSaleCaleName(String saleCaleName) {
        this.saleCaleName = saleCaleName;
    }

    public String getParentSaleCateId() {
        return parentSaleCateId;
    }

    public void setParentSaleCateId(String parentSaleCateId) {
        this.parentSaleCateId = parentSaleCateId;
    }

    public Integer getSaleCateLevel() {
        return saleCateLevel;
    }

    public void setSaleCateLevel(Integer saleCateLevel) {
        this.saleCateLevel = saleCateLevel;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }
}