package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/12/18
 */
public class EbsResponsePayOrChargeSimple implements Serializable {

    private static final long serialVersionUID = 2173982754827777727L;

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
