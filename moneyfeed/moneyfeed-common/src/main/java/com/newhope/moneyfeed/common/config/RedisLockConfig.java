package com.newhope.moneyfeed.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by liming on 2018/8/3.
 */
@Configuration
public class RedisLockConfig {

    @Bean
    RedisMessageListenerContainer container(@Qualifier("redisConnectionFactory") RedisConnectionFactory connectionFactory,
                                            @Qualifier("stringSerializer") StringRedisSerializer stringRedisSerializer) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setTopicSerializer(stringRedisSerializer);
        return container;
    }



}