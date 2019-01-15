package com.newhope.moneyfeed.goods.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegularProductListDtoResult extends DtoResult implements Serializable {

    private List<RegularProductDtoResult> regularProductDtoResultList = new ArrayList<>();

    public List<RegularProductDtoResult> getRegularProductDtoResultList() {
        return regularProductDtoResultList;
    }

    public void setRegularProductDtoResultList(List<RegularProductDtoResult> regularProductDtoResultList) {
        this.regularProductDtoResultList = regularProductDtoResultList;
    }
}
