package com.newhope.openapi.api.vo.response.order;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Create by yyq on 2018/11/19
 */
public class OrderDetailResult extends Result implements Serializable {
    private static final long serialVersionUID = -2529757092332537184L;

    @ApiModelProperty(name = "orderInfoDtoResult", notes = "订单信息")
    private OrderDetailInfoResult orderInfoDtoResult;
    @ApiModelProperty(name = "orderTransportDtoResult", notes = "司机信息")
    private OrderTransportResult orderTransportDtoResult;
    @ApiModelProperty(name = "orderPresentDtoResultList", notes = "赠品信息列表")
    private List<OrderPresentResult> orderPresentDtoResultList;
    @ApiModelProperty(name = "orderGoodsDtoResultList", notes = "商品信息列表")
    private List<OrderGoodsResult> orderGoodsDtoResultList;

    public OrderDetailInfoResult getOrderInfoDtoResult() {
        return orderInfoDtoResult;
    }

    public void setOrderInfoDtoResult(OrderDetailInfoResult orderInfoDtoResult) {
        this.orderInfoDtoResult = orderInfoDtoResult;
    }

    public OrderTransportResult getOrderTransportDtoResult() {
        return orderTransportDtoResult;
    }

    public void setOrderTransportDtoResult(OrderTransportResult orderTransportDtoResult) {
        this.orderTransportDtoResult = orderTransportDtoResult;
    }

    public List<OrderPresentResult> getOrderPresentDtoResultList() {
        return orderPresentDtoResultList;
    }

    public void setOrderPresentDtoResultList(List<OrderPresentResult> orderPresentDtoResultList) {
        this.orderPresentDtoResultList = orderPresentDtoResultList;
    }

    public List<OrderGoodsResult> getOrderGoodsDtoResultList() {
        return orderGoodsDtoResultList;
    }

    public void setOrderGoodsDtoResultList(List<OrderGoodsResult> orderGoodsDtoResultList) {
        this.orderGoodsDtoResultList = orderGoodsDtoResultList;
    }
}
