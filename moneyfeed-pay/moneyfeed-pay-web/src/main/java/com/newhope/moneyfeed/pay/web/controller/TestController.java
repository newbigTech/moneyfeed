package com.newhope.moneyfeed.pay.web.controller;

import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.util.ExcelImportUitls;
import com.newhope.moneyfeed.pay.api.rest.ExcelDemo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by liming on 2018/11/15.
 */
@RequestMapping("test")
@RestController
public class TestController extends AbstractController{



    @RequestMapping(value = "/import", method = RequestMethod.POST, consumes = { "multipart/form-data" }, produces = {
            "application/json" })
    public String excelImport(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception {

        int index = file.getOriginalFilename().lastIndexOf(".");
        if (index == -1) {
            throw new BizException("上传文件的扩展名为空，可能不是excel格式的文件，系统拒绝处理");
        }
        String suffix = file.getOriginalFilename().substring(index + 1);

        final List<ExcelDemo> excelDemos = ExcelImportUitls.excelImportConvert(file.getInputStream(), ExcelDemo.class, suffix, 3);

        return "";
    }


    @RequestMapping("hello")
    public String testHelloWorld(){

        return "hello world";

    }


}