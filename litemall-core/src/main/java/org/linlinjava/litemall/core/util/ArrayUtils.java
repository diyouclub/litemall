/*
 * Copyright 2010-2015 icl-network.com. All rights reserved.
 * Support: http://www.icl-network.com
 */
package org.linlinjava.litemall.core.util;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Utils - 数组
 *
 * @author ICLNetwork Team
 * @version 5.0
 */
public final class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {
	
	private ArrayUtils() {
	}
	
	/**
	 * 复制列表为数组
	 *
	 * @param list 列表
	 * @return 数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copyOf(List<T> list) {
		if (list == null || list.isEmpty())
			return null;
		return (T[]) list.toArray();
	}
	
	/**
	 * 复制列表为数组
	 *
	 * @param type 类型
	 * @param list 列表
	 * @return 数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copyOf(Class<T> type, List<T> list) {
		if (type == null || list == null || list.isEmpty())
			return null;
		T[] array = (T[]) Array.newInstance(type, list.size());
		for (int i = 0; i < list.size(); i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * 获取长度
	 *
	 * @param array 数组
	 * @return 长度
	 */
	public static int length(Object[] array) {
		if (array == null)
			return 0;
		return array.length;
	}
	
	/**
	 * 比较长度"高低位"
	 *
	 * @param array      数组
	 * @param lowLength  低位长度
	 * @param highLength 高位长度
	 * @return 比较结果
	 */
	public static boolean compareLengthBetween(Object[] array, Integer lowLength, Integer highLength) {
		if (array == null && lowLength == null && highLength == null)
			return true;
		if (array == null || array.length == 0 || lowLength == null || highLength == null)
			return false;
		return IntegerUtils.between(array.length, lowLength, highLength);
	}
	
	/**
	 * 分割子数组
	 *
	 * @param list  列表
	 * @param start 开始位置
	 * @param end   结束位置
	 * @return 数组
	 */
	public static Object[] subarray(List<?> list, int start, int end) {
		if (list == null || list.size() < (start + 1) || start < 0 || end < 0 || end < start)
			return null;
		if (list.size() < (end + 1))
			end = list.size() - 1;
		Object[] array = new Object[end - start + 1];
		for (int i = 0; i < end; i++)
			array[i] = list.get(i + start);
		return array;
	}
	
	/**
	 * 分割子数组
	 *
	 * @param type  类型
	 * @param list  列表
	 * @param start 开始位置
	 * @param end   结束位置
	 * @return 数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] subarray(Class<T> type, List<T> list, int start, int end) {
		if (type == null || list == null || list.size() < (start + 1) || start < 0 || end < 0 || end < start)
			return null;
		if (list.size() < (end + 1))
			end = list.size() - 1;
		T[] array = (T[]) Array.newInstance(type, end - start + 1);
		for (int i = 0; i < end; i++)
			array[i] = list.get(i + start);
		return array;
	}
	
	/**
	 * 分割子数组
	 *
	 * @param list  列表
	 * @param start 开始位置
	 * @return 数组
	 */
	public static Object[] subarray(List<?> list, int start) {
		if (list == null || list.size() < (start + 1) || start < 0)
			return null;
		return subarray(list, start, list.size() - 1);
	}
	
	/**
	 * 分割子数组
	 *
	 * @param type  类型
	 * @param list  列表
	 * @param start 开始位置
	 * @return 数组
	 */
	public static <T> T[] subarray(Class<T> type, List<T> list, int start) {
		if (type == null || list == null || list.size() < (start + 1) || start < 0)
			return null;
		return subarray(type, list, start, list.size() - 1);
	}
	
}