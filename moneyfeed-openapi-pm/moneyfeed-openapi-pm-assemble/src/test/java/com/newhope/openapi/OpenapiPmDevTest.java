
package com.newhope.openapi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class OpenapiPmDevTest {

	public static ApplicationContext appContext;
	
	public static void main( String[] args ) throws Exception {
		System.setProperty("spring.cloud.config.label", "RD-DGP1");
		appContext = new SpringApplicationBuilder().sources( BootStrap.class ).profiles( "dev" ).run( args );
	}

}
