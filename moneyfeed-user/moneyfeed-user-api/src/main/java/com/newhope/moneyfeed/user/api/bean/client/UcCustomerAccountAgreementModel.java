package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

/**
 *   用户中心-客户账户协议支付表
 */
public class UcCustomerAccountAgreementModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9012523266560606796L;

	/** 客户id */
    private Long cutomerId;

    /** 账户ID */
    private Long customerAccountId;

    /** 协议号 */
    private String agreementNo;

    /** 协议过期时间 */
    private Date agreementExpiredDate;

    /** 真实姓名 */
    private String realName;

    /** 手机号 */
    private String realMobile;

    /** 身份证号 */
    private String idCardNo;

    /** 出生日期(年月日) */
    private Date birthDate;

    /** 是否可用 */
    private Boolean enable;

    public Long getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(Long cutomerId) {
        this.cutomerId = cutomerId;
    }

    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Date getAgreementExpiredDate() {
        return agreementExpiredDate;
    }

    public void setAgreementExpiredDate(Date agreementExpiredDate) {
        this.agreementExpiredDate = agreementExpiredDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealMobile() {
        return realMobile;
    }

    public void setRealMobile(String realMobile) {
        this.realMobile = realMobile;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}