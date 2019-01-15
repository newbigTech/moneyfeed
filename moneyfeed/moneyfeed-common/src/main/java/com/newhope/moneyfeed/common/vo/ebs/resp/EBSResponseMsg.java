package com.newhope.moneyfeed.common.vo.ebs.resp;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by liming on 2018/7/9.
 */

public class EBSResponseMsg {


    private String returnCode;

    private String returnData;

    private String returnDesc;



    @XmlElement(name = "esb:RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "esb:RETURN_DATA")
    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    @XmlElement(name = "esb:RETURN_DESC")
    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }
}