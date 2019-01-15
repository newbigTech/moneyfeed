package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   赠品表
 */
public class OrderPresentFeedModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7306295677172816782L;

	/** 订单ID */
    private Long orderId;

    /** 商品ID */
    private Long pcProductId;

    /** 商品名称 */
    private String productName;

    /** 物料编号 */
    private String suppliesCode;

    /** 公司id */
    private Long companyId;

    /** 单位 */
    private String unit;

    /** 数量 */
    private Integer count;

    /** 重量规格 */
    private String weightParam;

    /** 订单详情ID */
    private Long orderDetailId;

    /** 库存组织编码 */
    private String organizationCode;

    /** 订单号 */
    private String orderNo;

    /** 公司简码 */
    private String companyShortCode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPcProductId() {
        return pcProductId;
    }

    public void setPcProductId(Long pcProductId) {
        this.pcProductId = pcProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getWeightParam() {
        return weightParam;
    }

    public void setWeightParam(String weightParam) {
        this.weightParam = weightParam;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }
}