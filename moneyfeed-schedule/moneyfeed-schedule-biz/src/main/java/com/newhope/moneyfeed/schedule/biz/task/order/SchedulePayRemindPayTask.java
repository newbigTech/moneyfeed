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
 * 提醒支付消息task
 * 根据订单状态
 * 现在设置成1分钟，如果修改，需要修改相应的业务代码
 * 后期可改成延迟任务
 */
@EScheduler(jobName = "SchedulePayRemindPayTask", shardingTotalCount = 1, cron = "0 */1 * * * ?", overwrite = true)
@Component
public class SchedulePayRemindPayTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(SchedulePayRemindPayTask.class);

    @Autowired
    private OrderScheduleFeignClient orderScheduleFeignClient;
    
    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
    	logger.info("SchedulePayRemindPayTask 开始");
    	orderScheduleFeignClient.remindPay();
    	logger.info("SchedulePayRemindPayTask 结束");
    }
}
