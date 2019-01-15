package com.newhope.moneyfeed.user.api.dto.response.platform;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Create by yyq on 2018/12/20
 */
public class UcPmShopListDtoResult extends PageDtoResult {

    private static final long serialVersionUID = 4433687200745043525L;
    @ApiModelProperty(name="dataList", notes="店铺列表")
    private List<UcPmShopDtoResult> dataList;

    public List<UcPmShopDtoResult> getDataList() {
        return dataList;
    }

    public void setDataList(List<UcPmShopDtoResult> dataList) {
        this.dataList = dataList;
    }
}
