package com.newhope.openapi.api.vo.response.customer;


import java.util.Date;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.response.label.LabelResult;
import com.newhope.openapi.api.vo.response.shop.ShopResult;

import io.swagger.annotations.ApiModelProperty;


public class CustomerResult extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454017345089758814L;

	@ApiModelProperty("主键ID")
    private Long id;

	@ApiModelProperty("创建时间")
    private Date gmtCreated;

	@ApiModelProperty("客户编码")
    private String customerNum;

	@ApiModelProperty("客户名称")
    private String customerName;

	@ApiModelProperty("客户简称")
    private String shortName;
    
    @ApiModelProperty("身份证/税号")
    private String cardNum;
    
    @ApiModelProperty("交易状态")
    private String tradingStatus;
    
    @ApiModelProperty("备注")
    private String comment;
    
    @ApiModelProperty("标签集合")
    private List<LabelResult> labels;
    
    @ApiModelProperty("状态：true-启用；false-禁用")
    private Boolean enable;
    
    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("类型：个人/企业")
    private String type;
    
    @ApiModelProperty("客户地址")
    private String customerAddr;
    
    @ApiModelProperty("签约商铺")
    private List<ShopResult> shops;
    
	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public Long getId() {
		return id;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
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

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getEnable() {
		return enable;
	}

	public String getType() {
		return type;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public List<ShopResult> getShops() {
		return shops;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public void setShops(List<ShopResult> shops) {
		this.shops = shops;
	}

	public List<LabelResult> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelResult> labels) {
		this.labels = labels;
	}
	

}