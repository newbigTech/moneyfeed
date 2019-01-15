package com.newhope.moneyfeed.user.api.dto.response.client;


import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;

public class PasswordManageDtoResult extends DtoResult {
    private boolean success;

    public static PasswordManageDtoResult fail(ResultCode resultCode) {
        PasswordManageDtoResult result = new PasswordManageDtoResult();
        result.setMsg(resultCode.getDesc());
        result.setCode(resultCode.getCode());
        result.setSuccess(false);
        return result;
    }

    public static PasswordManageDtoResult success(ResultCode resultCode) {
        PasswordManageDtoResult result = new PasswordManageDtoResult();
        result.setMsg(resultCode.getDesc());
        result.setCode(resultCode.getCode());
        result.setSuccess(true);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
