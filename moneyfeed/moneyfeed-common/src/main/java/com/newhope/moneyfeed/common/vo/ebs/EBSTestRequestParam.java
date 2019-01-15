package com.newhope.moneyfeed.common.vo.ebs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liming on 2018/7/5.
 */
@XmlRootElement(name = "P_REQUEST_LINE_ITEM")
public class EBSTestRequestParam {


    private String value1;

    private String value2;

    @XmlElement(name = "VALUE1")
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    @XmlElement(name = "VALUE2")
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}