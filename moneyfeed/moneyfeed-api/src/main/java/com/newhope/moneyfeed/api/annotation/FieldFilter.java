package com.newhope.moneyfeed.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
* @ClassName: FieldFilter  
* @Description: 字段内容过滤
* @author A18ccms a18ccms_gmail_com  
* @date 2017年8月7日 下午2:49:44  
*/
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldFilter {
}
