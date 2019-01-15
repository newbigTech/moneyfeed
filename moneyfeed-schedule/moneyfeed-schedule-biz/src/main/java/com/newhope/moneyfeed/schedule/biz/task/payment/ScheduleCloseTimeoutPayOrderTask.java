package com.newhope.moneyfeed.schedule.biz.task.payment;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.newhope.moneyfeed.schedule.biz.rpc.payment.PaymentScheduleFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 关闭支付超时订单
 * @author hp
 *
 */

//@EScheduler(jobName = "ScheduleCloseTimeoutPayOrderTask", shardingTotalCount = 1, cron = "0 */5 * * * ?", overwrite = true)
@Component
public class ScheduleCloseTimeoutPayOrderTask extends AbstractSimpleElasticJob {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleCloseTimeoutPayOrderTask.class);

    @Autowired
    PaymentScheduleFeignClient paymentScheduleFeignClient;

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        logger.info("------ ScheduleCloseTimeoutPayOrderTask starting ------");
        paymentScheduleFeignClient.closeTimeoutPayOrder();
        logger.info("------ ScheduleCloseTimeoutPayOrderTask ending ------");
    }
}
