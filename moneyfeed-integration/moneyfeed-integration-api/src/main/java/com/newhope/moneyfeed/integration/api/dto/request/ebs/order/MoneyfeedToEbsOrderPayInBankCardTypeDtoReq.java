package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/12/17
 */
public class MoneyfeedToEbsOrderPayInBankCardTypeDtoReq implements Serializable {

    private static final long serialVersionUID = 9143120407415306072L;

    @NotBlank(message = "ebsOrderNo不能为空")
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @NotBlank(message = "cusNo不能为空")
    @ApiModelProperty(name = "cusNo", value = "客户编号")
    private String cusNo;

    @NotNull(message = "totalPayAmount不能为空")
    @ApiModelProperty(name = "totalPayAmount", value = "支付总金额")
    private BigDecimal totalPayAmount;

    @NotBlank(message = "moneyfeedPayNo不能为空")
    @ApiModelProperty(name = "moneyfeedPayNo", value = "商城支付流水号")
    private String moneyfeedPayNo;

    @NotBlank(message = "accNo不能为空")
    @ApiModelProperty(name = "accNo", value = "银行流水号（银行卡支付）")
    private String accNo;

    @NotBlank(message = "currency不能为空")
    @ApiModelProperty(name = "currency", value = "币种")
    private String currency;

    @NotBlank(message = "payDateString不能为空")
    @ApiModelProperty(name = "payDateString", value = "支付日期字符串格式")
    private String payDateString;

    @NotBlank(message = "orgId不能为空")
    @ApiModelProperty(name = "orgId", value = "ebs系统中，组织ID")
    private String orgId;

    @NotBlank(message = "ebsRealAccount不能为空")
    @ApiModelProperty(name = "ebsRealAccount", value = "ebs最终收款账号")
    private String ebsRealAccount;

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getMoneyfeedPayNo() {
        return moneyfeedPayNo;
    }

    public void setMoneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayDateString() {
        return payDateString;
    }

    public void setPayDateString(String payDateString) {
        this.payDateString = payDateString;
    }

    public String getEbsRealAccount() {
        return ebsRealAccount;
    }

    public void setEbsRealAccount(String ebsRealAccount) {
        this.ebsRealAccount = ebsRealAccount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
