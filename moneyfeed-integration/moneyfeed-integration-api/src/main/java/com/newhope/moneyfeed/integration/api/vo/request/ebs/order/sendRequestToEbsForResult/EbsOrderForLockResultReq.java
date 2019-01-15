package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult;

import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/11/23
 */
public class EbsOrderForLockResultReq implements Serializable {

    private static final long serialVersionUID = -326522742941637728L;

    private String companyShortCode;

    private String ebsOrderNoList;

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getEbsOrderNoList() {
        return ebsOrderNoList;
    }

    public void setEbsOrderNoList(String ebsOrderNoList) {
        this.ebsOrderNoList = ebsOrderNoList;
    }
}
