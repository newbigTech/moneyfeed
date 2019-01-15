package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yuyanlin on 2018/11/21
 */
@XmlRootElement(name = "DATA")
public class EbsResponseCancelResultList {
	
	 private EbsResponseCancelResult ebsResponseCancelResult;

	 @XmlElement(name = "HEADER", type = EbsResponseCancelResult.class)
	public EbsResponseCancelResult getEbsResponseCancelResult() {
		return ebsResponseCancelResult;
	}

	public void setEbsResponseCancelResult(EbsResponseCancelResult ebsResponseCancelResult) {
		this.ebsResponseCancelResult = ebsResponseCancelResult;
	}
    
	 
}
