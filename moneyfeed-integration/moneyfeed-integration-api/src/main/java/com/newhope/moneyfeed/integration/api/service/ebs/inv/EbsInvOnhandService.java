package com.newhope.moneyfeed.integration.api.service.ebs.inv;

import java.util.List;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.inv.EbsItemOnhandDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDtoResult;

public interface EbsInvOnhandService {
	/**
	 * 客户余额查询
	 * */
	EbsItemOnhandDtoResult ebsItemOnhandQuery(List<EbsItemOnhandDtoReq> reqList);
}
