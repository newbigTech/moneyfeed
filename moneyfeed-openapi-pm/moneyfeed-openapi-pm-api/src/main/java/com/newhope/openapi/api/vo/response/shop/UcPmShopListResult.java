package com.newhope.openapi.api.vo.response.shop;

import com.newhope.moneyfeed.api.vo.response.PageResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Create by yyq on 2018/12/25
 */
public class UcPmShopListResult extends PageResult {

    private static final long serialVersionUID = -7388061557032705758L;
    @ApiModelProperty(name="dataList", notes="店铺列表")
    private List<UcPmShopResult> dataList;

    public List<UcPmShopResult> getDataList() {
        return dataList;
    }

    public void setDataList(List<UcPmShopResult> dataList) {
        this.dataList = dataList;
    }
}
