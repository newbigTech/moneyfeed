package com.newhope.moneyfeed.common.vo.ebs.req;

import org.apache.poi.ss.formula.functions.T;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by liming on 2018/7/5.
 */
@XmlRootElement(name = "inv:InputParameters")
@XmlType(propOrder = {
        "ifCode",
        "batchNum",
        "lineCount",
        "sourceCode",
        "pRequestLine"
})
public class EBSRequestDataBean {



    private String xmlnsInv="http://xmlns.oracle.com/apps/cux/soaprovider/plsql/cux_0_ws_server_prg/invokefmsws/";


    /**
     * ifCode，ebs给出的唯一ID
     */

    private String ifCode="";


    /**
     * sn号，调用EBSinterfaceUtil.createSn
     */

    private String batchNum;



    private String lineCount="1";



    private String sourceCode="MF01";




    private String  pRequestLine;


    @XmlAttribute(name = "xmlns:inv")
    public String getXmlnsInv() {
        return xmlnsInv;
    }

    public void setXmlnsInv(String xmlnsInv) {
        this.xmlnsInv = xmlnsInv;
    }


    @XmlElement(name="inv:P_IFACE_CODE")
    public String getIfCode() {
        return ifCode;
    }

    public void setIfCode(String ifCode) {
        this.ifCode = ifCode;
    }


    @XmlElement(name="inv:P_BATCH_NUMBER")
    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    @XmlElement(name = "inv:P_LINE_COUNT")
    public String getLineCount() {
        return lineCount;
    }

    public void setLineCount(String lineCount) {
        this.lineCount = lineCount;
    }

    @XmlElement(name = "inv:P_SOURCE_CODE")
    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }


    @XmlElement(name = "inv:P_REQUEST_LINE")
    public String getpRequestLine() {
        return pRequestLine;
    }

    public void setpRequestLine(String pRequestLine) {
        this.pRequestLine = pRequestLine;
    }


}