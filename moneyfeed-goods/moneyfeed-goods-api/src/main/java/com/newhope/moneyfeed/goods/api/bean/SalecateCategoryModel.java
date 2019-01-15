package com.newhope.moneyfeed.goods.api.bean;

public class SalecateCategoryModel extends BaseModel {
    /** 前台目录id */
    private String salecateId;

    /** 后台目录id */
    private String cateId;

    public String getSalecateId() {
        return salecateId;
    }

    public void setSalecateId(String salecateId) {
        this.salecateId = salecateId;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
}