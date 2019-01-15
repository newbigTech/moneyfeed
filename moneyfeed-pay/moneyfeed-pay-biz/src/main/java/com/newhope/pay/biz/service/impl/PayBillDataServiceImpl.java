package com.newhope.pay.biz.service.impl;

import com.newhope.moneyfeed.pay.api.bean.PayBillDataModel;
import com.newhope.moneyfeed.pay.dal.BaseDao;
import com.newhope.moneyfeed.pay.dal.dao.PayBillDataDao;
import com.newhope.pay.biz.service.PayBillDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bena.peng
 * @date 2018/12/20 10:39
 */

@Service
public class PayBillDataServiceImpl extends BaseServiceImpl<PayBillDataModel> implements PayBillDataService {
    @Autowired
    PayBillDataDao payBillDataDao;

    @Override
    protected BaseDao<PayBillDataModel> getDao() {
        return payBillDataDao;
    }

}
