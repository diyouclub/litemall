/*
 * Copyright 2010-2016 icl-network.com. All rights reserved.
 * Support: http://www.icl-network.com
 */
package org.linlinjava.litemall.core.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utils - 日期
 *
 * @author ICLNetwork Team
 * @version 5.0
 */
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	/** 日期格式 */
	public static final String[] DATE_FORMATS = new String[]{"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};
	
	/** 14位日期 */
	public static final String FMT_14 = "yyyyMMddHHmmss";
	
	/** 日期格式 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/** 日期格式 */
	public static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日 HH:mm:ss";
	
	/** 时间格式 */
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/** 日期格式 */
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	/** 时间格式 */
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
	
	private DateUtils() {
		
	}
	
	public static String getNow() {
		return format(new Date(), TIME_FORMAT);
	}
	
	/**
	 * 比较日期
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 比较结果
	 */
	public static int compare(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		return leftHandSide.compareTo(rightHandSide);
	}
	
	/**
	 * 解析日期
	 *
	 * @param date   日期
	 * @param format 格式
	 * @return 日期
	 */
	public static Date parseDate(String date, String format) {
		if (StringUtils.isBlank(date) || StringUtils.isBlank(format) || !ArrayUtils.contains(DATE_FORMATS, format))
			return null;
		try {
			return parseDate(date, new String[]{format});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 解析日期
	 *
	 * @param date 日期
	 * @return 时间
	 */
	public static Date parseDate(String date) {
		if (StringUtils.isBlank(date))
			return null;
		try {
			return parseDate(date, DATE_FORMATS);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 解析日期
	 *
	 * @param date 日期
	 * @return 时间
	 */
	public static Date parseDate(Object date) {
		if (date == null)
			return null;
		return parseDate(date.toString());
	}
	
	/**
	 * 格式日期
	 *
	 * @param date   日期
	 * @param format 格式
	 * @return 字符串
	 */
	public static String format(Date date, String format) {
		if (date == null || StringUtils.isBlank(format)/* || !ArrayUtils.contains(DATE_FORMATS, format) */)
			return null;
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 格式日期
	 *
	 * @param date 日期
	 * @return 字符串
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return null;
		return dateFormat.format(date);
	}
	
	/**
	 * 格式时间
	 *
	 * @param time 时间
	 * @return 字符串
	 */
	public static String formatTime(Date time) {
		if (time == null)
			return null;
		return timeFormat.format(time);
	}
	
	/**
	 * 计算年份求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 年份求差
	 */
	public static int calculateYearDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		Calendar leftCalendar = Calendar.getInstance();
		leftCalendar.setTime(leftHandSide);
		Calendar rightCalendar = Calendar.getInstance();
		rightCalendar.setTime(rightHandSide);
		return leftCalendar.get(Calendar.YEAR) - rightCalendar.get(Calendar.YEAR);
	}
	
	/**
	 * 计算月数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 月数求差
	 */
	public static int calculateMonthDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		Calendar leftCalendar = Calendar.getInstance();
		leftCalendar.setTime(leftHandSide);
		Calendar rightCalendar = Calendar.getInstance();
		rightCalendar.setTime(rightHandSide);
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.setTime(rightHandSide);
		tempCalendar.add(Calendar.DATE, 1);
		int year = leftCalendar.get(Calendar.YEAR) - rightCalendar.get(Calendar.YEAR);
		int month = leftCalendar.get(Calendar.MONTH) - rightCalendar.get(Calendar.MONTH);
		if (leftCalendar.get(Calendar.DATE) == 1 && tempCalendar.get(Calendar.DATE) == 1)
			return year * 12 + month + 1;
		else if (leftCalendar.get(Calendar.DATE) != 1 && tempCalendar.get(Calendar.DATE) == 1)
			return year * 12 + month;
		else if (leftCalendar.get(Calendar.DATE) == 1 && tempCalendar.get(Calendar.DATE) != 1)
			return year * 12 + month;
		else
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
	}
	
	/**
	 * 计算周数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 周数求差
	 */
	public static BigDecimal calculateWeekDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		return new BigDecimal(leftHandSide.getTime() - rightHandSide.getTime()).divide(DecimalUtils.MILLISECONDS_AS_WEEK, 2, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 计算天数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 天数求差
	 */
	public static BigDecimal calculateDayDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		return new BigDecimal(leftHandSide.getTime() - rightHandSide.getTime()).divide(DecimalUtils.MILLISECONDS_AS_DAY, 2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * 计算天数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @param roundingMode 取整模式
	 * @return 天数求差
	 */
	public static BigDecimal calculateDayDifference(Date leftHandSide, Date rightHandSide, RoundingMode roundingMode, BigDecimal min) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		BigDecimal[] result = new BigDecimal(leftHandSide.getTime() - rightHandSide.getTime()).divideAndRemainder(DecimalUtils.MILLISECONDS_AS_DAY);
		if (result[1].compareTo(BigDecimal.ZERO) == -1 && min!=null)
			return min;
		if (RoundingMode.UP.equals(roundingMode))
			return result[0].add(result[1].compareTo(BigDecimal.ZERO)==1? BigDecimal.ONE: BigDecimal.ZERO);
		if (RoundingMode.HALF_UP.equals(roundingMode))
			return result[0].add(result[1].compareTo(new BigDecimal(1000*60*60*12))>=0? BigDecimal.ONE: BigDecimal.ZERO);
		return result[0];
	}
	
	/**
	 * 计算时数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 时数求差
	 */
	public static BigDecimal calculateHourDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		return new BigDecimal(leftHandSide.getTime() - rightHandSide.getTime()).divide(DecimalUtils.MILLISECONDS_AS_HOUR, 2, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 计算分数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 分数求差
	 */
	public static BigDecimal calculateMinuteDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		return new BigDecimal(leftHandSide.getTime() - rightHandSide.getTime()).divide(DecimalUtils.MILLISECONDS_AS_MINUTE, 2, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 计算秒数求差
	 *
	 * @param leftHandSide  等式左边
	 * @param rightHandSide 等式右边
	 * @return 秒数求差
	 */
	public static BigDecimal calculateSecondDifference(Date leftHandSide, Date rightHandSide) {
		if (leftHandSide == null || rightHandSide == null)
			throw new IllegalArgumentException("Date must not be null");
		return new BigDecimal(leftHandSide.getTime() - rightHandSide.getTime()).divide(DecimalUtils.MILLISECONDS_AS_SECOND, 2, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 获取零点日期
	 *
	 * @param date 日期
	 * @return 零点日期
	 */
	public static Date getZeroDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 添加日期
	 *
	 * @param date          日期
	 * @param calendarField 日历字段
	 * @param amount        数量
	 * @return 日期
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null)
			throw new IllegalArgumentException("The date must not be null");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarField, amount);
		return calendar.getTime();
	}
	
	/**
	 * 设置日期
	 *
	 * @param year  年份
	 * @param month 月份
	 * @param date  天数
	 * @return 日期
	 */
	public static Date set(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		return calendar.getTime();
	}
	
	/**
	 * 获取日期字段
	 *
	 * @param date          日期
	 * @param calendarField 日历字段
	 * @return 日期字段
	 */
	public static int get(Date date, int calendarField) {
		if (date == null)
			throw new IllegalArgumentException("The date must not be null");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(calendarField);
	}
	
	/**
	 * 获取天数
	 *
	 * @param date 日期
	 * @return 天数
	 */
	public static int getDays(Date date) {
		return get(date, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 判断为空时返回默认数值
	 *
	 * @param date         日期
	 * @param defaultValue 默认数值
	 * @return 数值
	 */
	public static Object defaultIfEmpty(Date date, Object defaultValue) {
		return date != null ? date : defaultValue;
	}
	
	/** 
	 * 取得当月天数 
	 * */  
	public static int getCurrentMonthLastDay() {  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    return maxDate;  
	} 
	/** 
	 * 取得第二天凌晨
	 * */  
	public static Date getSecondDay() {  
		Date today = parseDate(format(new Date(), DATE_FORMAT));
		Date secondDay = add(today, Calendar.DAY_OF_MONTH, 1);
		secondDay = parseDate(format(secondDay, DATE_FORMAT) + " 0:0:0");
		return secondDay;  
	} 
	/**
	 * 时间戳 转时间
	 * @param time
	 * @return
	 */
	public static Date stampToDate(Long time) { 
		 Date date = new Date(time);
		return date;  
	} 
	public static String stampToDateString(Long time) { 
		if(time == null){
			return "";
		}
		Date date = new Date(time);
		return format(date, DATE_FORMAT);
	} 

	/**
	 * 获取月份的第一天   xxxx-xx-1 00:00:00
	 * @param date 待获取日期
	 * @return xxxx-xx-1 00:00:00
	 */
	public static Date getFirstDateOfMonth(Date date){
	    if(date == null)
	        date = new Date();
	    return DateUtils.getZeroDate(DateUtils.set(get(date, Calendar.YEAR), get(date, Calendar.MONTH), 1));
	}
	
}