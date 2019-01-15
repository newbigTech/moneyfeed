package com.newhope.moneyfeed.order.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/17 13:36
 */
public class OrderListDtoResult extends PageDtoResult implements Serializable{
    private static final long serialVersionUID = 3445346679991291187L;
    private List<OrderDtoResult> orderList;

    public List<OrderDtoResult> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDtoResult> orderList) {
        this.orderList = orderList;
    }
}
