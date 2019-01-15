package com.newhope.moneyfeed.user.api.dto.request.client;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import java.io.Serializable;

public class CustomerCarQueryDtoReq extends PageReq implements Serializable {
    private Long id;
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
