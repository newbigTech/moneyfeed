package com.newhope.moneyfeed.order.api.dto.response.present;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderPresentFeedModel;

public class OrderPresentFeedListDtoResult extends DtoResult implements Serializable {

	private static final long serialVersionUID = 1469817842315891049L;

	private List<OrderPresentFeedModel> orderPresentFeeds;

	public List<OrderPresentFeedModel> getOrderPresentFeeds() {
		return orderPresentFeeds;
	}

	public void setOrderPresentFeeds(List<OrderPresentFeedModel> orderPresentFeeds) {
		this.orderPresentFeeds = orderPresentFeeds;
	}

}
