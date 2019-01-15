package com.newhope.moneyfeed.integration.api.vo.response.ebs;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCategoryModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by liming on 2018/7/3.
 */

@XmlRootElement(name = "DATA")
public class EBSResponeseCategoryListData {

    private List<EbsCategoryModel> dataList;

    @XmlElement(name = "HEADER",type = EbsCategoryModel.class)
    public List<EbsCategoryModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<EbsCategoryModel> dataList) {
        this.dataList = dataList;
    }
}