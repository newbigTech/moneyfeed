package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单信息表
 */
public class OrderCartModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5335990652142714287L;

	/** 商户店铺ID */
    private Long ucShopId;

    /** 客户号 */
    private String customerNo;

    /** 买家用户ID */
    private Long buyerId;

    /** 数量 */
    private Integer count;

    /** 商品ID */
    private Long pcProcuctId;

    /** 备注 */
    private String remark;

    public Long getUcShopId() {
        return ucShopId;
    }

    public void setUcShopId(Long ucShopId) {
        this.ucShopId = ucShopId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getPcProcuctId() {
        return pcProcuctId;
    }

    public void setPcProcuctId(Long pcProcuctId) {
        this.pcProcuctId = pcProcuctId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}