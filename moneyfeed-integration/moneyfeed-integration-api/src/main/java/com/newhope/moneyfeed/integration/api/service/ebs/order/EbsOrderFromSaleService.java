package com.newhope.moneyfeed.integration.api.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleModel;

/**
 * ebs order from sale 表相关服务
 *
 * Created by yuyanlin on 2018/11/22
 */
public interface EbsOrderFromSaleService {

    EbsOrderFromSaleModel queryOrderFromSaleByEbsOrderId(Long ebsOrderId);

}
