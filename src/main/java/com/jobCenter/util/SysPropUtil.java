package com.jobCenter.util;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 描述：获取系统资源配置信息
 * 作者 ：kangzz
 * 日期 ：2016-03-23 16:41:20
 */
public class SysPropUtil {
	
	private static Properties systemConstants;// 系统变量存放区
	private static Properties errorCodeConstants;//错误编码
	
	private static final Logger LOGGER = Logger.getLogger(SysPropUtil.class);
	
	private SysPropUtil() {
	}

	/**
	 * 获取系统变量(默认为system.properties)
	 * @param constantName 属性名称
	 * @return
	 */
	public static String getSystemConstant(String constantName) {
		return getSystemConstant("system.properties", constantName);
	}
	public static String getErrorMessageConstant(String constantName) {
		return getErrorMessageConstant("error_code_msg_zh.properties", constantName);
	}
	/**
	 * 获取系统变量
	 * @param propertyName properties的名称
	 * @param constantName 属性名称
	 * @return value
	 */
	private static String getSystemConstant(String propertyName, String constantName) {
		String property = null;
		if (null == systemConstants) {
			initSystemProperties(propertyName);
		}
		property = systemConstants.getProperty(constantName);
		Assert.notNull(property, "系统变量" + constantName + "不存在");
		return property;
	}
	/**
	 * 获取系统变量
	 * @param propertyName properties的名称
	 * @param constantName 属性名称
	 * @return value
	 */
	private static String getErrorMessageConstant(String propertyName, String constantName) {
		String property = null;
		if (null == errorCodeConstants) {
			initErrorMessageProperties(propertyName);
		}
		property = errorCodeConstants.getProperty(constantName);
		Assert.notNull(property, "系统变量" + constantName + "不存在");
		return property;
	}
	
	/**
	 * 初始化系统属性文件
	 */
	private static void initSystemProperties(String propertyName) {
		systemConstants = new Properties();
		ClassLoader cl = SysPropUtil.class.getClassLoader();
		// 加载数据字典分类初始化文件
		InputStream stream = cl.getResourceAsStream(propertyName);
		try {
			systemConstants.load(stream);
		} catch (IOException e) {
			LOGGER.error("系统文件加载失败:", e);
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					LOGGER.error("资源关闭失败:", e);
				}
			}
		}
	}
	/**
	 * 初始化系统属性文件
	 */
	private static void initErrorMessageProperties(String propertyName) {
		errorCodeConstants = new Properties();
		ClassLoader cl = SysPropUtil.class.getClassLoader();
		// 加载数据字典分类初始化文件
		InputStream stream = cl.getResourceAsStream(propertyName);
		try {
			errorCodeConstants.load(stream);
		} catch (IOException e) {
			LOGGER.error("系统文件加载失败:", e);
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					LOGGER.error("资源关闭失败:", e);
				}
			}
		}
	}
}
