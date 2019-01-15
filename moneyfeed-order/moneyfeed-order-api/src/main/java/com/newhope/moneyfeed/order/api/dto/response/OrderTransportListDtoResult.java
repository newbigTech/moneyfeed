package com.newhope.moneyfeed.order.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;


public class OrderTransportListDtoResult extends DtoResult  implements Serializable {

	private static final long serialVersionUID = 327978939660094295L;
	private List<OrderFeedTransportModel> orderFeedTransports;

	public List<OrderFeedTransportModel> getOrderFeedTransports() {
		return orderFeedTransports;
	}

	public void setOrderFeedTransports(List<OrderFeedTransportModel> orderFeedTransports) {
		this.orderFeedTransports = orderFeedTransports;
	}

}
