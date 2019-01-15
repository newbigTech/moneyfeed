package com.newhope.order.biz.hepler.selector;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.enums.IdGeneraterEnum;
import com.newhope.moneyfeed.order.api.enums.OrderSysParamsTypeEnum;
import com.newhope.order.biz.constant.OrderCacheConstant;
import com.newhope.order.biz.hepler.IdGeneraterHelper;

public class PaySegmentSelector extends SegmentSelector {

	public void initQty(int maxQty, int leftQty) {
		IdGeneraterHelper.PAY_SEGMENT_MAX_QTY = maxQty;
		IdGeneraterHelper.PAY_SEGMENT_MAX_LEFT_QTY = leftQty;

	}

	public void initSysParam() {
		setSegmentMaxQty(IdGeneraterHelper.ORDER_SEGMENT_MAX_QTY);
		setSysParamType(OrderSysParamsTypeEnum.ID_GENERATION.getType());
		setSysParamCode(IdGeneraterEnum.PAY_NUMBER.getCode());
		setBizCode(IdGeneraterEnum.PAY_NUMBER.getValue());
		setErrCode(ResultCode.PAY_GEN_PAYNO_ERROR);
		setLockKey(OrderCacheConstant.PAYNO_REDISLOCK_KEY);
	}
	
	public void initError(){
		setErrCode(ResultCode.PAY_CREATE_ERROR);
	}
}
