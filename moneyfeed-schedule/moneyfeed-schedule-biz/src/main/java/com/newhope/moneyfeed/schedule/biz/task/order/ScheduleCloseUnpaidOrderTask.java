package com.newhope.moneyfeed.schedule.biz.task.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.schedule.biz.rpc.order.OrderScheduleFeignClient;

/**
 * 关闭未支付订单
 */
@EScheduler(jobName = "ScheduleCloseUnpaidOrderTask", shardingTotalCount = 1, cron = "0 */1 * * * ?", overwrite = true)
@Component
public class ScheduleCloseUnpaidOrderTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(ScheduleCloseUnpaidOrderTask.class);

    @Autowired
    private OrderScheduleFeignClient orderScheduleFeignClient;
    
    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
    	logger.info("ScheduleCloseUnpaidOrderTask 开始");
    	orderScheduleFeignClient.closeUnPaidOrder();
    	logger.info("ScheduleCloseUnpaidOrderTask 结束");
    }
}
