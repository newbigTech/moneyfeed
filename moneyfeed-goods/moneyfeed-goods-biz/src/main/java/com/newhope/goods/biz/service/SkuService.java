package com.newhope.goods.biz.service;

import java.util.Date;

public interface SkuService {

	/**
	 * 初始化sku列表，仅仅包含重量
	 * @param fromDate
	 * @return
	 */
	 boolean allSkuInit(Date fromDate);
}
