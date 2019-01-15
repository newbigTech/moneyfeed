package com.newhope.moneyfeed.api.dto.request.user;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.util.List;

/**
 * Created by liming on 2018/11/24.
 */
public class UserRemoveDtoReq {

    private List<Long> userIds;

    private String thirdSource;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getThirdSource() {
        return thirdSource;
    }

    public void setThirdSource(String thirdSource) {
        this.thirdSource = thirdSource;
    }
}