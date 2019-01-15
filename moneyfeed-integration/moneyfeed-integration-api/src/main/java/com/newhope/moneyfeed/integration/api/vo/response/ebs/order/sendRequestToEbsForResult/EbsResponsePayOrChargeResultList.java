package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by yuyanlin on 2018/12/18
 */
@XmlRootElement(name = "DATA")
public class EbsResponsePayOrChargeResultList {

    private List<EbsResponsePayOrChargeResult> resultList;

    @XmlElement(name = "HEADER", type = EbsResponsePayOrChargeResult.class)
    public List<EbsResponsePayOrChargeResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<EbsResponsePayOrChargeResult> resultList) {
        this.resultList = resultList;
    }
}
