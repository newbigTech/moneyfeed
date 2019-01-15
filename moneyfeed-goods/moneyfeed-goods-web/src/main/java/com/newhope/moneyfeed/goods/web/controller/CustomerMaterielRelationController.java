package com.newhope.moneyfeed.goods.web.controller;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.CustomerMaterielRelationDtoReq;
import com.newhope.moneyfeed.goods.api.rest.CustomerMaterielRelationAPI;
import com.newhope.goods.biz.service.CustomerMaterielRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/29 15:22
 */
@RestController
public class CustomerMaterielRelationController extends AbstractController implements CustomerMaterielRelationAPI {

    @Autowired
    private CustomerMaterielRelationService customerMaterielRelationService;


    @Override
    public BaseResponse<DtoResult> syncCustomerMaterielRelation(@RequestBody List<CustomerMaterielRelationDtoReq> dtoReqList) {
        DtoResult result = customerMaterielRelationService.syncCustomerMaterielRelation(dtoReqList);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> builderCustomerMateriel(@RequestBody List<CustomerMaterielRelationDtoReq> dtoReqList) {


        return null;
    }
}
