package com.newhope.moneyfeed.integration.biz.thread;

import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.service.SyncEBSDataService;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.MoneyfeedToEbsOrderServiceImpl;

import java.util.concurrent.Callable;

/**
 * Created by Dave Chen on 2018/11/27.
 */
public class UpdateOrderDetailThread  implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        final MoneyfeedToEbsOrderServiceImpl syncEBSDataService = AppContext.getSpringContext().getBean(MoneyfeedToEbsOrderServiceImpl.class);
        syncEBSDataService.queryEbsOrderDetailInfoJobThread();
        return  true;
    }
}
