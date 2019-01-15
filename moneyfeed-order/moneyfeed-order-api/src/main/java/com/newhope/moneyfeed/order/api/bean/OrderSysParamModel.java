package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单系统参数表
 */
public class OrderSysParamModel extends BaseModel {
	
	private static final long serialVersionUID = 3212926778835203096L;

	/** 名称 */
    private String name;

    /** 值 */
    private String value;

    /** 枚举类型 */
    private String type;

    /** CODE */
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}