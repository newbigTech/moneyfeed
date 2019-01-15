package com.newhope;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class BootStrap extends WebBootStrap {
    public static void main(String[] args) throws Exception {
        WebBootStrap.builder(args).run();
    }
}
