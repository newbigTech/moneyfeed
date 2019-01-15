package com.newhope.moneyfeed.common.vo.ebs.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liming on 2018/7/5.
 */
@XmlRootElement(name = "soapenv:Body")
public class EBSBody {

    private EBSRequest ebsRequest=new EBSRequest();


    @XmlElement(name = "esb:REQUEST")
    public EBSRequest getEbsRequest() {
        return ebsRequest;
    }

    public void setEbsRequest(EBSRequest ebsRequest) {
        this.ebsRequest = ebsRequest;
    }
}