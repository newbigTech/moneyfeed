package com.newhope.moneyfeed.goods.api.dto.request;

public class  SaleCategoryQueryDtoReq {

    private String customerNo;

    private Long shopId;

    private Integer saleCateId;

    private String saleCaleName;

    private Integer cateId;

    private Integer parentSaleCateId;

    private Integer saleCateLevel;

    private Long enabled;

    public Integer getSaleCateId() {
        return saleCateId;
    }

    public void setSaleCateId(Integer saleCateId) {
        this.saleCateId = saleCateId;
    }

    public String getSaleCaleName() {
        return saleCaleName;
    }

    public void setSaleCaleName(String saleCaleName) {
        this.saleCaleName = saleCaleName;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getParentSaleCateId() {
        return parentSaleCateId;
    }

    public void setParentSaleCateId(Integer parentSaleCateId) {
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

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
