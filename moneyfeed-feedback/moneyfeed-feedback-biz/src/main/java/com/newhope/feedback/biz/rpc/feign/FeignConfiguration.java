package com.newhope.feedback.biz.rpc.feign;

import feign.Request;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义feign配置，延长超时时间，永不重试
 *
 * @author: zhangyanyan
 * @date: 2018/4/27
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Request.Options feignOptions() {
        //timeout时间设置为30秒
        return new Request.Options(30000, 30000);
    }

    @Bean
    Retryer feignRetryer() {
        /**
         * Implementation that never retries request. It propagates the RetryableException.
         */
        return new Retryer() {
            @Override
            public void continueOrPropagate(RetryableException e) {
                throw e;
            }

            @Override
            public Retryer clone() {
                return this;
            }
        };
    }
}
