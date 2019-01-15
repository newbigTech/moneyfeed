package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-openapi-pm
 * @date : 2018/12/11 10:32
 */
public class ProductSyncHeartbeatResult extends Result implements Serializable {
    private static final long serialVersionUID = 2081876178005875062L;

    @ApiModelProperty(name = "status", notes = "同步状态 [RUNNING-运行中、COMPLETE-已完成]")
    private Object status;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
