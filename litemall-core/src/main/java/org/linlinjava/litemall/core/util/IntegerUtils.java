/*
 * Copyright 2010-2015 icl-network.com. All rights reserved.
 * Support: http://www.icl-network.com
 */
package org.linlinjava.litemall.core.util;

import java.math.BigDecimal;

/**
 * Utils - 整数
 *
 * @author ICLNetwork Team
 * @version 5.0
 */
public final class IntegerUtils {
	
	private IntegerUtils() {
	}
	
	/**
	 * 转换对象
	 *
	 * @param object 对象
	 * @return 整数
	 */
	public static Integer valueOf(Object object) {
		if (object == null)
			return null;
		try {
			return Integer.valueOf(object.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 判断是否大于
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 是否大于
	 */
	public static boolean gt(Integer sourceValue, Integer targetValue) {
		if (sourceValue == null || targetValue == null)
			throw new IllegalArgumentException();
		return sourceValue.compareTo(targetValue) == 1;
	}
	
	/**
	 * 判断是否大于或等于
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 是否大于或等于
	 */
	public static boolean ge(Integer sourceValue, Integer targetValue) {
		if (sourceValue == null && targetValue == null)
			return true;
		if (sourceValue == null || targetValue == null)
			throw new IllegalArgumentException();
		return sourceValue.compareTo(targetValue) != -1;
	}
	
	/**
	 * 判断是否小于
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 是否小于
	 */
	public static boolean lt(Integer sourceValue, Integer targetValue) {
		if (sourceValue == null || targetValue == null)
			throw new IllegalArgumentException();
		return sourceValue.compareTo(targetValue) == -1;
	}
	
	/**
	 * 判断是否小于或等于
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 是否小于或等于
	 */
	public static boolean le(Integer sourceValue, Integer targetValue) {
		if (sourceValue == null && targetValue == null)
			return true;
		if (sourceValue == null || targetValue == null)
			throw new IllegalArgumentException();
		return sourceValue.compareTo(targetValue) != 1;
	}
	
	/**
	 * 判断是否高低位间
	 *
	 * @param sourceValue 源值
	 * @param lowValue    低位值
	 * @param highValue   高位值
	 * @return 是否高低位间
	 */
	public static boolean between(Integer sourceValue, Integer lowValue, Integer highValue) {
		if (sourceValue == null && lowValue == null && highValue == null)
			return true;
		if (sourceValue == null || lowValue == null || highValue == null)
			return false;
		return sourceValue.compareTo(lowValue) != -1 && sourceValue.compareTo(highValue) != 1;
	}
	
	/**
	 * 判断是否不为高低位间
	 *
	 * @param sourceValue 源值
	 * @param lowValue    低位值
	 * @param highValue   高位值
	 * @return 是否不为高低位间
	 */
	public static boolean notBetween(Integer sourceValue, Integer lowValue, Integer highValue) {
		return !between(sourceValue, lowValue, highValue);
	}
	
	/**
	 * 比较
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 比较结果
	 */
	public static int compare(Integer sourceValue, Integer targetValue) {
		if (sourceValue == null && targetValue == null)
			return 0;
		if (sourceValue == null || targetValue == null)
			throw new IllegalArgumentException();
		return sourceValue.compareTo(targetValue);
	}
	
	/**
	 * 判断是否可被整除
	 *
	 * @param dividend 被除数
	 * @param divisor  除数
	 * @return 是否可被整除
	 */
	public static boolean isDivisible(Integer dividend, Integer divisor) {
		return DecimalUtils.isDivisible(new BigDecimal(dividend), new BigDecimal(divisor));
	}
	
	/**
	 * 判断是否不可被整除
	 *
	 * @param dividend 被除数
	 * @param divisor  除数
	 * @return 是否不可被整除
	 */
	public static boolean isNotDivisible(Integer dividend, Integer divisor) {
		return !isDivisible(dividend, divisor);
	}
	
}