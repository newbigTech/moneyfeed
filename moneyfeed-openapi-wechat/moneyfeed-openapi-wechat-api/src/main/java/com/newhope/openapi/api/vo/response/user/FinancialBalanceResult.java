package com.newhope.openapi.api.vo.response.user;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class FinancialBalanceResult extends Result {
    @ApiModelProperty("临欠额度")
    private BigDecimal availableCredit;
    @ApiModelProperty("保证金")
    private BigDecimal returnDeposit;
    @ApiModelProperty("可用额度")
    private BigDecimal available;
    @ApiModelProperty("余额")
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }

    public BigDecimal getReturnDeposit() {
        return returnDeposit;
    }

    public void setReturnDeposit(BigDecimal returnDeposit) {
        this.returnDeposit = returnDeposit;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }
}
