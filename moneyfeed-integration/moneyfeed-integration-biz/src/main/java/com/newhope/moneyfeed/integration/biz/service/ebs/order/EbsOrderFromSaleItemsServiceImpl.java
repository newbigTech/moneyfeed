package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleItemsModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderFromSaleItemsService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderFromSaleItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuyanlin on 2018/11/22
 */
@Service
public class EbsOrderFromSaleItemsServiceImpl extends BaseService<EbsOrderFromSaleItemsModel> implements EbsOrderFromSaleItemsService {

    @Autowired
    private EbsOrderFromSaleItemsDao ebsOrderFromSaleItemsDao;

    @Override
    protected BaseDao<EbsOrderFromSaleItemsModel> getDao() {
        return ebsOrderFromSaleItemsDao;
    }
}
