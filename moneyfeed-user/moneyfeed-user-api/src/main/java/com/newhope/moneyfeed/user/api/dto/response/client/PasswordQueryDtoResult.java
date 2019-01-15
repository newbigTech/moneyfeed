package com.newhope.moneyfeed.user.api.dto.response.client;


import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;

public class PasswordQueryDtoResult extends Result {
    private boolean hasPassword;
    private String hexPassword;
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

    public String getHexPassword() {
        return hexPassword;
    }

    public void setHexPassword(String hexPassword) {
        this.hexPassword = hexPassword;
    }
}
