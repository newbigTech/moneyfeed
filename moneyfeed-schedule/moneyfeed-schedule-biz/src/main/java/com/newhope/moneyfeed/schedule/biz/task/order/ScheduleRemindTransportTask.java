package com.newhope.moneyfeed.schedule.biz.task.order;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.schedule.biz.rpc.order.OrderScheduleFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by yyq on 2018/11/29
 * 待拉料订单，在拉料前一天的18：00，提醒拉料
 */
@EScheduler(jobName = "ScheduleRemindTransportTask", shardingTotalCount = 1, cron = "0 0 18 * * ?", overwrite = true)
@Component
public class ScheduleRemindTransportTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(ScheduleRemindTransportTask.class);

    @Autowired
    private OrderScheduleFeignClient orderScheduleFeignClient;

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        logger.info("ScheduleRemindTransportTask 开始");
        orderScheduleFeignClient.remindTransport();
        logger.info("ScheduleRemindTransportTask 结束");
    }
}