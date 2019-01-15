package com.newhope.moneyfeed.integration.dal.dao.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderLogModel;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;

import java.util.List;

public interface EbsOrderLogDao extends BaseDao<EbsOrderLogModel> {

   EbsOrderLogModel queryEbsOrderLogLatestLogByOrderIdAndLogType(EbsOrderLogModel ebsOrderLogSearch);

   List<EbsOrderLogModel> queryFailSendMqLog();

}