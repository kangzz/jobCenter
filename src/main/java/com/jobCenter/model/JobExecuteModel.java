package com.jobCenter.model;

import java.io.Serializable;

public class JobExecuteModel implements Serializable {

    private static final long serialVersionUID = -4620230540801143680L;
    private String nextFireTime;//下次执行时间
    private String previousFireTime;//上次执行时间
    private String executeType;//运行状态

    public String getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(String nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(String previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }
}