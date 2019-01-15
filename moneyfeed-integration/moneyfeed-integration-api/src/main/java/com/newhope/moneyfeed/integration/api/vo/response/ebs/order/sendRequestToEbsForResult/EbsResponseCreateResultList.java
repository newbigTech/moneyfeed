package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by yuyanlin on 2018/11/21
 */
@XmlRootElement(name = "DATA")
public class EbsResponseCreateResultList {

    private List<EbsResponseCreateResult> resultList;

    @XmlElement(name = "HEADER", type = EbsResponseCreateResult.class)
    public List<EbsResponseCreateResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<EbsResponseCreateResult> resultList) {
        this.resultList = resultList;
    }
}
