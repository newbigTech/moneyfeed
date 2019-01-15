package com.newhope.moneyfeed.goods.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdProductListDtoResult  extends DtoResult implements Serializable {

    List<AdProductDtoResult> adProductDtoResultList = new ArrayList<>();

    public List<AdProductDtoResult> getAdProductDtoResultList() {
        return adProductDtoResultList;
    }

    public void setAdProductDtoResultList(List<AdProductDtoResult> adProductDtoResultList) {
        this.adProductDtoResultList = adProductDtoResultList;
    }
}
