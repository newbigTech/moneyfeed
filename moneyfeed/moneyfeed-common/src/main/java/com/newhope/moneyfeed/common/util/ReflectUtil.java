package com.newhope.moneyfeed.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @ClassName: ReflectUtil
 * @Description: 反射工具类
 * @author A18ccms a18ccms_gmail_com
 * @date 2017年5月16日 下午3:50:36
 * 
 */
public class ReflectUtil {

	private static final String SETTER_PREFIX = "set";

	private static final String GETTER_PREFIX = "get";

	/**
	 * @throws Exception
	 * @Title: getValueByCoulmn
	 * @Description: 反射根据属性名的字符串返回值
	 * @param @param obj
	 * @param @param field
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public static Object getValueByCoulmn(Object obj, String field) throws Exception {
		Class<?> clazz = obj.getClass();
		Field field1;
		try {
			field1 = clazz.getDeclaredField(field);
			PropertyDescriptor pd = new PropertyDescriptor(field1.getName(), clazz);
			Method getMethod = pd.getReadMethod();
			Object o = getMethod.invoke(obj);
			return (o == null) ? "" : o;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * 判断bean中属性值是不是空值
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static boolean emptyBean(Object bean) throws Exception {
		Method[] methods = bean.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
				Object value = method.invoke(bean);
				if (value != null) {
					if (value instanceof List) {
						List list = (List) value;
						if (list.size() > 0) {
							return false;
						}
					} else if (value instanceof String) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 调用Getter方法.
	 * 支持多级，如：对象名.对象名.方法
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object invokeGetter(Object obj, String propertyName)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object object = obj;
		for (String name : StringUtils.split(propertyName, ".")) {
			String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
			object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
		}
		return object;
	}

	/**
	 * 调用Setter方法, 仅匹配方法名。
	 * 支持多级，如：对象名.对象名.方法
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void invokeSetter(Object obj, String propertyName, Object value)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object object = obj;
		String[] names = StringUtils.split(propertyName, ".");
		for (int i = 0; i < names.length; i++) {
			if (i < names.length - 1) {
				String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
				object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
			} else {
				String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
				invokeMethodByName(object, setterMethodName, new Object[] { value });
			}
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 * 用于一次性调用的情况，否则应使用getAccessibleMethod()函数获得Method后反复调用.
	 * 同时匹配方法名+参数类型，
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		return method.invoke(obj, args);

	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符，
	 * 用于一次性调用的情况，否则应使用getAccessibleMethodByName()函数获得Method后反复调用.
	 * 只匹配函数名，如果有多个同名函数调用第一个。
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		return method.invoke(obj, args);

	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 只匹配函数名。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
	 * args)
	 */
	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType
				.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}
		return null;
	}

	/**
	 * 改变private/protected的方法为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 匹配函数名+参数类型。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
	 * args)
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType
				.getSuperclass()) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
				continue;// new add
			}
		}
		return null;
	}

	/**
	 * 参数对象作为查询参数时，如果String类型的属性值进行like查询，
	 * 当value中包含%或_,且%或_并不作为通配符，
	 * 即作为字符串进行查询时，需要对%和_进行转义，同时在like语句后面加上关键字 ESCAPE '/'
	 * <p>
	 * 例：
	 * 原： customerNum = "20181204%"
	 * 转义：customerNum = "20181204/%"
	 * c.`ebs_customer_num` LIKE CONCAT(CONCAT('%',
	 * #{queryParam.customerNum},'%')) ESCAPE '/'
	 * 
	 * @param obj
	 */
	public static void resetAttributeValue(Object obj) {
		resetAttributeValue(obj, true);
	}

	public static void resetAttributeValue(Object obj, boolean changedFlag) {
		Field[] field = obj.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组

		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																// "，后面跟类名
					Method m = obj.getClass().getMethod("get" + name);
					String value = (String) m.invoke(obj);
					if (value == null) {
						if (changedFlag) {
							m = obj.getClass().getMethod("set" + name, String.class);
							m.invoke(obj, "");
						}
					} else {
						m = obj.getClass().getMethod("set" + name, String.class);
						if (value.indexOf("%") > -1) {
							String newValue = value.substring(0, value.indexOf("%")) + "/"
									+ value.substring(value.indexOf("%"), value.length());
							m.invoke(obj, newValue);
						}
						if (value.indexOf("_") > -1) {
							String newValue = value.substring(0, value.indexOf("_")) + "/"
									+ value.substring(value.indexOf("_"), value.length());
							m.invoke(obj, newValue);
						}
					}
				}

			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
