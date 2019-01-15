package com.newhope.openapi.api.vo.request.customer;

import java.util.List;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class CustomerQueryReq extends PageReq {

	private static final long serialVersionUID = -5247244547613786689L;

	@ApiModelProperty(notes = "店铺Id")
	private Long shopId;
	
	@ApiModelProperty(notes = "商户名称")
    private String shopName;
	
	@ApiModelProperty(notes = "客户名称")
	private String customerName;
    
	@ApiModelProperty(notes = "客户简称")
    private String shortName;
    
	@ApiModelProperty(notes = "客户编码")
    private String customerNum;
    
	@ApiModelProperty(notes = "交易状态")
    private String status;
	
	@ApiModelProperty("标签Id集合")
    private List<Long> labelIds;
    
    @ApiModelProperty("状态：true-启用；false-禁用")
    private Boolean enable;

	public Long getShopId() {
		return shopId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public String getStatus() {
		return status;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<Long> getLabelIds() {
		return labelIds;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setLabelIds(List<Long> labelIds) {
		this.labelIds = labelIds;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	
	
}
