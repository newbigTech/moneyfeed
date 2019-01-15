package com.newhope.openapi.api.rest.user;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.user.CustomerCarReq;
import com.newhope.openapi.api.vo.response.user.CustomerCarListResult;
import com.newhope.openapi.api.vo.response.user.CustomerCarResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(description = "车辆管理", tags = "CustomerCarManageOpenAPI", protocols = "http")
@RequestMapping("/user/car")
public interface CustomerCarManageOpenAPI {
    @ApiOperation(value = "车辆管理-创建或修改车辆信息", notes = "车辆管理-创建或修改车辆信息")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "CustomerCarReq")
    })
    BaseResponse<Result> saveOrUpdate(@Valid @RequestBody CustomerCarReq request);

    @ApiOperation(value = "车辆管理-查询车辆信息", notes = "车辆管理-查询车辆信息")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = true, value = "当前?页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "每页多少条", paramType = "query", dataType = "int")
    })
    BaseResponse<CustomerCarListResult> find(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);

    @ApiOperation(value = "车辆管理-查询车辆信息详情", notes = "车辆管理-查询车辆信息详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "path", dataType = "Long")
    })
    BaseResponse<CustomerCarResult> detail(@PathVariable("id") Long id);

    @ApiOperation(value = "车辆管理-查询默认车辆信息", notes = "车辆管理-查询默认车辆信息")
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    BaseResponse<CustomerCarResult> defaultCar();
    
    @ApiOperation(value = "车辆管理-删除车辆信息", notes = "车辆管理-删除车辆信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "path", dataType = "Long")
    })
    BaseResponse<Result> remove(@PathVariable("id") Long id);
}