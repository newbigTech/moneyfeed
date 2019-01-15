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
 * 每天七点发送所库存失败提醒
 * 
 */
@EScheduler(jobName = "SchedulePayRemindPreparingMaterialTask", shardingTotalCount = 1, cron = "0 0 7 * * ?", overwrite = true)
@Component
public class SchedulePayRemindPreparingMaterialTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(SchedulePayRemindPreparingMaterialTask.class);

    @Autowired
    private OrderScheduleFeignClient orderScheduleFeignClient;
    
    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
    	logger.info("SchedulePayRemindPreparingMaterialTask 开始");
    	orderScheduleFeignClient.remindPreparingMaterial();
    	logger.info("SchedulePayRemindPreparingMaterialTask 结束");
    }
}
