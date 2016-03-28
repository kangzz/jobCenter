package com.jobCenter.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：定时任务链接或回调
 * 作者 ：kangzz
 * 日期 ：2016-03-23 00:28:06
 */
public enum DoneStatus {
	TZCG("通知成功","connectSuccess"),
	UNDONE("正在执行,不需要执行","unNeedDone"),
	TZSB("通知失败", "connectFail"),
	ZXCG("执行成功","doneSuccess"),
	ZXSB("执行失败","doneFail");
	// 成员变量
	private String name;
	private String value;

	private DoneStatus(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * enum lookup map
	 */
	public static final Map<String, String> lookup = new HashMap<String, String>();

	static {
		for (DoneStatus s : EnumSet.allOf(DoneStatus.class)) {
			lookup.put(s.getValue(), s.getName());
		}
	}
}
