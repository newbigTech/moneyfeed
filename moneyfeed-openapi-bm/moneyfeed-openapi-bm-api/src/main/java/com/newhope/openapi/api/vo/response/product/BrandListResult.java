package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.api.vo.response.PageResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-openapi-bm
 * @date : 2018/12/24 08:48
 */
public class BrandListResult extends PageResult implements Serializable {
    private static final long serialVersionUID = 3687991297346154417L;

    @ApiModelProperty(name = "brandResults", notes = "品牌数据")
    private List<BrandResult> brandResults;

    public List<BrandResult> getBrandResults() {
        return brandResults;
    }

    public void setBrandResults(List<BrandResult> brandResults) {
        this.brandResults = brandResults;
    }
}
