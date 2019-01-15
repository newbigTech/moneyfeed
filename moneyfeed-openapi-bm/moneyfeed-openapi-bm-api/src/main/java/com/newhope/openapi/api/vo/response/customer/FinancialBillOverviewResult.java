package com.newhope.openapi.api.vo.response.customer;

import java.math.BigDecimal;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class FinancialBillOverviewResult  extends Result {
	private static final long serialVersionUID = -2780723379881795040L;

	@ApiModelProperty("期初待还款")
	private BigDecimal beginRepayment;
	
    @ApiModelProperty("期末待还额")
    private BigDecimal endRepayment;
    
    @ApiModelProperty(value = "交易数")
    private Integer tradingVolume;
    
    @ApiModelProperty(value = "购买量")
    private Double purchaseQuantity;
    
    @ApiModelProperty(value = "赠包数")
    private Double giftQuantity;
    
    @ApiModelProperty(value = "应付货款")
    private BigDecimal payable;
    
    @ApiModelProperty(value = "实付货款")
    private BigDecimal actual;
    
    @ApiModelProperty(value = "本期余额支付")
    private BigDecimal payByBalance;
    
    @ApiModelProperty(value = "本期银行卡支付")
    private BigDecimal payByCard;

    public Integer getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(Integer tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public Double getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Double purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Double getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Double giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public BigDecimal getPayable() {
        return payable;
    }

    public void setPayable(BigDecimal payable) {
        this.payable = payable;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public BigDecimal getPayByBalance() {
        return payByBalance;
    }

    public void setPayByBalance(BigDecimal payByBalance) {
        this.payByBalance = payByBalance;
    }

    public BigDecimal getPayByCard() {
        return payByCard;
    }

    public void setPayByCard(BigDecimal payByCard) {
        this.payByCard = payByCard;
    }

    public BigDecimal getBeginRepayment() {
        return beginRepayment;
    }

    public void setBeginRepayment(BigDecimal beginRepayment) {
        this.beginRepayment = beginRepayment;
    }

    public BigDecimal getEndRepayment() {
        return endRepayment;
    }

    public void setEndRepayment(BigDecimal endRepayment) {
        this.endRepayment = endRepayment;
    }
}
