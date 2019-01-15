package com.newhope.assemble;

import org.springframework.boot.builder.SpringApplicationBuilder;

import com.newhope.BootStrap;

public class MoneyfeedGoodsDevTest {
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.cloud.config.label", "RD-DGP1");// 开发环境
		new SpringApplicationBuilder().sources(BootStrap.class).profiles("dev").run(args);
		/*
		System.setProperty("spring.cloud.config.label", "RD-TGP1");// test环境
		new SpringApplicationBuilder().sources(BootStrap.class).profiles("test1").run(args);
		 */

	}
}
