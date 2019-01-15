package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs;

import com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult.EbsResponseCreateResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/11/23
 */
@XmlRootElement(name = "DATA")
public class EbsResponseCreateSimpleData implements Serializable {

    private static final long serialVersionUID = -2757282574480834351L;

    private EbsResponseCreateSimple ebsResponseCreateSimple;

    @XmlElement(name = "HEADER", type = EbsResponseCreateSimple.class)
    public EbsResponseCreateSimple getEbsResponseCreateSimple() {
        return ebsResponseCreateSimple;
    }

    public void setEbsResponseCreateSimple(EbsResponseCreateSimple ebsResponseCreateSimple) {
        this.ebsResponseCreateSimple = ebsResponseCreateSimple;
    }
}
