package com.newhope.openapi.api.vo.response.product;

import java.io.Serializable;

import com.newhope.moneyfeed.api.vo.response.Result;

public class SaleCategoryResult extends Result implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String saleCateId;

    private String saleCaleName;

    private String parentSaleCateId;

    private Integer saleCateLevel;

    public String getSaleCateId() {
        return saleCateId;
    }

    public void setSaleCateId(String saleCateId) {
        this.saleCateId = saleCateId;
    }

    public String getSaleCaleName() {
        return saleCaleName;
    }

    public void setSaleCaleName(String saleCaleName) {
        this.saleCaleName = saleCaleName;
    }

    public String getParentSaleCateId() {
        return parentSaleCateId;
    }

    public void setParentSaleCateId(String parentSaleCateId) {
        this.parentSaleCateId = parentSaleCateId;
    }

    public Integer getSaleCateLevel() {
        return saleCateLevel;
    }

    public void setSaleCateLevel(Integer saleCateLevel) {
        this.saleCateLevel = saleCateLevel;
    }
}
