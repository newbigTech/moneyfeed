package com.newhope.pay.biz.service.impl;

import com.newhope.moneyfeed.pay.api.bean.PayBillDataExceptionModel;
import com.newhope.moneyfeed.pay.dal.BaseDao;
import com.newhope.moneyfeed.pay.dal.dao.PayBillDataExceptionDao;
import com.newhope.pay.biz.service.PayBillDataExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bena.peng
 * @date 2018/12/20 10:43
 */

@Service
public class PayBillDataExceptionServiceImpl extends BaseServiceImpl<PayBillDataExceptionModel> implements PayBillDataExceptionService {

    @Autowired
    PayBillDataExceptionDao payBillDataExceptionDao;

    @Override
    protected BaseDao<PayBillDataExceptionModel> getDao() {
        return payBillDataExceptionDao;
    }
}
