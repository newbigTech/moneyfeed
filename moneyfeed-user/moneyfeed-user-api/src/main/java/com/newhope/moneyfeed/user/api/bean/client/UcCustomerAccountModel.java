package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

/**
 *   用户中心-客户账户表
 */
public class UcCustomerAccountModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4122685998666348668L;

	/** 客户id */
    private Long customerId;

    /** 账户类型(银行卡，授信，钱包) */
    private String accountType;

    /** 卡号(银行卡卡号、钱包卡号) */
    private String cardNo;

    /** 过期时间 */
    private Date expiredDate;

    /** 发卡行(工行，建行，中邮等) */
    private String issuingBank;

    /** 是否推荐支付账户 */
    private Boolean recommendFlag;

    /** 是否可用 */
    private Boolean enable;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public Boolean getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Boolean recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}