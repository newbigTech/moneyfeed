package com.newhope.openapi.api.vo.response.user;

import com.newhope.moneyfeed.api.vo.response.PageResult;
import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Create by yyq on 2018/12/25
 */
public class UcPmLabelListResult extends PageResult {

    private static final long serialVersionUID = -8871043129463164753L;
    @ApiModelProperty(name="dataList", notes="标签列表")
    private List<UcPmLabelResult> dataList;

    public List<UcPmLabelResult> getDataList() {
        return dataList;
    }

    public void setDataList(List<UcPmLabelResult> dataList) {
        this.dataList = dataList;
    }
}
