package com.newhope.moneyfeed.goods.api.rest;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.CustomerMaterielRelationDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/29 15:16
 */
@Api(value = "组织用户折扣(政策)数据", description = "组织用户折扣(政策)数据REST API", protocols = "http")
public interface CustomerMaterielRelationAPI {

    @ApiOperation(value = "同步组织用户折扣(政策)数据", notes = "同步组织用户折扣(政策)数据", protocols = "http")
    @RequestMapping(value = "/pc/customer/materiel/relation/init", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> syncCustomerMaterielRelation(@RequestBody List<CustomerMaterielRelationDtoReq> dtoReqList);


    @ApiOperation(value = "绑定客户和商品关系", notes = "绑定客户和商品关系", protocols = "http")
    @RequestMapping(value = "/pc/customer/materiel/builder", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> builderCustomerMateriel(@RequestBody List<CustomerMaterielRelationDtoReq> dtoReqList);

}
