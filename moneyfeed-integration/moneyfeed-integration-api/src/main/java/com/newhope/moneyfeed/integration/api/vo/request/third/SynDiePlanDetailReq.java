package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;


public class SynDiePlanDetailReq implements Serializable {

	private static final long serialVersionUID = 4405594470910093945L;

	@NotNull(message = "dieQuantity为必传参数")
	@Min(value=0,message = "dieQuantity最小为0")
	@ApiModelProperty(name = "dieQuantity", notes = "猪只死淘数", required = true)
	private Integer dieQuantity;

	@ApiModelProperty(name = "operType", notes = "死淘操作类型", required = true)
	private String operType;
	
	@ApiModelProperty(name = "sowDieQuantity", notes = "母猪死淘数")
	private Integer sowDieQuantity;
	
	@ApiModelProperty(name = "boarDieQuantity", notes = "公猪死淘数")
	private Integer boarDieQuantity;
	
	@NotBlank(message = "dieDate为必传参数")
	@ApiModelProperty(name = "dieDate", notes = "死淘日期", required = true)
	private Date dieDate;
	
	@NotBlank(message = "batchId为必传参数")
	@ApiModelProperty(name = "batchId", notes = "猪只批次", required = true)
	private String batchId;
	
	@NotBlank(message = "breedsId为必传参数")
	@ApiModelProperty(name = "breedsId", notes = "猪只品种", required = true)
	private String breedsId;
	
	@ApiModelProperty(name = "strainId", notes = "猪只品系")
	private String strainId;
	
	@NotBlank(message = "saleType为必传参数")
	@ApiModelProperty(name = "saleType", notes = "猪只销售类型", required = true)
	private String saleType;

	public Integer getDieQuantity() {
		return dieQuantity;
	}

	public void setDieQuantity(Integer dieQuantity) {
		this.dieQuantity = dieQuantity;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Integer getSowDieQuantity() {
		return sowDieQuantity;
	}

	public void setSowDieQuantity(Integer sowDieQuantity) {
		this.sowDieQuantity = sowDieQuantity;
	}

	public Integer getBoarDieQuantity() {
		return boarDieQuantity;
	}

	public void setBoarDieQuantity(Integer boarDieQuantity) {
		this.boarDieQuantity = boarDieQuantity;
	}

	public Date getDieDate() {
		return dieDate;
	}

	public void setDieDate(Date dieDate) {
		this.dieDate = dieDate;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBreedsId() {
		return breedsId;
	}

	public void setBreedsId(String breedsId) {
		this.breedsId = breedsId;
	}

	public String getStrainId() {
		return strainId;
	}

	public void setStrainId(String strainId) {
		this.strainId = strainId;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
}
