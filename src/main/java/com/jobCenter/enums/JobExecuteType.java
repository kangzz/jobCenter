package com.jobCenter.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：执行类型枚举
 * 作者 ：kangzz
 * 日期 ：2016-03-18 00:53:56
 */
public enum JobExecuteType {
	ALL("全部执行", 1), ONE("执行其中一台", 0);
	// 成员变量
	private String name;
	private Integer value;

	private JobExecuteType(String name, Integer value) {
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
        for (JobExecuteType s : EnumSet.allOf(JobExecuteType.class)) {
            lookup.put(s.getValue(), s.getName());
        }  
    }
}
