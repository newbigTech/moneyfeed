package com.newhope.openapi.api.vo.response.user;


import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordQueryDtoResult;
import io.swagger.annotations.ApiModelProperty;

public class PasswordQueryResult extends Result {
    @ApiModelProperty("是否有支付密码")
    private boolean hasPassword;
    @ApiModelProperty("管理支付密码的手机")
    private String phone;

    public static PasswordQueryDtoResult fail(ResultCode resultCode) {
        PasswordQueryDtoResult result = new PasswordQueryDtoResult();
        result.setMsg(resultCode.getDesc());
        result.setCode(resultCode.getCode());
        return result;
    }

    public static PasswordQueryDtoResult success(ResultCode resultCode) {
        PasswordQueryDtoResult result = new PasswordQueryDtoResult();
        result.setMsg(resultCode.getDesc());
        result.setCode(resultCode.getCode());
        return result;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
