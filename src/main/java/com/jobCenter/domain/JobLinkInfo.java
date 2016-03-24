package com.jobCenter.domain;

import java.util.Date;

/**
 * 描述：定时任务关联请求路径信息实体
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:37:58
 */
public class JobLinkInfo {
    private Integer jobLinkId;//请求路径id

    private Integer jobId;//任务ID

    private String jobLink;//任务链接 为的是支持一个任务多台机器执行

    private String serviceName;//定时任务Service名称

    private Integer isValid;//是否有效 1是 0否

    private Integer isDel;//是否删除 1是 0否

    private String createId;//创建人

    private Date createTime;//创建时间

    private String updateId;//修改人

    private Date updateTime;//修改时间

    public Integer getJobLinkId() {
        return jobLinkId;
    }

    public void setJobLinkId(Integer jobLinkId) {
        this.jobLinkId = jobLinkId == null ? null : jobLinkId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId == null ? null : jobId;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink == null ? null : jobLink.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "JobLinkInfo{" +
                "jobLinkId=" + jobLinkId +
                ", jobId=" + jobId +
                ", jobLink='" + jobLink + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", isValid=" + isValid +
                ", isDel=" + isDel +
                ", createId='" + createId + '\'' +
                ", createTime=" + createTime +
                ", updateId='" + updateId + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}