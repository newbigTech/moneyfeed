package com.newhope.moneyfeed.mq.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.newhope.moneyfeed.mq.adapter.event.EventType;
import com.newhope.moneyfeed.mq.adapter.processor.Processor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BizDataSyncAnnotation {
	EventType eventType();

	Processor processor();
}
