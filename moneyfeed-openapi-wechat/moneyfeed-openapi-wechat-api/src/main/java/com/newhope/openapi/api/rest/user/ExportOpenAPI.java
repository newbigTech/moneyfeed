package com.newhope.openapi.api.rest.user;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Api(description = "导出接口", tags = "ExportOpenAPI", protocols = "http")
public interface ExportOpenAPI {

    @ApiOperation(value = "下载pdf电子对账单", notes = "下载pdf电子对账单")
    @RequestMapping(value = "/bill/pdf", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", paramType = "query", required = true, value = "年", dataType = "int"),
            @ApiImplicitParam(name = "month", paramType = "query", required = true, value = "月", dataType = "int"),
            @ApiImplicitParam(name = "customerNo", paramType = "query", value = "客户号", dataType = "String"),
            @ApiImplicitParam(name = "orgId", paramType = "query", value = "公司id", dataType = "String")
    })
    ResponseEntity<byte[]> download(HttpServletRequest request,
                                    @RequestParam Integer year,
                                    @RequestParam Integer month,
                                    @RequestParam(name = "orgId", required = false) String shopId,
                                    @RequestParam(name = "customerNo", required = false) String customerNo
    );

    @ApiOperation(value = "查看pdf电子对账单", notes = "查看pdf电子对账单")
    @RequestMapping(value = "/bill/pdf/preview", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", paramType = "query", required = true, value = "年", dataType = "int"),
            @ApiImplicitParam(name = "month", paramType = "query", required = true, value = "月", dataType = "int"),
            @ApiImplicitParam(name = "customerNo", paramType = "query", value = "客户号", dataType = "String"),
            @ApiImplicitParam(name = "orgId", paramType = "query", value = "公司id", dataType = "String")
    })
    ResponseEntity<byte[]> preview(HttpServletRequest request,
                                   @RequestParam Integer year,
                                   @RequestParam Integer month,
                                   @RequestParam(name = "orgId", required = false) String orgId,
                                   @RequestParam(name = "customerNo", required = false) String customerNo);
}
