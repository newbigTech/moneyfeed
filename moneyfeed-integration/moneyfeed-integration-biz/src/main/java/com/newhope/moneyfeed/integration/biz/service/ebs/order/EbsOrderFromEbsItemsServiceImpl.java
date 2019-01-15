package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsItemsModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderFromEbsItemsService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderFromEbsItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuyanlin on 2018/11/22
 */
@Service
public class EbsOrderFromEbsItemsServiceImpl extends BaseService<EbsOrderFromEbsItemsModel> implements EbsOrderFromEbsItemsService {

    @Autowired
    private EbsOrderFromEbsItemsDao ebsOrderFromEbsItemsDao;

    @Override
    protected BaseDao<EbsOrderFromEbsItemsModel> getDao() {
        return ebsOrderFromEbsItemsDao;
    }
}
