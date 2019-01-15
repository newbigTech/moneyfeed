package com.newhope.moneyfeed.user.api.dto.request.client;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CustomerCarDtoReq implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1630133213946670183L;
	
	@ApiModelProperty("id，修改与删除时必须传入该字段")
    private Long id;
    @ApiModelProperty("用户id")
    @NotNull(message = "用户Id不能为空")
    private Long userId;
    @ApiModelProperty("车牌号")
    @NotBlank(message = "车牌号不能为空")
    private String carNum;
    @ApiModelProperty("司机姓名")
    private String driverName;
    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String driverMobile;
    @ApiModelProperty("身份证")
    private String driverCardNum;
    @ApiModelProperty(value = "是否默认车辆，0为否，1为是", allowableValues = "0,1")
    private Boolean defaultFlag;

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

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
