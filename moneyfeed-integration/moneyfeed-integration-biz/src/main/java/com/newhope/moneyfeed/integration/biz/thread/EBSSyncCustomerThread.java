package com.newhope.moneyfeed.integration.biz.thread;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.EBSCompanyRespListResult;
import com.newhope.moneyfeed.integration.api.service.SyncEBSDataService;
import com.newhope.moneyfeed.integration.api.service.SyncEBScompanyService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CustomerReq;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by liming on 2018/7/13.
 */
public class EBSSyncCustomerThread implements Callable<Boolean> {

    private final static Logger logger= LoggerFactory.getLogger(EBSSyncCustomerThread.class);


    public EBSSyncCustomerThread() {

    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Boolean call() throws Exception {

        final SyncEBSDataService syncEBSDataService = AppContext.getSpringContext().getBean(SyncEBSDataService.class);

        final SyncEBScompanyService ebsCompanyFacade = AppContext.getSpringContext().getBean(SyncEBScompanyService.class);

        final CacheData cacheData = AppContext.getSpringContext().getBean(CacheData.class);

        EbsCompanyModel companyModel = new EbsCompanyModel();
        
        final EBSCompanyRespListResult syncCompanyList = ebsCompanyFacade.selectComapny(companyModel);

        if(!ResultCode.SUCCESS.getCode().equals(syncCompanyList.getCode())){
            logger.error("查询需要同步的ebs组织报错",syncCompanyList.getMsg());
            return Boolean.TRUE;
        }
        if(CollectionUtils.isEmpty(syncCompanyList.getDataList())){
            logger.info("-------未发现需要同步的EBS组织-----------------");
            return Boolean.TRUE;
        }
        String courrentTime= DateUtil.getDateStr(new Date(),DateUtil.YYYYMMDDHHMMSS);
        for(EbsCompanyModel ebsCompanyModel:syncCompanyList.getDataList()){
            String beginTime=(String)cacheData.getData("get_customer_time:"+ebsCompanyModel.getShortCode());
            if(StringUtils.isEmpty(beginTime)){
                beginTime="19000101000000";
            }
            
        	//beginTime="19000101000000";
        	
            CustomerReq customerReq=new CustomerReq();
            customerReq.setCompanyCode(ebsCompanyModel.getShortCode());
            customerReq.setBeginTime(beginTime);
            customerReq.setEndTime(courrentTime);
            logger.info("-----------sync customer,param:  companyCode:[{}],warehouse:[{}],beginTime:[{}],endTime:[{}]-----------------", customerReq.getCompanyCode(), customerReq.getBeginTime(), customerReq.getEndTime());
            final DtoResult result = syncEBSDataService.syncEBSCustomerCheckParam(customerReq);
            if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
                cacheData.setData("get_customer_time:"+customerReq.getCompanyCode(), customerReq.getEndTime());
            }
            logger.info("同步客户结束,公司："+ebsCompanyModel.getShortCode()+"----"+result.getCode()+"-----"+result.getMsg());
        }
        return Boolean.TRUE;
    }
}