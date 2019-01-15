package com.newhope.moneyfeed.integration.web.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * JSR和Hibernate validator的校验只能对Object的属性进行校验，不能对单个的参数进行校验，
 * Spring 在此基础上进行了扩展，添加了MethodValidationPostProcessor拦截器，以实现对方法参数的校验
 * 使用方法：
 * 1.在所要实现方法参数校验的类上面添加@Validated
 * 2.在方法上面添加校验规则，如 @Length(min = 10) @RequestParam String name
 *
 * @author: zhangyanyan
 * @date: 2018/3/6
 */
@Configuration
public class MethodValidationConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator());
        postProcessor.setProxyTargetClass(true);
        return postProcessor;
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                //设置validator模式为快速失败返回
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
