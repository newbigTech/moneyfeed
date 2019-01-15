package com.newhope.moneyfeed.goods.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryListDtoResult extends DtoResult implements Serializable {

    List<CategoryDtoResult> categoryDtoResultList = new ArrayList<>();


    public List<CategoryDtoResult> getCategoryDtoResultList() {
        return categoryDtoResultList;
    }

    public void setCategoryDtoResultList(List<CategoryDtoResult> categoryDtoResultList) {
        this.categoryDtoResultList = categoryDtoResultList;
    }
}
