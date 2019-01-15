package com.newhope.openapi.api.vo.response.customer;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class FinancialBillDetailResult {
	
	@ApiModelProperty(value = "商户名称")
	private String ShopName;
	
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	
	@ApiModelProperty(value = "创建时间")
    private Date gmtCreated;
    
    @ApiModelProperty(value = "周几")
    private Integer dayOfTheWeek;
    
    @ApiModelProperty(value = "账单类型：online:线上、offline:线下")
    private String type;
    
    @ApiModelProperty(value = "车牌")
    private String licensePlate;
    
    @ApiModelProperty(value = "支付方式：合计")
    private BigDecimal totalPay;
    
    @ApiModelProperty(value = "单据号，订单号")
    private String documentNo;
    
    @ApiModelProperty(value = "0：应收合计,1：实收合计")
    private Integer totalPayType = 0;
    
    @ApiModelProperty(value = "饲料名称")
    private String name;
    
    @ApiModelProperty(value = "购买数量（吨）")
    private Double quantity;
    
    @ApiModelProperty(value = "应收款")
    private BigDecimal accountReceivable;
    
    @ApiModelProperty(value = "实收款")
    private BigDecimal fundsReceived;
    
    @ApiModelProperty(value = "规格")
    private String specification;

	public Integer getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public String getType() {
		return type;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public Integer getTotalPayType() {
		return totalPayType;
	}

	public String getName() {
		return name;
	}

	public Double getQuantity() {
		return quantity;
	}


	public String getSpecification() {
		return specification;
	}

	public void setDayOfTheWeek(Integer dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public void setTotalPayType(Integer totalPayType) {
		this.totalPayType = totalPayType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getAccountReceivable() {
		return accountReceivable;
	}

	public BigDecimal getFundsReceived() {
		return fundsReceived;
	}

	public void setAccountReceivable(BigDecimal accountReceivable) {
		this.accountReceivable = accountReceivable;
	}

	public void setFundsReceived(BigDecimal fundsReceived) {
		this.fundsReceived = fundsReceived;
	}

	public String getShopName() {
		return ShopName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setShopName(String shopName) {
		ShopName = shopName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	

}
