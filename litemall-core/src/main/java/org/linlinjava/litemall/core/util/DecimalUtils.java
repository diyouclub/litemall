/*
 * Copyright 2010-2016 icl-network.com. All rights reserved.
 * Support: http://www.icl-network.com
 */
package org.linlinjava.litemall.core.util;


import java.math.BigDecimal;

/**
 * Utils - 小数
 *
 * @author ICLNetwork Team
 * @version 5.0
 */
public final class DecimalUtils {
	
	/** 百 */
	public static final BigDecimal HUNDRED = new BigDecimal(100);
	
	/** 千 */
	public static final BigDecimal THOUSAND = new BigDecimal(1000);
	
	/** 万 */
	public static final BigDecimal WAN = new BigDecimal(10000);
	
	/** 百万 */
	public static final BigDecimal MILLION = new BigDecimal(1000000);
	
	/** 每年天数 */
	public static final BigDecimal DAYS_AS_YEAR = new BigDecimal(365);
	
	/** 每月天数 */
	public static final BigDecimal DAYS_AS_MONTH = new BigDecimal(30);
	
	/** 每年月数 */
	public static final BigDecimal MONTHS_AS_YEAR = new BigDecimal(12);
	
	/** 每秒毫秒 */
	public static final BigDecimal MILLISECONDS_AS_SECOND = THOUSAND;
	
	/** 每分毫秒 */
	public static final BigDecimal MILLISECONDS_AS_MINUTE = new BigDecimal(1000 * 60);
	
	/** 每时毫秒 */
	public static final BigDecimal MILLISECONDS_AS_HOUR = new BigDecimal(1000 * 60 * 60);
	
	/** 每天毫秒 */
	public static final BigDecimal MILLISECONDS_AS_DAY = new BigDecimal(1000 * 60 * 60 * 24);
	
	/** 每周毫秒 */
	public static final BigDecimal MILLISECONDS_AS_WEEK = new BigDecimal(1000 * 60 * 60 * 24 * 7);
	
	//	/** 最大位数 */
	//	public static final int MAX_SCALE = 27;
	
	private DecimalUtils() {
	}
	
	/**
	 * 转换对象
	 *
	 * @param object 对象
	 * @return 大型小数
	 */
	public static BigDecimal valueOf(Object object) {
		if (object == null)
			return null;
		try {
			return new BigDecimal(object.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 判断是否等于
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 是否等于
	 */
	public static boolean equals(BigDecimal sourceValue, BigDecimal targetValue) {
		if (sourceValue == null && targetValue == null)
			return true;
		if (sourceValue == null || targetValue == null)
			return false;
		return sourceValue.compareTo(targetValue) == 0;
	}
	
	/**
	 * 判断是否大于
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 是否大于
	 */
	public static boolean gt(BigDecimal sourceValue, BigDecimal targetValue) {
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
	public static boolean ge(BigDecimal sourceValue, BigDecimal targetValue) {
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
	public static boolean lt(BigDecimal sourceValue, BigDecimal targetValue) {
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
	public static boolean le(BigDecimal sourceValue, BigDecimal targetValue) {
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
	public static boolean between(BigDecimal sourceValue, BigDecimal lowValue, BigDecimal highValue) {
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
	public static boolean notBetween(BigDecimal sourceValue, BigDecimal lowValue, BigDecimal highValue) {
		return !between(sourceValue, lowValue, highValue);
	}
	
	/**
	 * 比较
	 *
	 * @param sourceValue 源值
	 * @param targetValue 目标值
	 * @return 比较结果
	 */
	public static int compare(BigDecimal sourceValue, BigDecimal targetValue) {
		if (sourceValue == null && targetValue == null)
			return 0;
		if (sourceValue == null || targetValue == null)
			throw new IllegalArgumentException();
		return sourceValue.compareTo(targetValue);
	}
	
	/**
	 * 判断是否为零
	 *
	 * @param values 数值
	 * @return 是否为零
	 */
	public static boolean isZero(BigDecimal... values) {
		if (values == null || values.length == 0)
			throw new IllegalArgumentException();
		for (BigDecimal value : values)
			if (equals(value, BigDecimal.ZERO))
				return true;
		return false;
	}
	
	/**
	 * 判断是否不为零
	 *
	 * @param values 数值
	 * @return 是否不为零
	 */
	public static boolean isNotZero(BigDecimal... values) {
		return !isZero(values);
	}
	
	/**
	 * 判断是否可被整除
	 *
	 * @param dividend 被除数
	 * @param divisor  除数
	 * @return 是否可被整除
	 */
	public static boolean isDivisible(BigDecimal dividend, BigDecimal divisor) {
		if (dividend == null || divisor == null)
			return false;
		try {
			dividend.divide(divisor).intValueExact();
			return true;
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断是否不可被整除
	 *
	 * @param dividend 被除数
	 * @param divisor  除数
	 * @return 是否不可被整除
	 */
	public static boolean isNotDivisible(BigDecimal dividend, BigDecimal divisor) {
		return !isDivisible(dividend, divisor);
	}
	
	/**
	 * 计算百万分率
	 *
	 * @param numerator   分子
	 * @param denominator 分母
	 * @return 百万分率
	 */
	public static BigDecimal calculateMillionth(BigDecimal numerator, BigDecimal denominator) {
		if (numerator == null || denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0)
			return null;
		return numerator.divide(denominator, 6, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 计算求和
	 *
	 * @param augend 被加数
	 * @param addend 加数
	 * @return 求和
	 */
	public static BigDecimal calculateSummation(BigDecimal augend, BigDecimal addend) {
		if (augend == null || addend == null)
			return null;
		return augend.add(addend);
	}
	
	/**
     * 货币格式化
     * 
     * @param amount 金额
     * @param showSign 显示标志
     * @param showUnit 显示单位
     * @return 货币格式化
     */
    public static String currency(BigDecimal amount, boolean showSign, boolean showUnit) {
        if(amount == null)
            amount = BigDecimal.ZERO ; 
//        SecuritySetting setting = SettingUtils.get().getSecurity();
//        return setting.currency(amount, showSign, showUnit);
		return "fujue";
    }
}