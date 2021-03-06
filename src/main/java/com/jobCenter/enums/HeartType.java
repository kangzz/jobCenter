package com.jobCenter.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：心跳类型枚举
 * 作者 ：kangzz
 * 日期 ：2016-03-18 00:53:56
 */
public enum HeartType {
	JOB_CENTER("定时任务", "jobCenter");
	// 成员变量
	private String name;
	private String value;

	private HeartType(String name, String value) {
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
        for (HeartType s : EnumSet.allOf(HeartType.class)) {
            lookup.put(s.getValue(), s.getName());
        }  
    }
}
