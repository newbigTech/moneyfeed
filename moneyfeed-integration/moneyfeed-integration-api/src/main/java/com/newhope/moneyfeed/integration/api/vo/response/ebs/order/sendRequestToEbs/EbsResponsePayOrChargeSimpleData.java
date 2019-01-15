package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/12/18
 */
@XmlRootElement(name = "DATA")
public class EbsResponsePayOrChargeSimpleData implements Serializable {

    private static final long serialVersionUID = -4821882659698812160L;

    private EbsResponsePayOrChargeSimple ebsResponsePayOrChargeSimple;

    @XmlElement(name = "HEADER", type = EbsResponsePayOrChargeSimple.class)
    public EbsResponsePayOrChargeSimple getEbsResponsePayOrChargeSimple() {
        return ebsResponsePayOrChargeSimple;
    }

    public void setEbsResponsePayOrChargeSimple(EbsResponsePayOrChargeSimple ebsResponsePayOrChargeSimple) {
        this.ebsResponsePayOrChargeSimple = ebsResponsePayOrChargeSimple;
    }
}
