package com.newhope.moneyfeed.common.vo.ebs.req;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by liming on 2018/7/5.
 */

@XmlRootElement(name = "soapenv:Envelope")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "xmlnsSoapenv",
        "xmlnsEsb",
        "ebsHeader",
        "ebsBody"

})
public class EBSRequsetBaseBean implements Serializable {

    private static final long serialVersionUID = -6804507075768930398L;


    private String xmlnsSoapenv="http://schemas.xmlsoap.org/soap/envelope/";


    private String xmlnsEsb="http://w3.ibm.com/gbs/ais/ei/esb";


    private EBSHeader ebsHeader=new EBSHeader();



    private EBSBody ebsBody=new EBSBody();




    @XmlAttribute(name = "xmlns:soapenv")
    public String getXmlnsSoapenv() {
        return xmlnsSoapenv;
    }

    public void setXmlnsSoapenv(String xmlnsSoapenv) {
        this.xmlnsSoapenv = xmlnsSoapenv;
    }

    @XmlAttribute(name = "xmlns:esb")
    public String getXmlnsEsb() {
        return xmlnsEsb;
    }

    public void setXmlnsEsb(String xmlnsEsb) {
        this.xmlnsEsb = xmlnsEsb;
    }

    @XmlElement(name = "soapenv:Header")
    public EBSHeader getEbsHeader() {
        return ebsHeader;
    }

    public void setEbsHeader(EBSHeader ebsHeader) {
        this.ebsHeader = ebsHeader;
    }

    @XmlElement(name = "soapenv:Body")
    public EBSBody getEbsBody() {
        return ebsBody;
    }

    public void setEbsBody(EBSBody ebsBody) {
        this.ebsBody = ebsBody;
    }


    public void  createUniqueSnNum(String sn){

        this.getEbsBody().getEbsRequest().getEbsAttrs().setEsbSn(sn);
    }


    public void setBase64RequestParam(String data){

        this.getEbsBody().getEbsRequest().setRequestData(data);

    }

}