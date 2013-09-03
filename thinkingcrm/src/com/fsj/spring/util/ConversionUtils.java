package com.fsj.spring.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 	转换工具类
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 * www.9tang.info
 */
public class ConversionUtils {
	public static final short GOAL = 1;

	public static final short SOURCE = 2;

	private static final String GET = "get", SET = "set";

	/**
	 * 复制pojo属性值
	 * 
	 * @param goal
	 * @param source
	 * @param type
	 * @throws Exception
	 */

	public static void convertAttribute(Object goal, Object source, short type) throws Exception {

		String methodName = null;
		Method goalMethod = null;
		Method sourceMethod = null;
		Object result = null;
		Class<?> returnClass = null;

		Class goalClass = goal.getClass();
		Class sourceClass = source.getClass();

		Method[] methods = null;
		switch (type) {
		case GOAL:
			methods = goalClass.getMethods();
			break;
		case SOURCE:
		default:
			methods = sourceClass.getMethods();
			break;
		}

		for (int i = 0; i < methods.length; i++) {
			methodName = methods[i].getName();
			if (methodName.startsWith(SET)) {
				sourceMethod = null;
				result = null;

				try {
					sourceMethod = sourceClass.getMethod(GET + methodName.substring(3), null);
					result = sourceMethod.invoke(source, null);
					returnClass = sourceMethod.getReturnType();
				} catch (RuntimeException e) {

					e.printStackTrace();
				}

				if (result == null && (returnClass.equals(Date.class) || returnClass.equals(String.class) || methodName.equals("setCrtC") || methodName
								.equals("setCrtDate")))
					continue;

				if (result != null) {
					goalMethod = goalClass.getMethod(methodName, convertToNeedType(new Class[] { result.getClass() }));
				} else {
					goalMethod = goalClass.getMethod(methodName, new Class[] { returnClass });
				}
				goalMethod.invoke(goal, new Object[] { result });

			}
		}
	}

	public static Class[] convertToNeedType(Class[] argsType) {
		if (argsType == null)
			return null;

		int needLength = argsType.length;
		Class[] convertType = new Class[needLength];

		for (int i = 0; i < needLength; i++) {
			if (argsType[i].equals(Boolean.class))
				convertType[i] = boolean.class;
			else if (argsType[i].equals(Byte.class))
				convertType[i] = byte.class;
			else if (argsType[i].equals(Double.class))
				convertType[i] = Double.class;
			else if (argsType[i].equals(Integer.class))
				convertType[i] = Integer.class;
			else if (argsType[i].equals(Short.class))
				convertType[i] = short.class;
			else if (argsType[i].equals(Float.class))
				convertType[i] = float.class;
			else if (argsType[i].equals(HashSet.class))
				convertType[i] = Set.class;
			else
				convertType[i] = argsType[i];
		}

		return convertType;
	}
	/**
	 * 转换dataTables表格参数为Map
	 * @param params 参数数组
	 * @return	Map
	 */
	public static Map<String,Object> convertToMap(DataTableParam[] params){
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (DataTableParam param : params) {
			map.put(param.getName(), param.getValue());
		}
		return Utils.mapSortedByKey(map);
	}
}
