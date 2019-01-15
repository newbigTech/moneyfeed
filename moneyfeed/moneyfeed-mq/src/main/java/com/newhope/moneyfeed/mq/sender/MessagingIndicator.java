package com.newhope.moneyfeed.mq.sender;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface MessagingIndicator {
	String source() default "";// indicate the source which is required to send
								// message
	boolean checkReturn() default false;
	String messageTemplateCode() default "";// message template code

	String processor() default "processor.default";
}
