package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/11/23
 */
@XmlRootElement(name = "HEADER")
public class EbsResponseCreateSimple implements Serializable {

    private static final long serialVersionUID = -2757282574480834351L;

    // 创建结果（成功、失败）
    private Boolean returnCode;

    // 创建结果（失败原因）
    private String returnMsg;

    @XmlElement(name = "RETURN_CODE")
    public Boolean getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Boolean returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "RETURN_MESG")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
