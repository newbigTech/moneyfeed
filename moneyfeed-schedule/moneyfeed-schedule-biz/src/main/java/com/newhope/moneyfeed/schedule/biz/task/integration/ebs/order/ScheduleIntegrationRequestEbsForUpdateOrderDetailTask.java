package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.order;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.order.IntegrtionMoneyFeedToEbsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dave Chen on 2018/11/27.
 */
@EScheduler(jobName = "ScheduleIntegrationRequestEbsForUpdateOrderDetailTask", shardingTotalCount = 1, cron = "0 */1 * * * ?", overwrite = true)
@Component
public class ScheduleIntegrationRequestEbsForUpdateOrderDetailTask extends AbstractSimpleElasticJob {

    @Autowired
    private IntegrtionMoneyFeedToEbsClient client;

    private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationRequestEbsForUpdateOrderDetailTask.class);

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {

        logger.info("ScheduleIntegrationRequestEbsForUpdateOrderDetailTask 开始");
        client.updateOrderDetailFromEbs();
        logger.info("ScheduleIntegrationRequestEbsForUpdateOrderDetailTask 结束");
    }
}
