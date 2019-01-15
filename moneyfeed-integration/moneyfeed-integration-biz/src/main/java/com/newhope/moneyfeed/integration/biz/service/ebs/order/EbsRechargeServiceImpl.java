package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsRechargeModel;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsRechargeService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsRechargeDao;

/**
 * Created by yuyanlin on 2018/12/17
 */
@Service
public class EbsRechargeServiceImpl extends BaseService<EbsRechargeModel> implements EbsRechargeService {

    @Autowired
    private EbsRechargeDao ebsOrderRechargeDao;

    @Override
    protected BaseDao<EbsRechargeModel> getDao() {
        return ebsOrderRechargeDao;
    }
    
    public List<EbsRechargeModel> queryEbsRechargeModelByMoneyfeedPayNo(String moneyfeedPayNo){
    	EbsRechargeModel selectEbsRechargeModel = new EbsRechargeModel();
    	selectEbsRechargeModel.setMoneyfeedPayNo(moneyfeedPayNo);
    	List<EbsRechargeModel> ebsRechargeModelList = ebsOrderRechargeDao.select(selectEbsRechargeModel);
    	if(CollectionUtils.isEmpty(ebsRechargeModelList)){
        	return null;
        }
    	return ebsRechargeModelList;
    }
    
    
    @Override
    public List<EbsRechargeModel> getPayingEbsRechargeList() {
    	EbsRechargeModel ebsRechargeModel = new EbsRechargeModel();
    	ebsRechargeModel.setPayStatus(MoneyfeedToEbsOperationStatusEnum.OPERATING.name());
        
        return ebsOrderRechargeDao.select(ebsRechargeModel);
    }
}
