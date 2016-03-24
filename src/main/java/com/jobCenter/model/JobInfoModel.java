package com.jobCenter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JobInfoModel implements Serializable {

    private static final long serialVersionUID = -5138174371495651441L;
    private Integer jobId;//定时任务信息主键

    private String jobName;//定时任务名称

    private Integer jobExecuteType;//'定时任务执行类型。0 只执行一台 1 全部执行'

    private String jobExecuteRule;//'执行规则'

    private Integer jobNotifySucc;//'是否需要通知成功 1 是 0 否'

    private Integer jobRetryTimes;//重试次数

    private Date jobStartTime;//'任务生效时间'

    private Date jobEndTime;//'任务失效时间'

    private List<JobLinkInfoModel> jobLinkInfoModels;//链接集合

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobExecuteType() {
        return jobExecuteType;
    }

    public void setJobExecuteType(Integer jobExecuteType) {
        this.jobExecuteType = jobExecuteType;
    }

    public String getJobExecuteRule() {
        return jobExecuteRule;
    }

    public void setJobExecuteRule(String jobExecuteRule) {
        this.jobExecuteRule = jobExecuteRule;
    }

    public Integer getJobNotifySucc() {
        return jobNotifySucc;
    }

    public void setJobNotifySucc(Integer jobNotifySucc) {
        this.jobNotifySucc = jobNotifySucc;
    }

    public Integer getJobRetryTimes() {
        return jobRetryTimes;
    }

    public void setJobRetryTimes(Integer jobRetryTimes) {
        this.jobRetryTimes = jobRetryTimes;
    }

    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public List<JobLinkInfoModel> getJobLinkInfoModels() {
        return jobLinkInfoModels;
    }

    public void setJobLinkInfoModels(List<JobLinkInfoModel> jobLinkInfoModels) {
        this.jobLinkInfoModels = jobLinkInfoModels;
    }

    @Override
    public String toString() {
        return "JobInfoModel{" +
                "jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", jobExecuteType=" + jobExecuteType +
                ", jobExecuteRule='" + jobExecuteRule + '\'' +
                ", jobNotifySucc=" + jobNotifySucc +
                ", jobRetryTimes=" + jobRetryTimes +
                ", jobStartTime=" + jobStartTime +
                ", jobEndTime=" + jobEndTime +
                ", jobLinkInfoModels=" + jobLinkInfoModels +
                '}';
    }
}