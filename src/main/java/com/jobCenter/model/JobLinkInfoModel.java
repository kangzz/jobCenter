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
    private String jobLinkId;//请求路径id
    private String jobId;//任务ID
    private String jobLink;//任务链接

    public String getJobLinkId() {
        return jobLinkId;
    }

    public void setJobLinkId(String jobLinkId) {
        this.jobLinkId = jobLinkId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }
}