package com.newhope.moneyfeed.goods.api.bean;

/**
 *   属性表
 */
public class AttributesModel extends BaseModel {
    /** 目录id */
    private String categoryId;

    /** 属性名称 */
    private String name;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}