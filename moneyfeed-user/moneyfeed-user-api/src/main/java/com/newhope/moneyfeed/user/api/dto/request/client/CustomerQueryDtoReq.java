package com.newhope.moneyfeed.user.api.dto.request.client;

import java.util.List;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

import io.swagger.annotations.ApiModelProperty;

public class CustomerQueryDtoReq extends PageDtoReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4706494890299464266L;

	@ApiModelProperty(notes = "客户id")
	private Long id;

	@ApiModelProperty(notes = "客户ids")
	private List<Long> ids;
	
	@ApiModelProperty(notes = "是否可用")
    private Boolean enable;
    
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
    
	public Long getId() {
		return id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public Boolean getEnable() {
		return enable;
	}

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setLabelIds(List<Long> labelIds) {
		this.labelIds = labelIds;
	}
    
}
