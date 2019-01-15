package com.newhope.order.biz.hepler.selector;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.enums.IdGeneraterEnum;
import com.newhope.moneyfeed.order.api.enums.OrderSysParamsTypeEnum;
import com.newhope.order.biz.constant.OrderCacheConstant;
import com.newhope.order.biz.hepler.IdGeneraterHelper;

public class OrderSegmentSelector extends SegmentSelector {

	public void initQty(int maxQty, int leftQty) {
		IdGeneraterHelper.ORDER_SEGMENT_MAX_QTY = maxQty;
		IdGeneraterHelper.ORDER_SEGMENT_MAX_LEFT_QTY = leftQty;

	}

	public void initSysParam() {
		setSegmentMaxQty(IdGeneraterHelper.ORDER_SEGMENT_MAX_QTY);
		setSysParamType(OrderSysParamsTypeEnum.ID_GENERATION.getType());
		setSysParamCode(IdGeneraterEnum.ORDER_NUMBER.getCode());
		setBizCode(IdGeneraterEnum.ORDER_NUMBER.getValue());
		setErrCode(ResultCode.OC_GEN_ORDERNO_ERROR);
		setLockKey(OrderCacheConstant.ORDERNO_REDISLOCK_KEY);
	}
	
	public void initError(){
		setErrCode(ResultCode.OC_ORDER_CREATE_ERROR);
	}
}
