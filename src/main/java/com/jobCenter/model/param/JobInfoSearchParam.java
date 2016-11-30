package com.jobCenter.model.param;

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
}