package com.newhope.openapi.api.vo.response.user;


import com.newhope.moneyfeed.api.vo.response.Result;

public class PasswordManageResult extends Result {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
