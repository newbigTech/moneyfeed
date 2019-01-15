package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaleCategoryListResult  extends Result implements Serializable {

    @ApiModelProperty(name = "saleCategoryResultList", notes = "销售目录集合")
    private List<SaleCategoryResult> saleCategoryResultList = new ArrayList<>();

    public List<SaleCategoryResult> getSaleCategoryResultList() {
        return saleCategoryResultList;
    }

    public void setSaleCategoryResultList(List<SaleCategoryResult> saleCategoryResultList) {
        this.saleCategoryResultList = saleCategoryResultList;
    }
}
