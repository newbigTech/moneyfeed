package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

public class UcCustomerCarModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4420625039788081589L;

	/** 车牌号 */
    private String carNum;

    /** 司机姓名 */
    private String driverName;

    /** 司机手机号 */
    private String driverMobile;

    /** 司机身份证 */
    private String driverCardNum;

    /** 客户id */
    private Long customerId;

    /** 是否默认标志 */
    private Boolean defaultFlag;

    /** 是否可用 */
    private Boolean enable;

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getDriverCardNum() {
        return driverCardNum;
    }

    public void setDriverCardNum(String driverCardNum) {
        this.driverCardNum = driverCardNum;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}