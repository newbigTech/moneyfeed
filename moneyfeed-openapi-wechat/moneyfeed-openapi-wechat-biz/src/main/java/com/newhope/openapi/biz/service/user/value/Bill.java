package com.newhope.openapi.biz.service.user.value;


import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;

import java.math.BigDecimal;
import java.util.List;

public class Bill {
    private List<EbsCustomerBillDetailModel> details;
    private String customerName = "test";
    private String companyName = "测试公司";
    private String date = "2018年11月23日";
    /**
     * 期初余额
     */
    private BigDecimal openingBalance;
    /**
     * 期末余额
     */
    private BigDecimal endingBalance;
    /**
     * 购买数量总数
     */
    private BigDecimal totalQuantity;
    /**
     * 应收款合计
     */
    private BigDecimal totalMustPayable;
    /**
     * 实际收款合计
     */
    private BigDecimal totalRealPayable;
    /**
     * 对账期开始时间
     */
    private String start = "2018年11月01日";
    /**
     * 对账期结束时间
     */
    private String end = "2018年11月02日";

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<EbsCustomerBillDetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<EbsCustomerBillDetailModel> details) {
        this.details = details;
    }

    public BigDecimal getTotalQuantity() {
        if (this.details != null) {
            this.totalQuantity = this.details.stream().map(EbsCustomerBillDetailModel::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return totalQuantity;
    }

    public BigDecimal getTotalMustPayable() {
        if (this.details != null) {
            this.totalMustPayable = this.details.stream().map(EbsCustomerBillDetailModel::getAccountReceivable).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return totalMustPayable;
    }

    public BigDecimal getTotalRealPayable() {
        if (this.details != null) {
            this.totalRealPayable = this.details.stream().map(EbsCustomerBillDetailModel::getFundsReceived).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return totalRealPayable;
    }
}
