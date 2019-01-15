package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

public class UcShopCustomerPropertyModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6949962059338931516L;

	/** 配置编码 */
    private String name;

    /** 详细描述 */
    private String detail;

    /** 是否可用 */
    private Boolean enable;

    /** 元数据值 */
    private String value;

    /** 商户客户关联id */
    private Long mappingId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }
}