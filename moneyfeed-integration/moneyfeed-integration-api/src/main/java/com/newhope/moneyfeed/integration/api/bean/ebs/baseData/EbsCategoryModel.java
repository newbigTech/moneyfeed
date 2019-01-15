package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;


import com.newhope.moneyfeed.api.bean.BaseModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HERDER")
public class EbsCategoryModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7168115495706560282L;

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


    @XmlElement(name = "CATEGORY_ID")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @XmlElement(name = "ONE_CAT")
    public String getOneCat() {
        return oneCat;
    }

    public void setOneCat(String oneCat) {
        this.oneCat = oneCat;
    }

    @XmlElement(name = "ONE_CAT_DES")
    public String getOneCatDesc() {
        return oneCatDesc;
    }

    public void setOneCatDesc(String oneCatDesc) {
        this.oneCatDesc = oneCatDesc;
    }

    @XmlElement(name = "TWO_CAT")
    public String getTwoCat() {
        return twoCat;
    }

    public void setTwoCat(String twoCat) {
        this.twoCat = twoCat;
    }

    @XmlElement(name = "TWO_CAT_DES")
    public String getTwoCatDesc() {
        return twoCatDesc;
    }

    public void setTwoCatDesc(String twoCatDesc) {
        this.twoCatDesc = twoCatDesc;
    }

    @XmlElement(name = "THREE_CAT")
    public String getThreeCat() {
        return threeCat;
    }

    public void setThreeCat(String threeCat) {
        this.threeCat = threeCat;
    }

    @XmlElement(name = "THREE_CAT_DES")
    public String getThreeCatDesc() {
        return threeCatDesc;
    }

    public void setThreeCatDesc(String threeCatDesc) {
        this.threeCatDesc = threeCatDesc;
    }

    @XmlElement(name = "FOUR_CAT")
    public String getFourCat() {
        return fourCat;
    }

    public void setFourCat(String fourCat) {
        this.fourCat = fourCat;
    }

    @XmlElement(name = "FOUR_CAT_DES")
    public String getFourCatDesc() {
        return fourCatDesc;
    }

    public void setFourCatDesc(String fourCatDesc) {
        this.fourCatDesc = fourCatDesc;
    }
}