package com.newhope.moneyfeed.order.api.exception;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;

/**
 * 
 * 初始化订单号异常
 *
 */
public class NumberGeneratorException extends BizException{

	private static final long serialVersionUID = 8891509295304008167L;

	public NumberGeneratorException(ResultCode resultCode) {
        super(resultCode.getCode(), resultCode.getDesc());
    }

    public NumberGeneratorException(Integer code, String message) {
        super(code, message);
    }

    public NumberGeneratorException(String message) {
        super(message);
    }
}
