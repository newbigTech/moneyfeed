package com.newhope.openapi.api.vo.response.user;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarDtoResult;
import io.swagger.annotations.ApiModelProperty;

public class CustomerCarResult extends Result {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("车牌号")
    private String carNum;
    @ApiModelProperty("司机姓名")
    private String driverName;
    @ApiModelProperty("司机手机号")
    private String driverMobile;
    @ApiModelProperty("司机身份证")
    private String driverCardNum;
    @ApiModelProperty("客户id")
    private Long customerId;
    @ApiModelProperty("是否默认标志")
    private Boolean defaultFlag;
    @ApiModelProperty("是否可用")
    private Boolean enable;

    public CustomerCarResult translate(CustomerCarDtoResult carDtoResult) {
        CustomerCarResult result = new CustomerCarResult();
        result.setId(carDtoResult.getId());
        result.setCarNum(carDtoResult.getCarNum());
        result.setCustomerId(carDtoResult.getCustomerId());
        result.setDefaultFlag(carDtoResult.getDefaultFlag());
        result.setDriverMobile(carDtoResult.getDriverMobile());
        result.setDriverCardNum(carDtoResult.getDriverCardNum());
        result.setDriverName(carDtoResult.getDriverName());
        result.setEnable(carDtoResult.getEnable());
        return result;
    }

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
