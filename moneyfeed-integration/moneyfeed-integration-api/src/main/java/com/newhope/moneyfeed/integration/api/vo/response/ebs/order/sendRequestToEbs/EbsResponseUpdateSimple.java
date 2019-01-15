package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yuyanlin on 2018/11/30
 */
public class EbsResponseUpdateSimple {

    private static final long serialVersionUID = -2757282574480834351L;

    private String orgId;

    private String ebsOrderNo;

    private String carNo;

    private String planDate;

    private String reservationFlag;

    // 创建结果（成功、失败）
    private String returnCode;

    // 创建结果（失败原因）
    private String returnMsg;

    @XmlElement(name = "RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "RETURN_MESG")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @XmlElement(name = "EBS_ORDER_NO")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    @XmlElement(name = "CAR_NO")
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @XmlElement(name = "PLAN_DATE")
    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    @XmlElement(name = "RESERVATION_FLAG")
    public String getReservationFlag() {
        return reservationFlag;
    }

    public void setReservationFlag(String reservationFlag) {
        this.reservationFlag = reservationFlag;
    }

    @XmlElement(name = "ORG_ID")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
