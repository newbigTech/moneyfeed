package com.newhope.moneyfeed.user.api.dto.request.client;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

public class UserShopApplyDtoReq {
    @ApiModelProperty("意向商城id")
    private Long shopId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("留言")
    private String comment;
    @ApiModelProperty("电话号码")
    @Pattern(regexp = "^0?(13|15|18|14|17|19)[0-9]{9}$", message = "电话号码格式不正确")
    @NotBlank(message = "电话号码不能为空")
    private String phone;
    @ApiModelProperty("称呼")
    private String name;
    @ApiModelProperty("地址")
    private String address;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
