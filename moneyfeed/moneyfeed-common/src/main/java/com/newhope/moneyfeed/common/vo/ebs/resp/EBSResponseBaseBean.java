package com.newhope.moneyfeed.common.vo.ebs.resp;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by liming on 2018/7/3.
 */



public class EBSResponseBaseBean implements Serializable {


    private static final long serialVersionUID = -4160337481890930393L;

    private String  returnCode;


    private String returnMsg;



    @XmlElement(name = "esb:RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "esb:RETURN_DESC")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}