package com.newhope.moneyfeed.schedule.biz.task.payment;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.schedule.biz.rpc.payment.PaymentScheduleFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 与第三方进行对账
 * 每日 2：00 对账
 *              T-2 22：00 至 T-1 22：00 的账单
 * @author bena.peng
 * @date 2018/12/19 13:52
 */

//@EScheduler(jobName = "ScheduleSyncThirdPaymentBill", shardingTotalCount = 1, cron = "0 0 2 * * ?", overwrite = true)
@Component
public class ScheduleSyncThirdPaymentBillTask extends AbstractSimpleElasticJob {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleSyncThirdPaymentBillTask.class);

    @Autowired
    PaymentScheduleFeignClient paymentScheduleFeignClient;

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        logger.info("------ sync bill starting ------");
        paymentScheduleFeignClient.syncThirdPaymentBill();
        logger.info("------ sync bill ending ------");
    }
}
