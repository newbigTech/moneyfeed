package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商城请求EBS支付订单，支付参数
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedToEbsOrderPayDtoReq implements Serializable {

    private static final long serialVersionUID = 5800016590289804553L;

    @ApiModelProperty(name = "companyShortCode", value = "公司编码")
    private String companyShortCode;

    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @ApiModelProperty(name = "payType", value = "支付方式（EBS_PAY、BANK_PAY）")
    private String payType;

    @ApiModelProperty(name = "accNo", value = "银行流水号（银行卡支付）")
    private String accNo;

    @ApiModelProperty(name = "totalPayAmount", value = "支付总金额")
    private BigDecimal totalPayAmount;

    @ApiModelProperty(name = "bankPayAmount", value = "银行卡支付总金额")
    private BigDecimal bankPayAmount;

    @ApiModelProperty(name = "ebsPayAmount", value = "EBS余额支付总金额")
    private BigDecimal ebsPayAmount;

    public BigDecimal getBankPayAmount() {
        return bankPayAmount;
    }

    public void setBankPayAmount(BigDecimal bankPayAmount) {
        this.bankPayAmount = bankPayAmount;
    }

    public BigDecimal getEbsPayAmount() {
        return ebsPayAmount;
    }

    public void setEbsPayAmount(BigDecimal ebsPayAmount) {
        this.ebsPayAmount = ebsPayAmount;
    }

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }
}
