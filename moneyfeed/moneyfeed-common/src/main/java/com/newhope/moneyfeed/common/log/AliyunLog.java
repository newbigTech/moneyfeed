package com.newhope.moneyfeed.common.log;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.Level;

/**
 * Created by liming on 2019/1/4.
 */
public class AliyunLog {

    private final static   Level ALIYUN = Level.forName("ALIYUN", 10);
    private final static  org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger();

    public static void log(Object obj){
        log.log(ALIYUN,JSONObject.toJSONString(obj));
    }
}