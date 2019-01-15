package com.newhope.moneyfeed.integration.api.service.ebs.baseData;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;

/**
 * ebs company 表相关服务
 *
 * Created by yuyanlin on 2018/11/24
 */
public interface EbsCompanyService {

    EbsCompanyModel queryEbsCompanyByOrgId(String orgId);

}
