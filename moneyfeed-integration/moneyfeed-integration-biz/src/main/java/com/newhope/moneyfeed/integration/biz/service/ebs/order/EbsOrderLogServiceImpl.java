package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.google.common.collect.Lists;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderLogModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderLogModelBuilder;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderLogService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyanlin on 2018/11/24
 */
@Service
public class EbsOrderLogServiceImpl extends BaseService<EbsOrderLogModel> implements EbsOrderLogService {

    @Autowired
    private EbsOrderLogDao ebsOrderLogDao;

    @Override
    protected BaseDao<EbsOrderLogModel> getDao() {
        return ebsOrderLogDao;
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean saveEbsOrderLogWithNoRollback(List<EbsOrderLogModel> ebsOrderLogList) {
        save(ebsOrderLogList);

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean saveEbsOrderLogWithNoRollback(Long orderId, String saleOrderId, String logType, Date sendTime, String msgJson, String errorMsg, int failCount, Byte operationResult) {
        EbsOrderLogModelBuilder ebsOrderLogBuilder = EbsOrderLogModelBuilder.anEbsOrderLogModel();

        ebsOrderLogBuilder.orderId(orderId).saleOrderId(saleOrderId).logType(logType)
                .sendTime(sendTime).msgJson(msgJson).errorMsg(errorMsg)
                .failCount(failCount).operationResult(operationResult);

        saveEbsOrderLogWithNoRollback(Lists.newArrayList(ebsOrderLogBuilder.build()));

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean saveEbsOrderLogListWithNoRollback(Map<Long, String> ebsOrderIdSaleOrderIdMap, String logType, Date sendTime, String msgJson, String errorMsg, int failCount, Byte operationResult) {
        if (CollectionUtils.isEmpty(ebsOrderIdSaleOrderIdMap)) {
            return true;
        }

        List<EbsOrderLogModel> ebsOrderLogList = Lists.newArrayList();

        for (Map.Entry<Long, String> entry : ebsOrderIdSaleOrderIdMap.entrySet()) {
            EbsOrderLogModelBuilder ebsOrderLogBuilder = EbsOrderLogModelBuilder.anEbsOrderLogModel();

            ebsOrderLogBuilder.orderId(entry.getKey()).saleOrderId(entry.getValue()).logType(logType)
                    .sendTime(sendTime).msgJson(msgJson).errorMsg(errorMsg)
                    .failCount(failCount).operationResult(operationResult);

            ebsOrderLogList.add(ebsOrderLogBuilder.build());
        }

        save(ebsOrderLogList);

        return true;
    }

    @Override
    public EbsOrderLogModel queryEbsOrderLogLatestLogByOrderIdAndLogType(EbsOrderLogModel ebsOrderLogSearch) {
        if (null == ebsOrderLogSearch) {
            return null;
        }

        return ebsOrderLogDao.queryEbsOrderLogLatestLogByOrderIdAndLogType(ebsOrderLogSearch);
    }


    /**
     * 查询发送失败的MQ日志
     * @return
     */
    public List<EbsOrderLogModel> queryFailSendMqLog(){
       return   ebsOrderLogDao.queryFailSendMqLog();
    }
}
