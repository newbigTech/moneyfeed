package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.common;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.order.IntegrtionMoneyFeedToEbsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 重发MQ信息
 * Created by Dave Chen on 2018/12/17.
 */
@EScheduler(jobName = "ReSendIntergrationMQTask", shardingTotalCount = 1, cron = "0 */2 * * * ?", overwrite = true)
@Component
public class ReSendIntergrationMQTask extends AbstractSimpleElasticJob {

    @Autowired
    private IntegrtionMoneyFeedToEbsClient client;

    private final static Logger logger = LoggerFactory.getLogger(ReSendIntergrationMQTask.class);


    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        logger.info("ReSendIntergrationMQTask 开始");
        client.resendMQ();
        logger.info("ReSendIntergrationMQTask 结束");
    }
}
