package com.newhope.moneyfeed.order.api.dto.request.order.nh;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.order.api.bean.OrderChangeLogModel;
import com.newhope.moneyfeed.order.api.bean.OrderDetailModel;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.bean.OrderSnapshotModel;

public class OrderBuilder implements Serializable {

	private static final long serialVersionUID = -250552496978369807L;

	private final OrderInfoModel orderInfo;

	private final List<OrderDetailModel> orderDetails;

	private final OrderFeedTransportModel orderFeedTransport;

	private final OrderChangeLogModel orderChangeLog;

	private final List<OrderSnapshotModel> orderSnapshots;

	private final UserOperationLogDtoReq operaLog;

	private OrderBuilder(Builder builder) {
		this.orderInfo = builder.orderInfo;
		this.orderDetails = builder.orderDetails;
		this.orderFeedTransport = builder.orderFeedTransport;
		this.orderChangeLog = builder.orderChangeLog;
		this.orderSnapshots = builder.orderSnapshots;
		this.operaLog = builder.operaLog;
	}

	public UserOperationLogDtoReq getOperaLog() {
		return operaLog;
	}

	public OrderInfoModel getOrderInfo() {
		return orderInfo;
	}

	public List<OrderDetailModel> getOrderDetails() {
		return orderDetails;
	}

	public OrderFeedTransportModel getOrderFeedTransport() {
		return orderFeedTransport;
	}

	public OrderChangeLogModel getOrderChangeLog() {
		return orderChangeLog;
	}

	public List<OrderSnapshotModel> getOrderSnapshots() {
		return orderSnapshots;
	}

	public static class Builder {
		private OrderInfoModel orderInfo;

		private List<OrderDetailModel> orderDetails;

		private OrderFeedTransportModel orderFeedTransport;

		private OrderChangeLogModel orderChangeLog;

		private List<OrderSnapshotModel> orderSnapshots;

		private UserOperationLogDtoReq operaLog;

		private Builder() {

		}

		public static Builder newInstance() {
			return new Builder();
		}

		public Builder orderInfo(OrderInfoModel orderInfo) {
			this.orderInfo = orderInfo;
			return this;
		}

		public Builder orderDetails(List<OrderDetailModel> orderDetails) {
			this.orderDetails = orderDetails;
			return this;
		}

		public Builder orderFeedTransport(OrderFeedTransportModel orderFeedTransport) {
			this.orderFeedTransport = orderFeedTransport;
			return this;
		}

		public Builder orderChangeLog(OrderChangeLogModel orderChangeLog) {
			this.orderChangeLog = orderChangeLog;
			return this;
		}

		public Builder orderSnapshot(List<OrderSnapshotModel> orderSnapshots) {
			this.orderSnapshots = orderSnapshots;
			return this;
		}

		public Builder opreaLog(UserOperationLogDtoReq operaLog) {
			this.operaLog = operaLog;
			return this;
		}

		public OrderBuilder build() {
			return new OrderBuilder(this);
		}
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
