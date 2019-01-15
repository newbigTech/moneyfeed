package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.CompanyService;
import com.newhope.moneyfeed.goods.dal.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/17 16:59
 */
@Service
public class CompanyServiceImpl  implements CompanyService {

    @Autowired
    private CompanyDao companyDao;


}
