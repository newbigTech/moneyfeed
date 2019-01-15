package com.newhope.moneyfeed.user.api.dto.request.client;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PasswordManageDtoReq {

    @NotBlank(message = "新密码")
    @Pattern(regexp = "^\\d{6}$", message = "密码只允许6位数字")
    @ApiModelProperty("新密码")
    private String password;
    @ApiModelProperty("再次输入的密码")
    private String repeat;
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private Long userId;
    @NotBlank(message = "手机验证码不能为空")
    @ApiModelProperty("手机验证码")
    private String code;
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String phone;
    @NotBlank(message = "身份证或税务登记证不能为空")
    @ApiModelProperty("身份证或税务登记证")
    private String cardNo;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @AssertTrue(message = "两次密码不一致")
    private boolean isSamePassword() {
        return this.password.equals(this.repeat);
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
