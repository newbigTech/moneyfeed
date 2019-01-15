package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SynSellPlanReq implements Serializable {


	private static final long serialVersionUID = 1769712161197124408L;
	@NotBlank(message = "thirdId为必传参数")
	@ApiModelProperty(name = "thirdId", notes = "业务系统主键id", required = true)
	private String thirdId;

	@ApiModelProperty(name = "launchType", notes = "上市类型:正常，紧急", required = true)
	private String launchType;

	@NotNull(message = "planSaleQuantity为必传参数")
	@ApiModelProperty(name = "planSaleQuantity", notes = "计划出栏数量", required = true)
	private Integer planSaleQuantity;

	@ApiModelProperty(name = "sowPlanSaleQuantity", notes = "母猪计划出栏数量")
	private Integer sowPlanSaleQuantity;

	@ApiModelProperty(name = "boarPlanSaleQuantity", notes = "公猪计划出栏数量")
	private Integer boarPlanSaleQuantity;

	@NotNull(message = "planDate为必传参数")
	@ApiModelProperty(name = "planDate", notes = "计划上市日期（计划交割开始日期）", required = true)
	private Date planDate;

	@NotNull(message = "planAvgWeight为必传参数")
	@ApiModelProperty(name = "planAvgWeight", notes = "预估均重")
	private BigDecimal planAvgWeight;

	@NotBlank(message = "batchId为必传参数")
	@ApiModelProperty(name = "batchId", notes = "批次ID")
	private String batchId;

	@NotBlank(message = "thirdPigFarmUid为必传参数")
	@ApiModelProperty(name = "thirdPigFarmUid", notes = "业务系统猪场UID")
	private String thirdPigFarmUid;

	@NotBlank(message = "orgId为必传参数")
	@ApiModelProperty(name = "orgId", notes = "组织id")
	private String orgId;

	@NotBlank(message = "breedsId为必传参数")
	@ApiModelProperty(name = "breeds", notes = "品种", required = true)
	private String breedsId;

	@NotBlank(message = "saleType为必传参数")
	@ApiModelProperty(name = "saleType", notes = "销售类型", required = true)
	private String saleType;
	
	@ApiModelProperty(name = "strainId", notes = "品系")
	private String strainId;

	@ApiModelProperty(name = "pictureUrlList", notes = "照片地址(完整的外部可访问的url)")
	private List<String> pictureUrlList;

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public String getLaunchType() {
		return launchType;
	}

	public void setLaunchType(String launchType) {
		this.launchType = launchType;
	}

	public Integer getPlanSaleQuantity() {
		return planSaleQuantity;
	}

	public void setPlanSaleQuantity(Integer planSaleQuantity) {
		this.planSaleQuantity = planSaleQuantity;
	}

	public Integer getSowPlanSaleQuantity() {
		return sowPlanSaleQuantity;
	}

	public void setSowPlanSaleQuantity(Integer sowPlanSaleQuantity) {
		this.sowPlanSaleQuantity = sowPlanSaleQuantity;
	}

	public Integer getBoarPlanSaleQuantity() {
		return boarPlanSaleQuantity;
	}

	public void setBoarPlanSaleQuantity(Integer boarPlanSaleQuantity) {
		this.boarPlanSaleQuantity = boarPlanSaleQuantity;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getPlanAvgWeight() {
		return planAvgWeight;
	}

	public void setPlanAvgWeight(BigDecimal planAvgWeight) {
		this.planAvgWeight = planAvgWeight;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getThirdPigFarmUid() {
		return thirdPigFarmUid;
	}

	public void setThirdPigFarmUid(String thirdPigFarmUid) {
		this.thirdPigFarmUid = thirdPigFarmUid;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public void setStrainId(	String strainId) {
		this.strainId = strainId;
	}

	public List<String> getPictureUrlList() {
		return pictureUrlList;
	}

	public void setPictureUrlList(List<String> pictureUrlList) {
		this.pictureUrlList = pictureUrlList;
	}


}
