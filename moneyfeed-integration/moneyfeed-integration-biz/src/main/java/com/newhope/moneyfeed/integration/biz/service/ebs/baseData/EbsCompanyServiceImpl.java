package com.newhope.moneyfeed.integration.biz.service.ebs.baseData;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.service.ebs.baseData.EbsCompanyService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.baseData.EbsCompanyDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuyanlin on 2018/11/24
 */
@Service
public class EbsCompanyServiceImpl extends BaseService<EbsCompanyModel> implements EbsCompanyService {

    @Autowired
    private EbsCompanyDao ebsCompanyDao;

    @Override
    protected BaseDao<EbsCompanyModel> getDao() {
        return ebsCompanyDao;
    }

    @Override
    public EbsCompanyModel queryEbsCompanyByOrgId(String orgId) {
        if (StringUtils.isEmpty(orgId)) {
            return null;
        }

        EbsCompanyModel ebsCompanySearch = new EbsCompanyModel();
        ebsCompanySearch.setCompanyId(orgId);

        List<EbsCompanyModel> ebsCompanyList = ebsCompanyDao.select(ebsCompanySearch);

        if (CollectionUtils.isEmpty(ebsCompanyList)) {
            return null;
        }

        return ebsCompanyList.get(0);
    }
    
}
