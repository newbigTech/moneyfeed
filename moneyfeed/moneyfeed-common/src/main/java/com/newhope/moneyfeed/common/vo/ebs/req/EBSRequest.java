package com.newhope.moneyfeed.common.vo.ebs.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liming on 2018/7/5.
 */

@XmlRootElement(name = "esb:REQUEST")
public class EBSRequest {



    private EBSAttrs ebsAttrs=new EBSAttrs();



    private String requestData;


    @XmlElement(name = "esb:ESB_ATTRS")
    public EBSAttrs getEbsAttrs() {
        return ebsAttrs;
    }

    public void setEbsAttrs(EBSAttrs ebsAttrs) {
        this.ebsAttrs = ebsAttrs;
    }

    @XmlElement(name = "esb:REQUEST_DATA")
    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
}