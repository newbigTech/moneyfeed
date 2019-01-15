package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户账户-银行账户
 */
public class UcCustomerAccountBankCardModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7825443185825819249L;

	/** 客户账户id */
    private Long cutomerAccountId;

    /** 卡号 */
    private String cardNum;

    /** 银行编号 */
    private String bankCode;

    /** 银行名称 */
    private String bankName;

    public Long getCutomerAccountId() {
        return cutomerAccountId;
    }

    public void setCutomerAccountId(Long cutomerAccountId) {
        this.cutomerAccountId = cutomerAccountId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}