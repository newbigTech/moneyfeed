package com.newhope.moneyfeed.integration.biz.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.newhope.moneyfeed.api.annotation.FieldFilter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidationUtils {
	private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);
	
	private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
	
	public static <T> ValidationResult validateEntity(T obj){
		ValidationResult result = new ValidationResult();
		 Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
		 if( CollectionUtils.isNotEmpty(set) ){
			 result.setHasErrors(true);
			 Map<String,String> errorMsg = new HashMap<String,String>();
			 for(ConstraintViolation<T> cv : set){
				 errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			 }
			 result.setErrorMsg(errorMsg);
		 }
		 return result;
	}
	
	public static <T> ValidationResult validateProperty(T obj,String propertyName){
		ValidationResult result = new ValidationResult();
		 Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
		 if( CollectionUtils.isNotEmpty(set) ){
			 result.setHasErrors(true);
			 Map<String,String> errorMsg = new HashMap<String,String>();
			 for(ConstraintViolation<T> cv : set){
				 errorMsg.put(propertyName, cv.getMessage());
			 }
			 result.setErrorMsg(errorMsg);
		 }
		 return result;
	}
	
	/**
	* @throws Exception   
	* @Title: isParameterFilter  
	* @Description: 判断是否需要做敏感词过滤,返回true表示需要做验证
	* @param @param obj 传入的类对象
	* @param @return    设定文件  
	* @return Map<String,Object>    返回类型  
	* @throws  
	*/
	public static List<String> getFiltedParameters(Object obj) throws Exception{
		//需要验证的字段集合
		List<String> parameters = new ArrayList<>();
		//找出类中需要验证的字段
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			//设置可访问私有变量
			field.setAccessible(true);
			//获取字段的值
			try {
				//获取字段上的所有注解集合
				Annotation[] arrAnno = field.getAnnotations();
				for (Annotation annotation : arrAnno) {
					//获取注解的class
					Class<?> annoCls = annotation.annotationType();
					//如果注解需要过滤敏感词,返回true
					if(annoCls == FieldFilter.class){
						Object value = field.get(obj);
						if(value != null && !"".equals(value) ){
							parameters.add(String.valueOf(value));
						}
					}
				}
			} catch (Exception e) {
				logger.error("[ValidationUtils.getFiltedParameters]系统异常:", e);
				throw new Exception(e);
			}
		}
		return parameters;
	}
	
	public static class ValidationResult {

		//校验结果是否有错
		private boolean hasErrors;
		
		//校验错误信息
		private Map<String,String> errorMsg;

		public String errorMsgDetail(){
			Iterator<Entry<String, String>> iterator = errorMsg.entrySet().iterator();
			StringBuilder build = new StringBuilder("");
			while(iterator.hasNext()){
				Entry<String, String> entry = iterator.next();
				build.append(","+entry.getValue());
			}
			return build.toString().replaceFirst(",", "");
		}
		
		public boolean isHasErrors() {
			return hasErrors;
		}

		public void setHasErrors(boolean hasErrors) {
			this.hasErrors = hasErrors;
		}

		public Map<String, String> getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(Map<String, String> errorMsg) {
			this.errorMsg = errorMsg;
		}

		@Override
		public String toString() {
			return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
					+ errorMsg + "]";
		}

	}
	
	
}
