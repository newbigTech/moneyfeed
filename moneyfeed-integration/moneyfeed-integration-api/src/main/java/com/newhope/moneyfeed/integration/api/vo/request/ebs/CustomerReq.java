package com.newhope.moneyfeed.integration.api.vo.request.ebs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liming on 2018/9/11.
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class CustomerReq {

    private String companyCode;

    private String beginTime;

    private String endTime;

    @XmlElement(name = "inv:VALUE1")
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    @XmlElement(name = "inv:VALUE5")
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @XmlElement(name = "inv:VALUE6")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}