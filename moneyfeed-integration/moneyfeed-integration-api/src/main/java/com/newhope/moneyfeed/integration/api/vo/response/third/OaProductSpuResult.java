package com.newhope.moneyfeed.integration.api.vo.response.third;

import java.io.Serializable;

public class OaProductSpuResult implements Serializable{

	private static final long serialVersionUID = -7039205398023494073L;

	private String weightMin;

	private String weightMax;

	public String getWeightMin() {
		return weightMin;
	}

	public void setWeightMin(String weightMin) {
		this.weightMin = weightMin;
	}

	public String getWeightMax() {
		return weightMax;
	}

	public void setWeightMax(String weightMax) {
		this.weightMax = weightMax;
	}

}
