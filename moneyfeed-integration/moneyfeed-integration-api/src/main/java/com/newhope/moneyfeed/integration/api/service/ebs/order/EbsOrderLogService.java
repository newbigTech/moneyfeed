package com.newhope.moneyfeed.integration.api.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderLogModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyanlin on 2018/11/24
 */
public interface EbsOrderLogService {

    boolean saveEbsOrderLogWithNoRollback(List<EbsOrderLogModel> ebsOrderLogList);

    boolean saveEbsOrderLogWithNoRollback(Long orderId, String saleOrderId, String logType, Date sendTime, String msgJson, String errorMsg, int failCount, Byte operationResult);

    boolean saveEbsOrderLogListWithNoRollback(Map<Long, String> ebsOrderIdSaleOrderIdMap, String logType, Date sendTime, String msgJson, String errorMsg, int failCount, Byte operationResult);

    EbsOrderLogModel queryEbsOrderLogLatestLogByOrderIdAndLogType(EbsOrderLogModel ebsOrderLogSearch);
}
