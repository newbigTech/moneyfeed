package com.newhope.moneyfeed.common.vo.ebs.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by liming on 2018/7/5.
 */
@XmlType(propOrder = {
        "ebsSid",
        "esbUser",
        "ebsPassWord",
        "esbSn",
        "esbIfaceCode",
        "esbServiceId",
        "arg1",
        "arg2",
        "arg3",
        "arg4",
        "arg5"
})
public class EBSAttrs {

    private String ebsSid="MF01";

    private String esbUser="MF01";

    private String ebsPassWord="1665cad577ff1a2b472416b5b9fa1843";

    private String esbSn;

    private String esbIfaceCode="MF01005ITEMKIND001";

    private String esbServiceId="A0020000000007";

    private EBSArg arg1=new EBSArg();

    private EBSArg arg2=new EBSArg();

    private EBSArg arg3=new EBSArg();

    private EBSArg arg4=new EBSArg();

    private EBSArg arg5=new EBSArg();


    @XmlElement(name = "esb:ESB_SID")
    public String getEbsSid() {
        return ebsSid;
    }

    public void setEbsSid(String ebsSid) {
        this.ebsSid = ebsSid;
    }

    @XmlElement(name = "esb:ESB_USER")
    public String getEsbUser() {
        return esbUser;
    }

    public void setEsbUser(String esbUser) {
        this.esbUser = esbUser;
    }

    @XmlElement(name = "esb:ESB_PWD")
    public String getEbsPassWord() {
        return ebsPassWord;
    }

    public void setEbsPassWord(String ebsPassWord) {
        this.ebsPassWord = ebsPassWord;
    }

    @XmlElement(name = "esb:ESB_SN")
    public String getEsbSn() {
        return esbSn;
    }


    public void setEsbSn(String esbSn) {
        this.esbSn = esbSn;
    }
    @XmlElement(name = "esb:IFACE_CODE")
    public String getEsbIfaceCode() {
        return esbIfaceCode;
    }

    public void setEsbIfaceCode(String esbIfaceCode) {
        this.esbIfaceCode = esbIfaceCode;
    }

    @XmlElement(name = "esb:ESB_SERVICE_ID")
    public String getEsbServiceId() {
        return esbServiceId;
    }

    public void setEsbServiceId(String esbServiceId) {
        this.esbServiceId = esbServiceId;
    }

    @XmlElement(name = "esb:ARG_1")
    public EBSArg getArg1() {
        return arg1;
    }

    public void setArg1(EBSArg arg1) {
        this.arg1 = arg1;
    }

    @XmlElement(name = "esb:ARG_2")
    public EBSArg getArg2() {
        return arg2;
    }

    public void setArg2(EBSArg arg2) {
        this.arg2 = arg2;
    }

    @XmlElement(name = "esb:ARG_3")
    public EBSArg getArg3() {
        return arg3;
    }

    public void setArg3(EBSArg arg3) {
        this.arg3 = arg3;
    }

    @XmlElement(name = "esb:ARG_4")
    public EBSArg getArg4() {
        return arg4;
    }

    public void setArg4(EBSArg arg4) {
        this.arg4 = arg4;
    }

    @XmlElement(name = "esb:ARG_5")
    public EBSArg getArg5() {
        return arg5;
    }

    public void setArg5(EBSArg arg5) {
        this.arg5 = arg5;
    }


}