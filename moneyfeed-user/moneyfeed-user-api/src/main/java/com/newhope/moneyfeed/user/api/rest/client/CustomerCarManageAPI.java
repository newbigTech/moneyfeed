package com.newhope.moneyfeed.user.api.rest.client;


import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Api(description = "车辆管理", tags = "CustomerCarManageAPI", protocols = "http")
@RequestMapping("/uc/user/car")
public interface CustomerCarManageAPI {
    @ApiOperation(value = "车辆管理-创建或修改车辆信息", notes = "车辆管理-创建或修改车辆信息")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "CustomerCarDtoReq")
    })
    BaseResponse<DtoResult> saveOrUpdate(@RequestBody CustomerCarDtoReq request);

    @ApiOperation(value = "车辆管理-查询车辆信息分页", notes = "车辆管理-查询车辆信息分页")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "CustomerCarQueryDtoReq")
    })
    BaseResponse<CustomerCarListDtoResult> find(@RequestBody CustomerCarQueryDtoReq request);

    @ApiOperation(value = "车辆管理-查询默认车辆信息", notes = "车辆管理-查询默认车辆信息")
    @RequestMapping(value = "/default", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "CustomerCarQueryDtoReq")
    })
    BaseResponse<CustomerCarDtoResult> defaultCar(@RequestBody CustomerCarQueryDtoReq request);
    
    @ApiOperation(value = "车辆管理-删除车辆信息", notes = "车辆管理-删除车辆信息")
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "CustomerCarDtoReq")
    })
    BaseResponse<DtoResult> remove(@RequestBody CustomerCarDtoReq request);

}