package com.newhope.moneyfeed.goods.api.bean;

/**
 *   属性参数
 */
public class AttributeParmsModel extends BaseModel {
    private Long attributeId;

    /** 属性值 */
    private String parmValue;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getParmValue() {
        return parmValue;
    }

    public void setParmValue(String parmValue) {
        this.parmValue = parmValue;
    }
}