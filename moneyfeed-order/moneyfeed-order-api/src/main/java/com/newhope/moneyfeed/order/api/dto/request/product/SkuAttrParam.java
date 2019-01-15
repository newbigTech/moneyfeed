package com.newhope.moneyfeed.order.api.dto.request.product;

import java.io.Serializable;

public class SkuAttrParam implements Serializable {

	private static final long serialVersionUID = -7352695183478753931L;

	/** 属性名 */
	private String name;

	/** 属性值 */
	private String parmValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParmValue() {
		return parmValue;
	}

	public void setParmValue(String parmValue) {
		this.parmValue = parmValue;
	}

}
