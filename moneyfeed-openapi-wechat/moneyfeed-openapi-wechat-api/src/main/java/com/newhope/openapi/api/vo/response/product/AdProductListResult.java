package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.api.vo.response.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdProductListResult extends Result implements Serializable {

    private List<CategoryAdProduct> categoryAdProductList = new ArrayList<>();

    public List<CategoryAdProduct> getAdProductResults() {
        return categoryAdProductList;
    }

    public void setAdProductResults(List<CategoryAdProduct> adProductResults) {
        this.categoryAdProductList = adProductResults;
    }

    public class CategoryAdProduct{
        private  String categoryId;
        private  String categoryName;
        private List<AdProductResult> adProductResults = new ArrayList<>();

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<AdProductResult> getAdProductResults() {
            return adProductResults;
        }

        public void setAdProductResults(List<AdProductResult> adProductResults) {
            this.adProductResults = adProductResults;
        }
    }
}
