package com.newhope.feedback.biz.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.feedback.api.enums.FeedbackStatusEnum;

/**
 * 售后工具类
 * @author liuyc
 * @Time 2018-11-17
 *
 */
public class ValidateUtils {
	
	public static void validateFeedbackStatus(String feedbackStatus){
		String descByCode = FeedbackStatusEnum.getDescByCode(feedbackStatus);
		if(StringUtils.isEmpty(descByCode)){
			throw new BizException("售后处理状态类型不匹配.");
		}
	}
}
