package com.newhope.openapi.web.config;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.enums.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration 
@EnableSwagger2 
public class SwaggerConfig {
	
	@Value("${newhope.trade.swagger.enable}")
	private boolean enable;
	
	public static final String apikeyName = "apikey";
	private static final String keyName = "newhope";
	private static final String password = "Newhope1982#";
	public static final String authScope = "global";
	public static final String authScopeDesc = "accessEverything";
	
	@Bean
    public Docket dock() {
		ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenPar.name("TOKEN").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2).enable(enable)
				.globalResponseMessage(RequestMethod.POST, getDefaultResponseMessages())
				.globalResponseMessage(RequestMethod.GET, getDefaultResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, getDefaultResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, getDefaultResponseMessages())
				.forCodeGeneration(true)
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.newhope.openapi.web.controller"))
				.paths(PathSelectors.any()).build().globalOperationParameters(parameters);
		// .securitySchemes(apiKey())
		// .securityContexts(securityContext());
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("聚宝猪系统微信端OPENAPI")
                .description("聚宝猪系统微信端OPENAPI")
                .contact("Newhope:R&D Center")
                .version("2.0")
                .build();
    }
	
	private List<ResponseMessage> getDefaultResponseMessages() {
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		for (ResultCode resultCode : ResultCode.values()) {
			responseMessages.add(new ResponseMessageBuilder()
					.code(resultCode.getCode())
					.message(resultCode.name() + "：" + resultCode.getDesc())
					.responseModel(new ModelRef(""))
					.build());
		}
		return responseMessages;
	}
	
	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", 
				"test-app-client-secret", 
				"test-app-realm", 
				"test-app",
				"apiKey", 
				ApiKeyVehicle.HEADER, 
				keyName,
				"," /* scope separator */);
	}
	
	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", // url
				"none", // docExpansion => none | list
				"alpha", // apiSorter => alpha
				"schema", // defaultModelRendering => schema
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, 
				false, // enableJsonEditor
				true, // showRequestHeaders => true | false
				60000L); // requestTimeout => in milliseconds, defaults to null
	}
	
	private List<ApiKey> apiKey() {
		List<ApiKey> keys = new ArrayList<>(1);
		keys.add(new ApiKey(apikeyName, keyName, password));
	    return keys;
	}

	private List<SecurityContext> securityContext() {
		List<SecurityContext> securityContext = new ArrayList<>(1);
		securityContext.add(SecurityContext.builder()
								.securityReferences(defaultAuth())
								.forPaths(PathSelectors.any())
								.build());
		return securityContext;
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope(authScope, authScopeDesc);
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> defaultAuth = new ArrayList<SecurityReference>();
		defaultAuth.add(new SecurityReference(keyName, authorizationScopes));
		return defaultAuth;
	}
}
