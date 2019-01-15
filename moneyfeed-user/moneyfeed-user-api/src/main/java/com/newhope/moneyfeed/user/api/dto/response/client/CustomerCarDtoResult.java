package com.newhope.moneyfeed.user.api.dto.response.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

public class CustomerCarDtoResult extends DtoResult {
    private Long id;
    private String carNum;
    private String driverName;
    private String driverMobile;
    private String driverCardNum;
    private Long customerId;
    private Boolean defaultFlag;
    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
