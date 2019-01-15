package com.newhope.moneyfeed.common.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 常见帮助方法
 * 
 * @author dave chen
 *
 */
public class CommonHelper {

	/**
	 * 将异常的堆栈信息转换为字符串
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackMsg(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();
		String res = sw.toString();
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return res;

	}

	/**
	 * 获取ip地址信息
	 * 
	 * @return
	 */
	public static String hostIp() {
		InetAddress ia;
		String localIp = "";
		try {
			ia = InetAddress.getLocalHost();
			localIp = ia.getHostAddress();
		} catch (UnknownHostException e) {
			// logger.error("", e);
		}
		return localIp;
	}

	/**
	 * 获取当前调用类的基本信息
	 * 
	 * @return 返回最顶一级类名和方法名称
	 */
	public static ArrayList<String> getClassInfo() {

		ArrayList<String> list = new ArrayList<String>();
		Throwable ex = new Throwable();
		StackTraceElement[] stackElements = ex.getStackTrace();
		if (stackElements != null && stackElements.length > 2) {
			list.add(stackElements[2].getClassName());
			list.add(stackElements[2].getMethodName());
			list.add(stackElements[2].getFileName());
			list.add(String.valueOf(stackElements[2].getLineNumber()));

		} else {
			list.add("");
			list.add("");
			list.add("");
			list.add("");
		}

		return list;
	}

	public static boolean isEmptyOrNull(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 根据ClassLoader加载资源文件(单个), 使用默认的ClassLoader!
	 *
	 * @param path
	 *            不能以'/'开头的路径
	 * @return
	 */
	public static InputStream loadResourceByClassLoader(ClassLoader cl, String path) {
		if (cl == null) {
			cl = Thread.currentThread().getContextClassLoader();
		}
		return cl.getResourceAsStream(path);
	}

	// /*
	// * 将父类所有的属性COPY到子类中。 类定义中child一定要extends father；
	// * 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate
	// */
	// public static Object fatherToChild(Object father, Object child) throws
	// Exception
	// {
	// if (!(child.getClass().getSuperclass() == father.getClass()))
	// {
	// throw new Exception("child不是father的子类");
	// }
	// Class fatherClass = father.getClass();
	// Field ff[] = fatherClass.getDeclaredFields();
	// for (int i = 0; i < ff.length; i++)
	// {
	// Field f = ff[i];// 取出每一个属性，如deleteDate
	// Class type = f.getType();
	// Method m = fatherClass.getMethod("get" + upperHeadChar(f.getName()));//
	// 方法getDeleteDate
	// Object obj = m.invoke(father);// 取出属性值
	// f.set(child, obj);
	// }
	// return child;
	// }

	// /**
	// * 首字母大写，in:deleteDate，out:DeleteDate
	// */
	// private static String upperHeadChar(String in)
	// {
	// String head = in.substring(0, 1);
	// String out = head.toUpperCase() + in.substring(1, in.length());
	// return out;
	// }

	/**
	 * 获取默认设置时间(1900-01-01)
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date DefaultDate() {
		String dd = "1900-01-01";
		String[] format = { "yyyy-MM-dd" };
		Date date = null;
		try {
			date = DateUtils.parseDate(dd, format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 系统缺省guid(00000000-0000-0000-0000-000000000000)
	 * 
	 * @return
	 */
	public static String DefaultGuid() {
		return "00000000-0000-0000-0000-000000000000";
	}

	/**
	 * 判断值是否不等于null,"",00000000-0000-0000-0000-000000000000 三种情况
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNullOrDefault(String value) {
		if (StringUtils.isBlank(value) || DefaultGuid().equals(value)) {
			return false;
		}
		return true;
	}

	/**
	 * 返回真正的类型
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> List<T> getJsonRelList(List list, Class<T> type) {
		List<T> relList = new ArrayList<T>(list.size());
		for (Object o : list) {
			if (o instanceof JSONObject) {
				JSONObject temp = (JSONObject) o;
				relList.add(JSON.parseObject(temp.toJSONString(), type));
			} else {
				relList.add((T) o);
			}
		}

		return relList;
	}
	
	/**
	 * 分解url：服务器地址+uri
	 * eg: http://zxe.newhope.com/test/123 = http://zxe.newhope.com + /test/123
	 * @param url
	 * @return
	 */
	public static String[] splitUrl(String url) {
		String[] result = new String[2];
		result[0] = url.substring(0, url.indexOf("/", 8));
		result[1] = url.substring(url.indexOf("/", 8), url.length());
		return result;
	}
	
	public static void main(String[] args) {
		String[] urls = splitUrl("http://wx.qlogo.cn/mmopen/OiatEPhbe5AABXZ4hb6IgQGPeicpQ55tFA5ky79ibUsnHCdhvx4TcxNqqqicTpYEeSwrl4PRlcOj1rv9leKL9hekibaOVLS8AJ0bZ/0");
		System.out.println(urls[0]);
		System.out.println(urls[1]);
	}
}
