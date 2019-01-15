package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yuyanlin on 2018/11/21
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderForCancelForResultReq {

    private String companyShortCode;

    private String ebsOrderNoListString;

    @XmlElement(name = "value1")
    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    @XmlElement(name = "value2")
    public String getEbsOrderNoListString() {
        return ebsOrderNoListString;
    }

    public void setEbsOrderNoListString(String ebsOrderNoListString) {
        this.ebsOrderNoListString = ebsOrderNoListString;
    }

}
