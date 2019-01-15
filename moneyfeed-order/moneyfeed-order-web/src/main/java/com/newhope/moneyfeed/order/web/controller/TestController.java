package com.newhope.moneyfeed.order.web.controller;

import com.newhope.moneyfeed.common.log.AliyunLog;
import com.newhope.moneyfeed.mq.service.MessageService;
import com.newhope.order.biz.hepler.NumberGeneraterHelper;


import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liming on 2018/11/26.
 */
@RestController
public class TestController {

	private Logger loggger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    NumberGeneraterHelper numberGeneraterHelper;

    @RequestMapping("test/mq")
    public void testMq(){
        messageService.sendUserCheckEventsMessage("testData");
    }
    
    @RequestMapping("/test/orderno")
    public void testOrderNo(){
    	for(int a = 0 ;a <= 152 ;a++){
    		new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.err.println("order-->"+numberGeneraterHelper.genOrderNo());;
				}
			}).start();
    	}
    }
    
    @RequestMapping("/test/payno")
    public void testPayNo(){
    	for(int a = 0 ;a <= 152 ;a++){
    		new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.err.println("pay-->"+numberGeneraterHelper.genPayNo());
				}
			}).start();
    	}
    }

	@RequestMapping("/test/aliyun")
    public void testAliLog(){
		AliyunLog.log("asdasdas");
		loggger.info("hello");
	}


}