package com.newhope.moneyfeed.goods.api.exbean;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 16:27
 */
public class ProductSkuAttributesExModel implements Serializable {
    private static final long serialVersionUID = -128641010226661546L;

    /** 属性名称 */
    private String name;

    /** 属性值 */
    private String parmValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParmValue() {
        return parmValue;
    }

    public void setParmValue(String parmValue) {
        this.parmValue = parmValue;
    }
}
