package com.newhope.moneyfeed.integration.api.vo.response.ebs;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCustomerModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author liming
 * @date 2018/9/11
 */
@XmlRootElement(name = "DATA")
public class EbsCustomerList implements Serializable {

    private static final long serialVersionUID = 4813098353288154456L;


    private List<EbsCustomerModel> dataList;


    @XmlElement(name = "HEADER")
    public List<EbsCustomerModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<EbsCustomerModel> dataList) {
        this.dataList = dataList;
    }
}