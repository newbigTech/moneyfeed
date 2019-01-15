package com.newhope.moneyfeed.integration.api.vo.request.ebs;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by liming on 2018/7/9.
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class CategoryReq {

    private String beginTime;

    private String endTime;

    private List<EbsCompanyModel> lists;


    @XmlElement(name = "inv:VALUE1")
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @XmlElement(name = "inv:VALUE2")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    @XmlElement(name = "value3")
    public List<EbsCompanyModel> getLists() {
        return lists;
    }

    public void setLists(List<EbsCompanyModel> lists) {
        this.lists = lists;
    }
}