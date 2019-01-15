package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

public class ShopCustomerPropertyQueryDtoReq implements Serializable {

	private static final long serialVersionUID = -4921777586251970412L;

	/** 配置编码 */
    private String name;
    
    /** 商户客户关联id */
    private Long mappingId;

    /** 客户 */
    private Long customerId;
    
    /** 商户id */
    private Long shopId;
    
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}
    
}
