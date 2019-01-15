package com.newhope.openapi.api.vo.request.category;

public class CategoryQueryReq {

    /** 目录id */
    private String cateId;

    /** 目录层级 */
    private Integer cateLevel;

    /** 目录名称 */
    private String cateName;

    /** 父级目录 */
    private String parentCateId;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public Integer getCateLevel() {
        return cateLevel;
    }

    public void setCateLevel(Integer cateLevel) {
        this.cateLevel = cateLevel;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getParentCateId() {
        return parentCateId;
    }

    public void setParentCateId(String parentCateId) {
        this.parentCateId = parentCateId;
    }
}
