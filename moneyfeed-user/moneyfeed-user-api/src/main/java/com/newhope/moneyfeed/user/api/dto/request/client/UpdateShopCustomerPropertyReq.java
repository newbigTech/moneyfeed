package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class UpdateShopCustomerPropertyReq implements Serializable {

	private static final long serialVersionUID = -5883818943298970963L;

	@NotBlank(message="配置编码为空")
	@ApiModelProperty(name="name", notes="配置编码", required=true)
    private String name;

	@NotBlank(message="元数据值为空")
	@ApiModelProperty(name="value", notes="元数据值", required=true)
    private String value;

	@NotNull(message="商户客户关联id为空")
	@ApiModelProperty(name="mappingId", notes="商户客户关联id", required=true)
    private Long mappingId;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Long getMappingId() {
		return mappingId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}
    
}