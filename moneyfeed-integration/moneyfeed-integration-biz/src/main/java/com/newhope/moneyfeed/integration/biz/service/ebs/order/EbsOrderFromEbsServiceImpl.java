package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderFromEbsService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderFromEbsDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuyanlin on 2018/11/22
 */
@Service
public class EbsOrderFromEbsServiceImpl extends BaseService<EbsOrderFromEbsModel> implements EbsOrderFromEbsService {

    @Autowired
    private EbsOrderFromEbsDao ebsOrderFromEbsDao;

    @Override
    protected BaseDao<EbsOrderFromEbsModel> getDao() {
        return ebsOrderFromEbsDao;
    }

    @Override
    public EbsOrderFromEbsModel getEbsOrderFromEbsByEbsOrderId(Long ebsOrderId) {
        EbsOrderFromEbsModel ebsOrderFromEbsSearch = new EbsOrderFromEbsModel();
        ebsOrderFromEbsSearch.setOrderId(ebsOrderId);

        List<EbsOrderFromEbsModel> ebsOrderFromEbsList = ebsOrderFromEbsDao.select(ebsOrderFromEbsSearch);

        if (CollectionUtils.isEmpty(ebsOrderFromEbsList)) {
            return null;
        }

        return ebsOrderFromEbsList.get(0);
    }
}
