package com.newhope.moneyfeed.user.api.dto.response.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;

public class UserShopApplyResult extends DtoResult {
    private Boolean success;

    public static UserShopApplyResult success() {
        UserShopApplyResult result = new UserShopApplyResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setSuccess(true);
        return result;
    }

    public static UserShopApplyResult fail() {
        UserShopApplyResult result = new UserShopApplyResult();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMsg(ResultCode.FAIL.getDesc());
        result.success = false;
        return result;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
