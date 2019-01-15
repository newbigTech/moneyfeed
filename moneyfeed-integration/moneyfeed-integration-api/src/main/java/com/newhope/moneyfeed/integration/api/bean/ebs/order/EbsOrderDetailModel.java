package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class EbsOrderDetailModel extends BaseModel {
    private String dataStatus;

    /** 订单创建日期 */
    private Date orderCreateDate;

    /** 订单中间件ID */
    private Long orderId;

    /** 1	已关闭	出库业务完成
            2	已输入	录入状态，还未支付
            3	已取消	取消
            4	已登记	已收款待发货
            5.             订单创建失败   没有创建成功的订单
            6.              支付失败   
            7             商城调EBS 取消失败
             */
    private String ebsOrderStatus;

    /** 进厂时间 */
    private Date carInTime;

    /** 订单关闭时间 */
    private Date closeOrderTime;

    /** 订单开票时间 */
    private Date invoiceOrderTime;

    /** 订单取消时间 */
    private Date cancelOrderTime;

    /** 出厂时间 */
    private Date carOutTime;

    /** EBS车牌号 */
    private String carNo;

    /** 进厂车辆重量 */
    private BigDecimal inCarWeight;

    /** 出厂车辆重量 */
    private BigDecimal outCarWeight;

    /** 是否库存不足 */
    private Boolean stockCanUse;

    /** 计划拉货日期 */
    private Date planTransportTime;

    /** 磅单编号 */
    private String weightNum;

    private Boolean materialChanged;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEbsOrderStatus() {
        return ebsOrderStatus;
    }

    public void setEbsOrderStatus(String ebsOrderStatus) {
        this.ebsOrderStatus = ebsOrderStatus;
    }

    public Date getCarInTime() {
        return carInTime;
    }

    public void setCarInTime(Date carInTime) {
        this.carInTime = carInTime;
    }

    public Date getCloseOrderTime() {
        return closeOrderTime;
    }

    public void setCloseOrderTime(Date closeOrderTime) {
        this.closeOrderTime = closeOrderTime;
    }

    public Date getInvoiceOrderTime() {
        return invoiceOrderTime;
    }

    public void setInvoiceOrderTime(Date invoiceOrderTime) {
        this.invoiceOrderTime = invoiceOrderTime;
    }

    public Date getCancelOrderTime() {
        return cancelOrderTime;
    }

    public void setCancelOrderTime(Date cancelOrderTime) {
        this.cancelOrderTime = cancelOrderTime;
    }

    public Date getCarOutTime() {
        return carOutTime;
    }

    public void setCarOutTime(Date carOutTime) {
        this.carOutTime = carOutTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public BigDecimal getInCarWeight() {
        return inCarWeight;
    }

    public void setInCarWeight(BigDecimal inCarWeight) {
        this.inCarWeight = inCarWeight;
    }

    public BigDecimal getOutCarWeight() {
        return outCarWeight;
    }

    public void setOutCarWeight(BigDecimal outCarWeight) {
        this.outCarWeight = outCarWeight;
    }

    public Boolean getStockCanUse() {
        return stockCanUse;
    }

    public void setStockCanUse(Boolean stockCanUse) {
        this.stockCanUse = stockCanUse;
    }

    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public String getWeightNum() {
        return weightNum;
    }

    public void setWeightNum(String weightNum) {
        this.weightNum = weightNum;
    }

    public Boolean getMaterialChanged() {
        return materialChanged;
    }

    public void setMaterialChanged(Boolean materialChanged) {
        this.materialChanged = materialChanged;
    }
}