package com.newhope.moneyfeed.order.api.rest;

import java.io.Writer;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "lxl测试API", description = "lxl测试API", protocols = "http")
public interface LxlTestOrderAPI {

    @ApiOperation(value = "lxl测试", notes = "lxl测试", protocols = "http")
    @RequestMapping(value = "/lxl/test", method = RequestMethod.GET)
    public DtoResult test1();

}