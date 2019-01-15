package com.newhope.openapi.api.vo.response.customer;


import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


public class CustomerResult implements Serializable {

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
    
    @ApiModelProperty("店铺名称")
    private String shopName;
    
    @ApiModelProperty("shopId")
    private Long shopId;

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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}