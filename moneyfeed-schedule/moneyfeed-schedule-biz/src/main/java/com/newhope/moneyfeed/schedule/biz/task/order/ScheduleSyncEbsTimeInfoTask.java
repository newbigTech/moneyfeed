package com.newhope.moneyfeed.schedule.biz.task.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.schedule.biz.rpc.order.OrderIntegrationFeignClient;

/**  
* @ClassName: ScheduleSyncEbsTimeInfoTask  
* @Description: 每五分钟拉取中台的EBS数据更新订单
* @author luoxl
* @date 2018年11月22日 下午8:33:01  
*    
*/
@EScheduler(jobName = "ScheduleSyncEbsTimeInfoTask", shardingTotalCount = 1, cron = "0 */2 * * * ?", overwrite = true)
@Component
public class ScheduleSyncEbsTimeInfoTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(ScheduleSyncEbsTimeInfoTask.class);

    @Autowired
    private OrderIntegrationFeignClient orderIntegrationFeignClient;
    
    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
    	logger.info("ScheduleSyncEbsTimeInfoTask.pullOrderInfo开始");
    	orderIntegrationFeignClient.pullOrderInfo();
    	logger.info("ScheduleSyncEbsTimeInfoTask.pullOrderInfo结束");
    }
}
