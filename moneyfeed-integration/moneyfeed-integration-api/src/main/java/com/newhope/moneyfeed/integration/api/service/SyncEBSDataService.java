package com.newhope.moneyfeed.integration.api.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CategoryReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CustomerReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.MaterialReq;

/**
 * Created by liming on 2018/11/20.
 */
public interface SyncEBSDataService {

    /**
     * 同步ebs品类信息包含验证参数
     * @param req
     * @return
     */
    Result syncEbsCategoryCheckParam(CategoryReq req);

    /**
     * 同步ebs物料信息包含验证参数
     * @param
     * @return
     */
    Result syncEBSMaterialCheckParam(MaterialReq materialReq);

    /**
     * 同步ebs客户信息包含验证参数
     * @param
     * @return
     */
    DtoResult syncEBSCustomerCheckParam(CustomerReq customerReq);

}