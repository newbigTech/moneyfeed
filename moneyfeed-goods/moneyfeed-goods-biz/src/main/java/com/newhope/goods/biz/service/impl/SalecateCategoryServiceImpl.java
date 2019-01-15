package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.SalecateCategoryService;
import com.newhope.moneyfeed.goods.api.bean.SalecateCategoryModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.SalecateCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/22 10:29
 */
@Service
public class SalecateCategoryServiceImpl extends BaseService<SalecateCategoryModel> implements SalecateCategoryService {

    @Autowired
    private SalecateCategoryDao salecateCategoryDao;

    @Override
    protected BaseDao<SalecateCategoryModel> getDao() {
        return salecateCategoryDao;
    }
}

