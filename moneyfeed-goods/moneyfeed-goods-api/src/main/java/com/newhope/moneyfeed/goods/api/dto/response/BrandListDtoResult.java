package com.newhope.moneyfeed.goods.api.dto.response;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

import java.io.Serializable;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:35
 */
public class BrandListDtoResult extends PageDtoResult implements Serializable {

    private List<BrandDtoResult> brandDtoResults;

    public List<BrandDtoResult> getBrandDtoResults() {
        return brandDtoResults;
    }

    public void setBrandDtoResults(List<BrandDtoResult> brandDtoResults) {
        this.brandDtoResults = brandDtoResults;
    }
}
