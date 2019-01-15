package com.newhope.openapi.api.vo.response.label;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Create by yyq on 2018/12/25
 */
public class LabelListResult extends Result {
    @ApiModelProperty(name="dataList", notes="手动标签列表")
    private List<LabelResult> dataList;

    public List<LabelResult> getDataList() {
        return dataList;
    }

    public void setDataList(List<LabelResult> dataList) {
        this.dataList = dataList;
    }
}
