package com.newhope.moneyfeed.order.api.dto.response.order;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: dongql
 * @date: 2018/12/5 14:33
 */
public class OrderGoodsDetailListDtoResult extends PageDtoResult implements Serializable{
    private static final long serialVersionUID = -5742298909218676795L;

    private List<OrderGoodsDetailDtoResult> goodsDetailList;

    public List<OrderGoodsDetailDtoResult> getGoodsDetailList() {
        return goodsDetailList;
    }

    public void setGoodsDetailList(List<OrderGoodsDetailDtoResult> goodsDetailList) {
        this.goodsDetailList = goodsDetailList;
    }
}
