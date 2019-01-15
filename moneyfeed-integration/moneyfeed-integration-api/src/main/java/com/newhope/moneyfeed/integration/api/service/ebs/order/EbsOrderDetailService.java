package com.newhope.moneyfeed.integration.api.service.ebs.order;

import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailExModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ebs order detail 表对应的基础服务
 *
 * Created by yuyanlin on 2018/11/20
 */
public interface EbsOrderDetailService {

    List<EbsOrderDetailExModel> queryEbsOrderDetailExModelList(EbsOrderDetailExModel ebsOrderDetailExSearch);

    Map<String, List<String>> queryCompanyCodeEbsOrderNoListMapFromDetailExList(List<EbsOrderDetailExModel> ebsOrderDetailExList);

    //查询需要锁库的订单信息
    List<EbsOrderDetailExModel> queryLockWarehouseDetailList(String orgId,Date planDate);
}
