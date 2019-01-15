package com.newhope.moneyfeed.user.api.bean.client.extend;

import java.util.Date;

import com.newhope.moneyfeed.user.api.bean.client.UcCustomerModel;

import io.swagger.annotations.ApiModelProperty;

/**
 *   用户中心-客户扩展表
 */
public class UcCustomerExModel extends UcCustomerModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7770529938562531929L;

	@ApiModelProperty("交易状态")
    private String tradingStatus;
    
    @ApiModelProperty("店铺名称")
    private String shopName;
    
    @ApiModelProperty("shopId")
    private Long shopId;
    
    @ApiModelProperty("客户地址")
    private String customerAddr;
    
    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}