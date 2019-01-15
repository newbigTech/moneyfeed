package com.newhope.moneyfeed.integration.api.vo.response.ebs;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsMaterialModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by liming on 2018/7/10.
 */
@XmlRootElement(name = "DATA")
public class EBSResponseMaterialListData {

    private List<EbsMaterialModel> dataList;


    @XmlElement(name = "HEADER",type = EbsMaterialModel.class)
    public List<EbsMaterialModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<EbsMaterialModel> dataList) {
        this.dataList = dataList;
    }
}