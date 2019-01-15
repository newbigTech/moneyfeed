package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.goods.api.dto.request.CustomerMaterielRelationDtoReq;

import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/29 15:24
 */
public interface CustomerMaterielRelationService {
    /**
     * 同步组织用户折扣(政策)数据
     *
     * @param dtoReqList
     * @return
     */
    DtoResult syncCustomerMaterielRelation(List<CustomerMaterielRelationDtoReq> dtoReqList);
}
