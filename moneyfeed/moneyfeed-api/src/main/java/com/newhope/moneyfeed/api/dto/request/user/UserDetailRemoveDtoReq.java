package com.newhope.moneyfeed.api.dto.request.user;

/**
 * Created by liming on 2018/12/4.
 */
public class UserDetailRemoveDtoReq {

    private Long userId;

    private Long randomId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRandomId() {
        return randomId;
    }

    public void setRandomId(Long randomId) {
        this.randomId = randomId;
    }
}