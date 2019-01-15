package com.newhope.moneyfeed.goods.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaleCategoryListDtoResult extends DtoResult implements Serializable {

    private static final long serialVersionUID = -128641010226661546L;

    List<SaleCategoryDtoResult> saleCategoryDtoResultList = new ArrayList<>();

    public List<SaleCategoryDtoResult> getSaleCategoryDtoResultList() {
        return saleCategoryDtoResultList;
    }

    public void setSaleCategoryDtoResultList(List<SaleCategoryDtoResult> saleCategoryDtoResultList) {
        this.saleCategoryDtoResultList = saleCategoryDtoResultList;
    }
}
