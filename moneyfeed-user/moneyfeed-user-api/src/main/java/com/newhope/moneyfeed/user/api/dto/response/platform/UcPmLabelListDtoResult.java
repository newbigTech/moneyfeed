package com.newhope.moneyfeed.user.api.dto.response.platform;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Create by yyq on 2018/12/20
 */
public class UcPmLabelListDtoResult extends PageDtoResult {
    private static final long serialVersionUID = 1835757675064834445L;
    @ApiModelProperty(name="dataList", notes="标签列表")
    private List<UcPmLabelDtoResult> dataList;

    public List<UcPmLabelDtoResult> getDataList() {
        return dataList;
    }

    public void setDataList(List<UcPmLabelDtoResult> dataList) {
        this.dataList = dataList;
    }
}
