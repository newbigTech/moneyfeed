package com.newhope.moneyfeed.integration.web.controller;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.mq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liming on 2018/12/18.
 */
@RestController
public class TestMqController extends AbstractController {


    @Autowired
    private MessageService messageService;

    @RequestMapping("/test/mq")
    public String testMq(){

        messageService.sendMessageFromIntToOrder("test mq");

        return "";
    }



}