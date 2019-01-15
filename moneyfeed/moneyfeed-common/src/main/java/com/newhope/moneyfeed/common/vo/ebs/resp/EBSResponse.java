package com.newhope.moneyfeed.common.vo.ebs.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liming on 2018/7/5.
 */
@XmlRootElement(name = "soapenv:Envelope")
public class EBSResponse {



    private EBSResponseBody ebsResponseBody;

    @XmlElement(name = "env:Body")
    public EBSResponseBody getEbsResponseBody() {
        return ebsResponseBody;
    }

    public void setEbsResponseBody(EBSResponseBody ebsResponseBody) {
        this.ebsResponseBody = ebsResponseBody;
    }
}