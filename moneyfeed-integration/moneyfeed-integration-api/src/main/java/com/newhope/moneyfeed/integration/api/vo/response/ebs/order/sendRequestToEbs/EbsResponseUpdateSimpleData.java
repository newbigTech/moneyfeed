package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs;

import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponseCreateResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/11/30
 */
@XmlRootElement(name = "DATA")
public class EbsResponseUpdateSimpleData implements Serializable {

    private static final long serialVersionUID = 134435251009609383L;

    private EbsResponseUpdateSimple ebsResponseUpdateSimple;

    @XmlElement(name = "HEADER")
    public EbsResponseUpdateSimple getEbsResponseUpdateSimple() {
        return ebsResponseUpdateSimple;
    }

    public void setEbsResponseUpdateSimple(EbsResponseUpdateSimple ebsResponseUpdateSimple) {
        this.ebsResponseUpdateSimple = ebsResponseUpdateSimple;
    }
}
