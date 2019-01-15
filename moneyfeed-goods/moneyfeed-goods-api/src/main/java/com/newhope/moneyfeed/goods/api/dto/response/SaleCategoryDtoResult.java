package com.newhope.moneyfeed.goods.api.dto.response;

public class SaleCategoryDtoResult {

    private String saleCateId;

    private String saleCaleName;

    private String parentSaleCateId;

    private Integer saleCateLevel;

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
}
