package com.jobCenter.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：定时任务状态
 * 作者 ：kangzz
 * 日期 ：2016-03-23 00:28:06
 */
public enum JobStatus {
	JYSB("校验失败!",-2),
	TZCG("通知成功!", 0),
	TZSB("通知失败!",1),
	ZZZX("正在执行,返回!",2),
	ZXCG("执行成功!",3),
	ZXSB("执行失败!",4);
	// 成员变量
	private String name;
	private Integer value;

	private JobStatus(String name, Integer value) {
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
