package com.newhope.moneyfeed.integration.api.dto.response.ebs;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;

import java.util.List;

/**
 * Created by liming on 2018/7/16.
 */
public class EBSCompanyRespListResult extends Result {

    private List<EbsCompanyModel> dataList;

    public List<EbsCompanyModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<EbsCompanyModel> dataList) {
        this.dataList = dataList;
    }
}