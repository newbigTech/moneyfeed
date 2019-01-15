package com.newhope.moneyfeed.order.api.dto.response;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Create by yyq on 2018/11/17
 */
public class OrderDetailDtoResult extends DtoResult {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8839307808694418417L;

	@ApiModelProperty(name = "orderInfoDtoResult", notes = "订单信息")
    private OrderDtoResult orderInfoDtoResult;

    @ApiModelProperty(name = "orderTransportDtoResult", notes = "司机信息")
    private OrderTransportDtoResult orderTransportDtoResult;

    @ApiModelProperty(name = "orderPresentDtoResultList", notes = "赠品信息列表")
    private List<OrderPresentDtoResult> orderPresentDtoResultList;

    @ApiModelProperty(name = "orderGoodsDtoResultList", notes = "商品信息列表")
    private List<OrderGoodsDtoResult> orderGoodsDtoResultList;

    public OrderDtoResult getOrderInfoDtoResult() {
        return orderInfoDtoResult;
    }

    public void setOrderInfoDtoResult(OrderDtoResult orderInfoDtoResult) {
        this.orderInfoDtoResult = orderInfoDtoResult;
    }

    public OrderTransportDtoResult getOrderTransportDtoResult() {
        return orderTransportDtoResult;
    }

    public void setOrderTransportDtoResult(OrderTransportDtoResult orderTransportDtoResult) {
        this.orderTransportDtoResult = orderTransportDtoResult;
    }

    public List<OrderPresentDtoResult> getOrderPresentDtoResultList() {
        return orderPresentDtoResultList;
    }

    public void setOrderPresentDtoResultList(List<OrderPresentDtoResult> orderPresentDtoResultList) {
        this.orderPresentDtoResultList = orderPresentDtoResultList;
    }

    public List<OrderGoodsDtoResult> getOrderGoodsDtoResultList() {
        return orderGoodsDtoResultList;
    }

    public void setOrderGoodsDtoResultList(List<OrderGoodsDtoResult> orderGoodsDtoResultList) {
        this.orderGoodsDtoResultList = orderGoodsDtoResultList;
    }
}
