package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Dave Chen on 2018/11/27.
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class QueryEbsOrderInfoReq {
    private  String companyId;
    private  String ebsOrderNo;

    @XmlElement(name = "inv:VALUE1")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @XmlElement(name = "inv:VALUE2")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }
}
