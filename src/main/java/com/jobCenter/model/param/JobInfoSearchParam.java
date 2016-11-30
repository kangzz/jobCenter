package com.jobCenter.model.param;

import com.jobCenter.util.DateUtil;
import com.jobCenter.util.StringUtil;

/**
 * 描述：定时任务主信息
 * 作者 ：kangzz
 * 日期 ：2016-03-19 21:57:09
 */
public class JobInfoSearchParam extends PageParam {
    //@NotNull(message = "定时任务名称不能为空")
    private String jobName;//定时任务名称

    private String jobSystem;//定时任务归属系统

    private Integer isValid;//'是否生效 1是 0 否'

    private String startCreateTime;//创建开始日期
    private String endCreateTime;//创建结束日期

    private String jobStartTime;//任务开始日期
    private String jobEndTime;//任务结束日期


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        if(StringUtil.isBlank(jobName)){
            jobName = null;
        }
        this.jobName = jobName;
    }

    public String getJobSystem() {
        return jobSystem;
    }

    public void setJobSystem(String jobSystem) {
        if(StringUtil.isBlank(jobSystem)){
            jobSystem = null;
        }
        this.jobSystem = jobSystem;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        if(StringUtil.isBlank(startCreateTime)){
            startCreateTime = null;
        }
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }
    public void setEndCreateTime(String endCreateTime) {
        if(!StringUtil.isBlank(endCreateTime)){
            endCreateTime = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseDate(endCreateTime),1),DateUtil.DATE_PATTERN_LINE);
        }else{
            endCreateTime = null;
        }
        this.endCreateTime = endCreateTime;
    }

    public String getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(String jobStartTime) {
        if(StringUtil.isBlank(jobStartTime)){
            jobStartTime = null;
        }
        this.jobStartTime = jobStartTime;
    }

    public String getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(String jobEndTime) {
        if(!StringUtil.isBlank(jobEndTime)){
            jobEndTime = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseDate(jobEndTime),1),DateUtil.DATE_PATTERN_LINE);
        }else{
            jobEndTime = null;
        }
        this.jobEndTime = jobEndTime;
    }
}