package com.newhope.moneyfeed.goods.api.bean.ebs;

import com.newhope.moneyfeed.goods.api.bean.BaseModel;

public class EbsCategoryModel extends BaseModel {
    /** ebs品类主键ID */
    private String categoryId;

    /** 一级分类id */
    private String oneCat;

    /** 一级分类描述 */
    private String oneCatDesc;

    /** 二级分类ID */
    private String twoCat;

    /** 二级分类描述 */
    private String twoCatDesc;

    /** 三级分类ID */
    private String threeCat;

    /** 三级分类描述 */
    private String threeCatDesc;

    /** 四级分类ID */
    private String fourCat;

    /** 四级分类描述 */
    private String fourCatDesc;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getOneCat() {
        return oneCat;
    }

    public void setOneCat(String oneCat) {
        this.oneCat = oneCat;
    }

    public String getOneCatDesc() {
        return oneCatDesc;
    }

    public void setOneCatDesc(String oneCatDesc) {
        this.oneCatDesc = oneCatDesc;
    }

    public String getTwoCat() {
        return twoCat;
    }

    public void setTwoCat(String twoCat) {
        this.twoCat = twoCat;
    }

    public String getTwoCatDesc() {
        return twoCatDesc;
    }

    public void setTwoCatDesc(String twoCatDesc) {
        this.twoCatDesc = twoCatDesc;
    }

    public String getThreeCat() {
        return threeCat;
    }

    public void setThreeCat(String threeCat) {
        this.threeCat = threeCat;
    }

    public String getThreeCatDesc() {
        return threeCatDesc;
    }

    public void setThreeCatDesc(String threeCatDesc) {
        this.threeCatDesc = threeCatDesc;
    }

    public String getFourCat() {
        return fourCat;
    }

    public void setFourCat(String fourCat) {
        this.fourCat = fourCat;
    }

    public String getFourCatDesc() {
        return fourCatDesc;
    }

    public void setFourCatDesc(String fourCatDesc) {
        this.fourCatDesc = fourCatDesc;
    }
}