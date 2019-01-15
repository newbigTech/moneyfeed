package com.newhope.moneyfeed.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class test {

    public static void main(String[] args) {
        SpringApplication.run(test.class, args);
    }
}
