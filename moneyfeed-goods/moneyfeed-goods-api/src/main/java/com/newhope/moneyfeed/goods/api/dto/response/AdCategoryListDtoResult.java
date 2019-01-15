package com.newhope.moneyfeed.goods.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdCategoryListDtoResult  extends DtoResult implements Serializable {

    private List<AdCategoryDtoResult> adCategoryDtoResultList = new ArrayList();

    public List<AdCategoryDtoResult> getAdCategoryDtoResultList() {
        return adCategoryDtoResultList;
    }

    public void setAdCategoryDtoResultList(List<AdCategoryDtoResult> adCategoryDtoResultList) {
        this.adCategoryDtoResultList = adCategoryDtoResultList;
    }
}
