package com.newhope.moneyfeed.pay.api.rest.pay;

import java.io.Writer;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCustomerDtoReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "lxl测试API", description = "lxl测试API", protocols = "http")
public interface LxlTestPayAPI {

//    @ApiOperation(value = "lxl测试", notes = "lxl测试", protocols = "http")
//    @RequestMapping(value = "/lxl/test", method = RequestMethod.POST)
//    public void test1(Writer writer, @ModelAttribute("form") PayOrderCustomerDtoReq reqBody);
    
    
    @ApiOperation(value = "lxl测试", notes = "lxl测试", protocols = "http")
    @RequestMapping(value = "/lxl/test", method = RequestMethod.POST)
    public void test1();

}