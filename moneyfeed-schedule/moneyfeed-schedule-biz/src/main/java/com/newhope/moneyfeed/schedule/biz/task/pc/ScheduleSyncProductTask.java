package com.newhope.moneyfeed.schedule.biz.task.pc;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.SkuInitReq;
import com.newhope.moneyfeed.schedule.biz.rpc.pc.ProductFeignClient;
import com.newhope.moneyfeed.schedule.biz.rpc.pc.SkuFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : tom
 * @project: moneyfeed-schedule
 * @date : 2018/11/21 10:13
 */
@EScheduler(jobName = "SyncProduct", shardingTotalCount = 1, cron = "0 /30 * * * ? *", overwrite = true)
@Component
public class ScheduleSyncProductTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(ScheduleSyncProductTask.class);

    @Autowired
    private ProductFeignClient productFeignClient;
    
    @Autowired
    private SkuFeignClient skuFeignClient;

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {

         BaseResponse<DtoResult> resultBaseResponse = productFeignClient.syncProduct();

        if(!ResultCode.SUCCESS.name().equals(resultBaseResponse.getCode())){
            logger.error("同步商品错误",resultBaseResponse.getMsg());
        }else {
        	SkuInitReq req=new SkuInitReq();
        	BaseResponse skuResult=skuFeignClient.skuInit(req);
        	if(!skuResult.isSuccess()) {
        		logger.error("初始化商品sku错误",resultBaseResponse.getMsg());
        	}
        }
    }
}
