package com.newhope.openapi.web.controller;

import com.newhope.moneyfeed.common.util.EnumUtil;
import com.newhope.moneyfeed.common.vo.EnumConstResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liming on 2018/12/25.
 */
@RestController
public class TestController {

    public static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test/enum")
    public void testEnum(){

        try {
            List<EnumConstResult> orderStatusEnum = EnumUtil.getEnumByName("com.newhope.moneyfeed.order.api.enums","OrderStatusEnum");
            logger.info(""+orderStatusEnum.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}