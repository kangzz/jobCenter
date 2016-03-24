package com.jobCenter.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 描述：定时任务关联请求路径信息实体
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:37:58
 */
public class JobLinkInfoModel implements Serializable {

    private static final long serialVersionUID = 5839382299698006071L;
    private Integer jobLinkId;//请求路径id
    private Integer jobId;//任务ID
    private String jobLink;//任务链接
    private String serviceName;//任务Service名称

    public Integer getJobLinkId() {
        return jobLinkId;
    }

    public void setJobLinkId(Integer jobLinkId) {
        this.jobLinkId = jobLinkId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "JobLinkInfoModel{" +
                "jobLinkId=" + jobLinkId +
                ", jobId=" + jobId +
                ", jobLink='" + jobLink + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}