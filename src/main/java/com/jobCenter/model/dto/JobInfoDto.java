package com.jobCenter.model.dto;

import com.jobCenter.enums.IsType;
import com.kangzz.mtool.date.DateUtil;

import java.util.Date;

/**
 * Created by kangzz on 16/11/29.
 */
public class JobInfoDto{

    private Integer jobId;//定时任务信息主键

    private String jobName;//定时任务名称

    private String jobSystem;//定时任务归属系统

    private Integer jobExecuteType;//'定时任务执行类型。0 只执行一台 1 全部执行'

    private String jobExecuteRule;//'执行规则'

    private Integer jobNotifySucc;//'是否需要通知成功 1 是 0 否'

    private Integer jobRetryTimes;//定时任务重试次数

    private String jobStartTime;//'任务生效时间'

    private String jobEndTime;//'任务失效时间'

    private String isValid;//'是否生效 1是 0 否'

    private Integer isDel;//'是否删除 1 是 0 否'

    private String createId;//'创建人'

    private String createTime;//'创建时间'

    private String updateId;//'修改人'

    private String updateTime;//'修改时间'

    private String nextFireTime;//下次执行时间
    private String previousFireTime;//上次执行时间
    private String executeType;//运行状态

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

    public String getJobSystem() {
        return jobSystem;
    }

    public void setJobSystem(String jobSystem) {
        this.jobSystem = jobSystem;
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

    public String getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        if (jobStartTime != null) {
            this.jobStartTime = DateUtil.format(jobStartTime,DateUtil.NORM_DATETIME_PATTERN);
        }else {
            this.jobStartTime = null;
        }
    }
    public String getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        if (jobEndTime != null) {
            this.jobEndTime = DateUtil.format(jobEndTime, DateUtil.NORM_DATETIME_PATTERN);
        }else {
            this.jobEndTime = null;
        }
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid != null && isValid == IsType.YES.getValue() ? IsType.YES.getName() : IsType.NO.getName();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            this.createTime = DateUtil.format(createTime,DateUtil.NORM_DATETIME_PATTERN);
        }
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        if (updateTime != null) {
            this.updateTime = DateUtil.format(updateTime,DateUtil.NORM_DATETIME_PATTERN);
        }
    }

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
