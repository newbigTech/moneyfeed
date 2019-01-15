package com.newhope.feedback.biz.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/* 时间格式：yyyy-MM-dd HH:mm:ss */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /* 时间格式：yyyy-MM-dd */
    public static final String YYYY_MM_DD= "yyyy-MM-dd";

    /* 时间格式：yyyyMMdd HH:mm:ss */
    public static final String YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";

    /* 时间格式：yyyyMMdd */
    public static final String YYYYMMDD = "yyyyMMdd";
    
    /* 时间格式：yyMMdd */
    public static final String YYMMDD = "yyMMdd";

    /* 时间格式：yyyyMM */
    public static final String YYYYMM = "yyyyMM";

    /* 时间格式：YYYYMMDDHHMMSS */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    public static final String EEEMMMDDHHMMSSZZZYYYY= "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String PERIOD_UNIT_MINUTE = "m";
    public static final String PERIOD_UNIT_HOUR = "h";
    public static final String PERIOD_UNIT_DAY = "d";
    public static final String PERIOD_UNIT_MONTH = "M";
    
 // 以毫秒表示的时间
 	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
 	private static final long HOUR_IN_MILLIS = 3600 * 1000;
 	private static final long MINUTE_IN_MILLIS = 60 * 1000;
 	private static final long SECOND_IN_MILLIS = 1000;
    
	/**
	 * 将指定字符串转换成日期
	 * 
	 * @param date
	 *            String 日期字符串
	 * @param datePattern
	 *            String 日期格式
	 * @return Date
	 */
	public static java.util.Date getDate(String dateStr, String datePattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
        	logger.error("[DateUtil.getDate]解析异常", e);
        }
        return date;
	}
	
	/**
	 * 将指定日期对象转换成格式化字符串
	 * 
	 * @param date
	 *            Date XML日期对象
	 * @param datePattern
	 *            String 日期格式
	 * @return String
	 */
	public static String getDateStr(Date date, String datePattern) {
		SimpleDateFormat sd = new SimpleDateFormat(datePattern);
		return sd.format(date);
	}

	public static String getCurrentYear() {
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		// 获得年月日
		String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
		return strYear;
	}

	public static String getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		// 获得年
		String strYear = String.valueOf(cld.get(Calendar.YEAR));
		return strYear;
	}

	// 获取当天时间
	public static java.sql.Timestamp getCurrentTimeStamp(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String dateString = dateFormat.format(now);
		SimpleDateFormat sd = new SimpleDateFormat(dateformat);
		Date dateFormt = sd.parse(dateString, new java.text.ParsePosition(0));
		java.sql.Timestamp dateTime = new java.sql.Timestamp(
				dateFormt.getTime());

		return dateTime;
	}

	// 获取指定时间
	public static java.sql.Timestamp getTimeStamp(String date, String dateformat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		Date dateFormt = dateFormat.parse(date, new java.text.ParsePosition(0));
		java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());
		return dateTime;
	}

	public static Long getTime(String dateStr, String dateformat, Locale locale) {
		try { 
            return new SimpleDateFormat(dateformat, locale).parse(dateStr).getTime(); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        return null;
	}
	/**
	 * @param 含有yyyy
	 *            -MM-dd'T'hh:mm:ss.SSS格式的时间转换.
	 * @return
	 */
	public static String getTFormatString(String tdate) {
		SimpleDateFormat format1 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS");
		String str = "";
		try {
			java.util.Date date = format1.parse(tdate);
			SimpleDateFormat format2 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			str = format2.format(date);
		} catch (ParseException e) {
			logger.error("[DateUtil.getTFormatString]解析异常", e);
		}
		return str;
	}

	/**
	 * 获取n小时前的时间
	 * @param n
	 * @return
	 */
	public static String getHourOfDate(int n) {
		SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, -n); // 目前時間加3小時
		return df.format(c.getTime());

	}

	/**
	 * 将毫秒转换成日期格式
	 * @param dateFormat
	 * @param millSec
	 * @return
	 */
	public static String getDateOfMillsec(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
	/**
	 * 将字符串转换成毫秒
	 * @param tdate
	 * @param formate
	 * @return
	 */
	public static long getMillsecOfDate(String tdate, String format){
		SimpleDateFormat format1 = new SimpleDateFormat(format);
		java.util.Date date = new Date();
		try {
			date = format1.parse(tdate);
		} catch (ParseException e) {
			logger.error("[DateUtil.getMillsecOfDate]解析异常", e);
		}
		return date.getTime();
	}
	

	/**
	 * 
	 * @param time1
	 *            当前时间
	 * @param time2
	 *            比较时间
	 * @param gap
	 * 			  时间间隔，单位分钟          
	 * @return 如果time1比time2大gap分钟，则返回true;
	 */
	public static boolean compareDateTime(Date time1, Date time2, int gap, String unit) {
		boolean result = false;
		switch(unit) {
			case "D" :	//天
				result = (time1.getTime() - time2.getTime() > gap * 86400000);
				break;
			case "H" :	//时
				result = (time1.getTime() - time2.getTime() > gap * 3600000);
				break;
			case "M" :	//分
				result = (time1.getTime() - time2.getTime() > gap * 60000);
				break;
			default:	//天
				result = (time1.getTime() - time2.getTime() > gap * 86400000);
				break;
		}
		return result;
	}
	
	public static boolean compareDateTime(Date time1, Date time2) {
		return compareDateTime(time1, time2, 0, "M");
	}
	
	/**
	 * 获取时间差
	 * @param time1
	 * @param time2
	 * @param unit 时间差单位，默认为D(天)
	 * @return
	 */
	public static long getDateGap(Date startTime, Date endTime, String unit) {
		return getDateGap(startTime.getTime(), endTime.getTime(), unit);
	}
	
	/**
	 * 获取时间差
	 * @param startTime
	 * @param endTime
	 * @param unit 时间差单位，默认为D(天)
	 * @return
	 */
	public static long getDateGap(long startTime, long endTime, String unit) {
		long gap = 0l;
		switch(unit) {
			case "D" :	//天
				gap = (endTime - startTime) / 86400000;
				break;
			case "H" :	//时
				gap = (endTime - startTime) / 3600000;
				break;
			case "M" :	//分
				gap = (endTime - startTime) / 60000;
				break;
			default:	//天
				gap = (endTime - startTime) / 86400000;
				break;
		}
		return gap;
	}
	
	/**
     * 获取时间戳，格式DATE_FORMAT_YYYYMMDDHHMMSS
     */
    public static String getCurrentTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    
    /**
     * 获取几分钟后
     * <功能详细描述>
     *
     * @param date
     * @param minute
     * @return String [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String addMinute(String dateStr, int minute, String dateFormat) {
        Calendar cale = Calendar.getInstance();
        Date date = StringToDate(dateStr, dateFormat);
        cale.setTime(date);
        cale.add(Calendar.MINUTE, minute);
        Date tasktime = cale.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(tasktime);
    }
    
    /**
     * 时间增加n天
     */
    public static Date addDays(Date date, int days) {
    	Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.DATE, days);
        return cale.getTime();
    }
    
    /**
     * 时间增加n分钟
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
    	Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MINUTE, minute);
        return cale.getTime();
    }
    /**
     * 时间增加n秒
     * @param date
     * @param minute
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
    	Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.SECOND, seconds);
        return cale.getTime();
    }
    /**
     * 将字符转换为日期类型
     * <功能详细描述>
     *
     * @param dateStr
     * @param sourceFormat
     * @return Date [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date StringToDate(String dateStr, String sourceFormat) {
    	if(StringUtils.isBlank(dateStr)){
    		logger.error("日期格式不正确，传入日期为空");
    		throw new RuntimeException("日期格式不正确，传入日期为空");
    	}
        SimpleDateFormat dateFormat = new SimpleDateFormat(sourceFormat);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
        	logger.error("日期格式不正确：" + dateStr, e);
        	throw new RuntimeException("日期格式不正确：" + dateStr);
        }
        return date;
    }
    
    /**
     * 分钟数动态转换为分，时，天，月
     * @param minute
     * @param periodLenth : 允许的单位值长度，如120分钟表示2小时，若maxPeriodLen=3，则表示为120m，超过maxPeriodLen则向上转换，如1440m转换为1d
     * 						若为0表示，时间单位按标准转换,1mon=30d=1440h=43200m
     * @return
     */
    public static String transferMinute(long minute, int maxPeriodLen) {
    	String result = "";
    	
    	if (maxPeriodLen == 0) { //按标准时间转换
    		long mon = minute / 43200;
    		long day = minute % 43200 / 1440;
    		long hour = minute % 43200 % 1440 / 60;
        	long mi = minute % 43200 % 1440 % 60;
        	if (mon > 0) { //单位月
        		result = mon + PERIOD_UNIT_MONTH;
        	}
        	if (day > 0) { //单位天
        		result = result + day + PERIOD_UNIT_DAY;
        	}
        	if (hour > 0) { //单位小时
        		result = result + hour + PERIOD_UNIT_HOUR;
        	}
        	if (mi > 0) {
        		result = result + mi + PERIOD_UNIT_MINUTE;
        	}
    	} else { //根据maxPeriodLen值转换
    		long power = (long)Math.pow(10, maxPeriodLen);
    		boolean isDay = ((minute / 1440) / (power) == 0) ? true : false;
    		boolean isHour = ((minute / 60) / (power) == 0) ? true : false;
    		boolean isMi = (minute / (power) == 0) ? true : false;
    		
    		long mon = minute / 43200;
    		long day = minute / 1440;
    		long hour = minute / 60;
    		if (isMi) {
    			result = minute + PERIOD_UNIT_MINUTE;
    		} else if (isHour) {
    			result = hour + PERIOD_UNIT_HOUR;
    		} else if (isDay) {
    			result = day + PERIOD_UNIT_DAY;
    		} else {
    			result = mon + PERIOD_UNIT_MONTH;
    		}
    	}
    	return result;
    }
    
    /**
     * 获取时间的月和日
     * @param date
     * @return eg:8月8日
     */
    public static String getMonthAndDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int month = cal.get(Calendar.MONTH) + 1;
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	return new StringBuilder().append(month).append("月")
    			.append(day).append("日")
    			.toString();
    }
    
    public static long getMillis(Calendar cal) {
		return cal.getTime().getTime();
	}
    
    /**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes)
	{

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y')
		{
			return (calSrc.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
		}

		if (flag == 'd')
		{
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h')
		{
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm')
		{
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's')
		{
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}
	
	
	/**
	 * 将时分秒设置成0
	 * 
	 * @param date
	 * @return
	 */
	public static Date trunc(Date date)
	{
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 设置当前时刻的时钟为0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 设置当前时刻的分钟为0
		c.set(Calendar.MINUTE, 0);
		// 设置当前时刻的秒钟为0
		c.set(Calendar.SECOND, 0);
		// 设置当前的毫秒钟为0
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}
	
	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author dave chen
	 */
	public static int getDaySub(Date beginDate, Date endDate) {
		int day = 0;
		day = (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000));
		return day;
	}
	
	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	
	public static boolean isSameDay(Date date1, Date date2){
		if(date1 == null
				|| date2 == null){
			return false;
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) 
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) ){
			return true;
		}
		return false;
	}
	
    public static void main(String[] args) {
    	System.out.println(DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
	}
}
