package com.jobCenter.web.manger;

/**
 * 描述：业务系统定时任务service需要继承的类
 * 作者 ：kangzz
 * 日期 ：2016-03-21 01:21:54
 */
public abstract class AbstractService implements Runnable  {
    private String uuid;
    private String jobId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
