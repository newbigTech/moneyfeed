package com.newhope.moneyfeed.common.vo.ebs.resp;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by liming on 2018/7/6.
 */
public class EBSResponseBody {

    private EBSResponseMsg ebsResponeseMsg;


    @XmlElement(name = "esb:RESPONSE")
    public EBSResponseMsg getEbsResponeseMsg() {
        return ebsResponeseMsg;
    }

    public void setEbsResponeseMsg(EBSResponseMsg ebsResponeseMsg) {
        this.ebsResponeseMsg = ebsResponeseMsg;
    }
}