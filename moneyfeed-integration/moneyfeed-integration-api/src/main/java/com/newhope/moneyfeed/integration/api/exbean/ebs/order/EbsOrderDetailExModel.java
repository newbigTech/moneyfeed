package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Dave Chen on 2018/11/22.
 */
public class EbsOrderDetailExModel extends EbsOrderDetailModel {
    private String saleOrderId;

    private String ebsOrderNo;

    /**
     * EBS公司编号
     */
    private String companyCode;

    private  String comanyId;

    /*** 查询条件 ***/
    private List<String> saleOrderIdList;

    private Byte excludeStockCanUse;

    private Date beginPlanTransportTime;

    public Date getBeginPlanTransportTime() {
        return beginPlanTransportTime;
    }

    public void setBeginPlanTransportTime(Date beginPlanTransportTime) {
        this.beginPlanTransportTime = beginPlanTransportTime;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public List<String> getSaleOrderIdList() {
        return saleOrderIdList;
    }

    public void setSaleOrderIdList(List<String> saleOrderIdList) {
        this.saleOrderIdList = saleOrderIdList;
    }

    public Byte getExcludeStockCanUse() {
        return excludeStockCanUse;
    }

    public void setExcludeStockCanUse(Byte excludeStockCanUse) {
        this.excludeStockCanUse = excludeStockCanUse;
    }

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getComanyId() {
        return comanyId;
    }

    public void setComanyId(String comanyId) {
        this.comanyId = comanyId;
    }
}
