package com.newhope.openapi.api.vo.response.product;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/22 19:33
 */
public class ProductSkuAttributesResult implements Serializable {
    private static final long serialVersionUID = 787652653967494391L;

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
