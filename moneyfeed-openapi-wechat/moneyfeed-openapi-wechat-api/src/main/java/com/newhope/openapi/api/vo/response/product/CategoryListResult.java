package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.api.vo.response.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryListResult  extends Result implements Serializable {

    private List<CategoryResult> categoryResultList = new ArrayList<>();


    public List<CategoryResult> getCategoryResultList() {
        return categoryResultList;
    }

    public void setCategoryResultList(List<CategoryResult> categoryResultList) {
        this.categoryResultList = categoryResultList;
    }
}
