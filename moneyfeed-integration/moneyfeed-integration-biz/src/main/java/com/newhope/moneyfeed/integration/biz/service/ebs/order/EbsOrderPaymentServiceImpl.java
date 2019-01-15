package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderPaymentModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderPaymentService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderPaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuyanlin on 2018/12/17
 */
@Service
public class EbsOrderPaymentServiceImpl extends BaseService<EbsOrderPaymentModel> implements EbsOrderPaymentService {

    @Autowired
    private EbsOrderPaymentDao ebsOrderPaymentDao;

    @Override
    protected BaseDao<EbsOrderPaymentModel> getDao() {
        return ebsOrderPaymentDao;
    }
}
