package com.newhope.moneyfeed.order.api.dto.request.order;

import com.newhope.moneyfeed.order.api.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/17 13:36
 */
public class OrderInfoQueryDtoReq extends PageDtoReq {

	private static final long serialVersionUID = 2084304088645463235L;
	@ApiModelProperty(name = "id", notes = "订单主键id")
	private Long id;
	@ApiModelProperty(name = "orderNo", notes = "订单号")
	private String orderNo;
	@ApiModelProperty(name = "ebsorderNo", notes = "ebs订单号")
	private String ebsorderNo;
	@ApiModelProperty(name = "refundOrderNo", notes = "退款订单号")
	private String refundOrderNo;
	@ApiModelProperty(name = "buyerId", notes = "买家id")
	private Long buyerId;
	@ApiModelProperty(name = "customerId", notes = "客户ID")
	private Long customerId;
	@ApiModelProperty(name = "customerName", notes = "客户名")
	private String customerName;
	@ApiModelProperty(name = "statusList", notes = "订单状态集合")
	private List<String> statusList;
	@ApiModelProperty(name = "orderBy", notes = "排序方式")
	private String orderBy;
	@ApiModelProperty(name = "ucShopId", notes = "商户店铺ID")
	private Long ucShopId;
	@ApiModelProperty(name = "shortNo", notes = "订单展示号")
	private String shortNo;
	@ApiModelProperty(name = "moneyNo", notes = "资金订单号")
	private String moneyNo;
	@ApiModelProperty(name = "ebsRefundOrderNo", notes = "EBS退款订单号")
	private String ebsRefundOrderNo;
	@ApiModelProperty(name = "bankRefundOrderNo", notes = "银行退款流水号")
	private String bankRefundOrderNo;
	@ApiModelProperty(name = "bankOrderNo", notes = "银行流水号")
	private String bankOrderNo;
	@ApiModelProperty(name = "orderBeginDate", notes = "下单开始日期，格式yyyy/MM/dd")
	private String orderBeginDate;
	@ApiModelProperty(name = "orderEndDate", notes = "下单结束日期，格式yyyy/MM/dd")
	private String orderEndDate;
	@ApiModelProperty(name = "pullBeginDate", notes = "拉料开始时间yyyy/MM/dd")
	private String pullBeginDate;
	@ApiModelProperty(name = "pullEndDate", notes = "拉料结束时间yyyy/MM/dd")
	private String pullEndDate;
	@ApiModelProperty(name = "completeBeginDate", notes = "完成起始时间yyyy/MM/dd")
	private String completeBeginDate;
	@ApiModelProperty(name = "completeEndDate", notes = "完成结束时间yyyy/MM/dd")
	private String completeEndDate;
	@ApiModelProperty(name = "closeBeginDate", notes = "关闭起始时间yyyy/MM/dd")
	private String closeBeginDate;
	@ApiModelProperty(name = "closeEndDate", notes = "关闭结束时间yyyy/MM/dd")
	private String closeEndDate;
	@ApiModelProperty(name = "notStatusList", notes = "不包含的订单状态集合")
	private List<String> notStatusList;
	@ApiModelProperty(name = "planTransportBeginDate", notes = "预计拉料起始日期yyyy/MM/dd")
	private String planTransportBeginDate;
	@ApiModelProperty(name = "planTransportEndDate", notes = "预计拉料结束日期yyyy/MM/dd")
	private String planTransportEndDate;
	@ApiModelProperty(name = "lableList", notes = "客户标签集合")
	private List<String> lableList;
	@ApiModelProperty(name = "relatFlag", notes = "是否连表")
	private boolean relatFlag = false;
	@ApiModelProperty(name = "payTypes", notes = "支付类型")
    private List<String> payTypes;
	@ApiModelProperty(name = "payFlag", notes = "是否已经支付成功")
    private Boolean payFlag;

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getEbsorderNo() {
		return ebsorderNo;
	}

	public void setEbsorderNo(String ebsorderNo) {
		this.ebsorderNo = ebsorderNo;
	}

	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderBeginDate() {
		return orderBeginDate;
	}

	public void setOrderBeginDate(String orderBeginDate) {
		this.orderBeginDate = orderBeginDate;
	}

	public String getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public Long getUcShopId() {
		return ucShopId;
	}

	public void setUcShopId(Long ucShopId) {
		this.ucShopId = ucShopId;
	}

	public String getShortNo() {
		return shortNo;
	}

	public void setShortNo(String shortNo) {
		this.shortNo = shortNo;
	}

	public String getMoneyNo() {
		return moneyNo;
	}

	public void setMoneyNo(String moneyNo) {
		this.moneyNo = moneyNo;
	}

	public String getEbsRefundOrderNo() {
		return ebsRefundOrderNo;
	}

	public void setEbsRefundOrderNo(String ebsRefundOrderNo) {
		this.ebsRefundOrderNo = ebsRefundOrderNo;
	}

	public String getBankRefundOrderNo() {
		return bankRefundOrderNo;
	}

	public void setBankRefundOrderNo(String bankRefundOrderNo) {
		this.bankRefundOrderNo = bankRefundOrderNo;
	}

	public String getBankOrderNo() {
		return bankOrderNo;
	}

	public void setBankOrderNo(String bankOrderNo) {
		this.bankOrderNo = bankOrderNo;
	}

	public String getCompleteBeginDate() {
		return completeBeginDate;
	}

	public void setCompleteBeginDate(String completeBeginDate) {
		this.completeBeginDate = completeBeginDate;
	}

	public String getCompleteEndDate() {
		return completeEndDate;
	}

	public void setCompleteEndDate(String completeEndDate) {
		this.completeEndDate = completeEndDate;
	}

	public String getPullBeginDate() {
		return pullBeginDate;
	}

	public void setPullBeginDate(String pullBeginDate) {
		this.pullBeginDate = pullBeginDate;
	}

	public String getPullEndDate() {
		return pullEndDate;
	}

	public void setPullEndDate(String pullEndDate) {
		this.pullEndDate = pullEndDate;
	}

	public String getCloseBeginDate() {
		return closeBeginDate;
	}

	public void setCloseBeginDate(String closeBeginDate) {
		this.closeBeginDate = closeBeginDate;
	}

	public String getCloseEndDate() {
		return closeEndDate;
	}

	public void setCloseEndDate(String closeEndDate) {
		this.closeEndDate = closeEndDate;
	}

	public List<String> getNotStatusList() {
		return notStatusList;
	}

	public void setNotStatusList(List<String> notStatusList) {
		this.notStatusList = notStatusList;
	}

	public String getPlanTransportBeginDate() {
		return planTransportBeginDate;
	}

	public void setPlanTransportBeginDate(String planTransportBeginDate) {
		this.planTransportBeginDate = planTransportBeginDate;
	}

	public String getPlanTransportEndDate() {
		return planTransportEndDate;
	}

	public void setPlanTransportEndDate(String planTransportEndDate) {
		this.planTransportEndDate = planTransportEndDate;
	}

	public boolean getRelatFlag() {
		return relatFlag;
	}

	public void setRelatFlag(boolean relatFlag) {
		this.relatFlag = relatFlag;
	}

	public List<String> getLableList() {
		return lableList;
	}

	public void setLableList(List<String> lableList) {
		this.lableList = lableList;
	}

	public Boolean getPayFlag() {
		return payFlag;
	}
	
	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}

	public List<String> getPayTypes() {
		return payTypes;
	}

	public void setPayTypes(List<String> payTypes) {
		this.payTypes = payTypes;
	}
}
