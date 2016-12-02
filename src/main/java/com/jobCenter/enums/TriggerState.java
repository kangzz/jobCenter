package com.jobCenter.enums;

import org.quartz.Trigger;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者 ：kangzz
 * 日期 ：2016-03-18 00:53:56
 */
public enum TriggerState {
	/*None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除
	NORMAL:正常状态
	PAUSED：暂停状态
	COMPLETE：触发器完成，但是任务可能还正在执行中
	BLOCKED：线程阻塞状态
	ERROR：出现错误*/

	NONE("无触发器", Trigger.STATE_NONE),
	NORMAL("正常状态", Trigger.STATE_NORMAL),
	PAUSED("暂停状态", Trigger.STATE_PAUSED),
	COMPLETE("正在执行", Trigger.STATE_COMPLETE),
	BLOCKED("线程阻塞", Trigger.STATE_BLOCKED),
	ERROR("异常", Trigger.STATE_ERROR);
	// 成员变量
	private String name;
	private Integer value;

	private TriggerState(String name, Integer value) {
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
        for (TriggerState s : EnumSet.allOf(TriggerState.class)) {
            lookup.put(s.getValue(), s.getName());
        }  
    }
}
