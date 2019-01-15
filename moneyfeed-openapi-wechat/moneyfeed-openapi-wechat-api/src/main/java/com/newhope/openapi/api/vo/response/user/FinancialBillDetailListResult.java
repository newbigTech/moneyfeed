package com.newhope.openapi.api.vo.response.user;

import com.newhope.moneyfeed.api.vo.response.PageResult;

import java.util.List;

public class FinancialBillDetailListResult extends PageResult {
    private List<FinancialBillDetailResult> detail;

    public List<FinancialBillDetailResult> getDetail() {
        return detail;
    }

    public void setDetail(List<FinancialBillDetailResult> detail) {
        this.detail = detail;
    }
}
