package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("更新订单创建信息")
public class ReceiveOrderCreateInfoDtoReq implements Serializable {
	
	private static final long serialVersionUID = -4321436272306992724L;

	@ApiModelProperty(name="companyShortCode",notes="公司编码（EBS编码）")
	private String companyShortCode;
	
	@NotBlank(message="moneyfeedOrderId不能为空")
	@ApiModelProperty(name="moneyfeedOrderId",notes="商城订单ID")
	private String moneyfeedOrderId;

	@ApiModelProperty(name="ebsorderNo",notes="EBS订单编号")
	private String ebsorderNo;
	
	@ApiModelProperty(name="totalOrginalAmount",notes="订单总额（厂价）")
	private BigDecimal totalOrginalAmount;
	
	@ApiModelProperty(name="totalPayAmount",notes="实付金额（折扣价）")
	private BigDecimal totalPayAmount;
	
	@ApiModelProperty(name="totalDiscountAmount",notes="折扣金额")
	private BigDecimal totalDiscountAmount;
	
	@ApiModelProperty(name="result",notes="创建结果（成功、失败）")
	private boolean result;
	
	@ApiModelProperty(name="remark",notes="创建结果（失败原因）")
	private String remark;
	
	@ApiModelProperty(name="productList",notes="订单关联商品列表")
	private List<ProductDtoReq> productList;

	@ApiModelProperty(name="productList",notes="订单关联商品列表")
	private List<PresentFeedDtoReq> presentFeedList;

	public String getCompanyShortCode() {
		return companyShortCode;
	}

	public void setCompanyShortCode(String companyShortCode) {
		this.companyShortCode = companyShortCode;
	}
	
	public String getMoneyfeedOrderId() {
		return moneyfeedOrderId;
	}

	public void setMoneyfeedOrderId(String moneyfeedOrderId) {
		this.moneyfeedOrderId = moneyfeedOrderId;
	}

	public String getEbsorderNo() {
		return ebsorderNo;
	}

	public void setEbsorderNo(String ebsorderNo) {
		this.ebsorderNo = ebsorderNo;
	}

	public BigDecimal getTotalOrginalAmount() {
		return totalOrginalAmount;
	}

	public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
		this.totalOrginalAmount = totalOrginalAmount;
	}

	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public BigDecimal getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ProductDtoReq> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDtoReq> productList) {
		this.productList = productList;
	}

	public List<PresentFeedDtoReq> getPresentFeedList() {
		return presentFeedList;
	}

	public void setPresentFeedList(List<PresentFeedDtoReq> presentFeedList) {
		this.presentFeedList = presentFeedList;
	}
	
}
