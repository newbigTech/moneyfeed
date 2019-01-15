package com.newhope.moneyfeed.feedback.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.common.cache.CacheData;

/**
 * Created by liming on 2018/11/15.
 */
@RequestMapping("test")
@RestController
public class TestController extends AbstractController{




    @Autowired
    private CacheData cacheData;


    @RequestMapping("hello")
    public String testHelloWorld(){
        try {
            cacheData.getDataCache().set("test123","test12312");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello world";

    }


}