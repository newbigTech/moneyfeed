package com.newhope.openapi.api.vo.response.order;


import com.newhope.moneyfeed.api.vo.response.PageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 9:47
 */
public class OrderInfoListResult extends PageResult {

    private static final long serialVersionUID = 4454588644537709672L;

    private List<OrderInfoResult> orderList = new ArrayList<>();

    public List<OrderInfoResult> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderInfoResult> orderList) {
        this.orderList = orderList;
    }
}
