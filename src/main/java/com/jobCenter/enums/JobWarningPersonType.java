package com.jobCenter.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：高警人员类型
 * 作者 ：kangzz
 * 日期 ：2016-03-23 00:28:06
 */
public enum JobWarningPersonType {
	ZYRY("主要人员!", 0),
	CYRY("次要人员!",1);
	// 成员变量
	private String name;
	private Integer value;

	private JobWarningPersonType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * enum lookup map
	 */
	public static final Map<Integer, String> lookup = new HashMap<Integer, String>();

	static {
		for (IsType s : EnumSet.allOf(IsType.class)) {
			lookup.put(s.getValue(), s.getName());
		}
	}
}
