package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import java.util.List;

/**
 * 查询EBS订单状态
 * Created by Dave Chen on 2018/11/22.
 */
public class QueryEbsOrderDetailInfoDtoReq {

    private List<String> moneyfeedOrderIds;

    public List<String> getMoneyfeedOrderIds() {
        return moneyfeedOrderIds;
    }

    public void setMoneyfeedOrderIds(List<String> moneyfeedOrderIds) {
        this.moneyfeedOrderIds = moneyfeedOrderIds;
    }
}
