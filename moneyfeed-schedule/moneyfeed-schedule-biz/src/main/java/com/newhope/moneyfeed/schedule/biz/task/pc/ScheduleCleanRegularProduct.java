package com.newhope.moneyfeed.schedule.biz.task.pc;


import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.schedule.biz.rpc.pc.RegularProductFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EScheduler(jobName = "ScheduleCleanRegularProduct", shardingTotalCount = 1, cron = "0 0 2 * * ?", overwrite = true)
@Component
public class ScheduleCleanRegularProduct extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(ScheduleSyncProductTask.class);

    @Autowired
    private RegularProductFeignClient feignClient;


    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        logger.info("------ ScheduleCleanRegularProduct starting ------");

        BaseResponse<DtoResult> resultBaseResponse = feignClient.cleanRegularProduct();

        logger.info("------ ScheduleCleanRegularProduct ending ------");

    }
}
