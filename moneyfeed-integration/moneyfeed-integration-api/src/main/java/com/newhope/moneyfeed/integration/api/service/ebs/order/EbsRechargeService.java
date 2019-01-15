package com.newhope.moneyfeed.integration.api.service.ebs.order;

import java.util.List;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsRechargeModel;

/**
 * Created by liuyc on 2018/12/18
 */
public interface EbsRechargeService {
	
	List<EbsRechargeModel> getPayingEbsRechargeList();
}
