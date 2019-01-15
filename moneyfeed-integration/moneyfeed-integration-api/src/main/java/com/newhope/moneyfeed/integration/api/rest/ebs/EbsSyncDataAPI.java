package com.newhope.moneyfeed.integration.api.rest.ebs;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liming on 2018/7/9.
 * 同步EBS数据
 */
public interface EbsSyncDataAPI {


    @ApiOperation(value = "同步物料分类（新增或修改）", notes = "同步物料分类（新增或修改）", protocols = "http")
    @RequestMapping(value = "/third/ebs/category", method = RequestMethod.POST, consumes = {
            "application/json" }, produces = { "application/json" })
    BaseResponse<DtoResult> syncEBSCategory();

    @ApiOperation(value = "同步物料（新增或修改）", notes = "同步物料（新增或修改）", protocols = "http")
    @RequestMapping(value = "/third/ebs/material", method = RequestMethod.POST, consumes = {
            "application/json" }, produces = { "application/json" })
    BaseResponse<DtoResult> syncEBSMaterial(@RequestBody String orgId);

    @ApiOperation(value = "同步EBS客户（新增或修改）", notes = "同步EBS客户（新增或修改）", protocols = "http")
    @RequestMapping(value = "/third/ebs/customer", method = RequestMethod.POST, consumes = {
            "application/json" }, produces = { "application/json" })
    BaseResponse<DtoResult> syncEBSCustomer();

}