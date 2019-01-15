package com.newhope.order.biz.task;

import java.util.concurrent.Callable;

import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.order.biz.hepler.NumberGeneraterHelper;
import com.newhope.order.biz.hepler.selector.SegmentSelector;

public class NumberGenerateTask implements Callable<String> {

	private SegmentSelector selector;
	
	public NumberGenerateTask(SegmentSelector selector) {
		this.selector = selector;
	}

	@Override
	public String call() throws Exception {
		NumberGeneraterHelper numberGeneraterHelper = AppContext.getSpringContext().getBean(NumberGeneraterHelper.class);
		numberGeneraterHelper.generateNumberSegment(selector);
		return null;
	}

	

}
