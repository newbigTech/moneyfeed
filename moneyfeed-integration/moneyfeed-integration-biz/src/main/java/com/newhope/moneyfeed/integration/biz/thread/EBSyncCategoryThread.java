package com.newhope.moneyfeed.integration.biz.thread;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.service.SyncEBSDataService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CategoryReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by liming on 2018/7/13.
 */
public class EBSyncCategoryThread implements Callable<Boolean> {

    private final static Logger logger= LoggerFactory.getLogger(EBSyncCategoryThread.class);
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */

    private CategoryReq categoryReq;

    public EBSyncCategoryThread(CategoryReq categoryReq) {
        this.categoryReq = categoryReq;
    }

    @Override
    public Boolean call() throws Exception {

        final SyncEBSDataService syncEBSDataService = AppContext.getSpringContext().getBean(SyncEBSDataService.class);

        //final CacheData cacheData = AppContext.getSpringContext().getBean(CacheData.class);

        logger.info("-----------sync category,param:  beginTime:[{}],endTime:[{}]-----------------",categoryReq.getBeginTime(),categoryReq.getEndTime());

        final Result result = syncEBSDataService.syncEbsCategoryCheckParam(categoryReq);

        logger.info("物料类别同步结束----"+result.getMsg()+"-----"+result.getCode());
        /*if(ResultCode.SUCCESS.getCode().equals(result.getCode())){
            cacheData.setData("get_category_time",categoryReq.getEndTime());
        }*/

        return Boolean.TRUE;
    }
}