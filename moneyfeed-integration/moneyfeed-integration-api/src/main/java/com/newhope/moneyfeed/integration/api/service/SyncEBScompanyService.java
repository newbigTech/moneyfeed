package com.newhope.moneyfeed.integration.api.service;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.EBSCompanyRespListResult;

/**
 * Created by liming on 2018/11/20.
 */
public interface SyncEBScompanyService {

    /**
     * 查询需要同步的所有公司
     * @return
     */
    EBSCompanyRespListResult selectComapny(EbsCompanyModel companyModel);

}