package com.jobCenter.domain;

import java.util.Date;
/**
 * 描述：任务与报警人关系表
 * 作者 ：kangzz
 * 日期 ：2016-03-23 20:15:04
 */
public class JobWarningPersonRelation {
    private Long id;//id

    private Long jobId;//定时任务id

    private Long personId;//人员id

    private Integer personReceiveType;//当前任务报警人类型

    private Integer isDel;//是否删除

    private String createId;//创建人

    private Date createTime;//创建时间

    private String updateId;//修改人

    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getPersonReceiveType() {
        return personReceiveType;
    }

    public void setPersonReceiveType(Integer personReceiveType) {
        this.personReceiveType = personReceiveType;
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

    @Override
    public String toString() {
        return "JobWarningPersonRelation{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", personId=" + personId +
                ", personReceiveType=" + personReceiveType +
                ", isDel=" + isDel +
                ", createId='" + createId + '\'' +
                ", createTime=" + createTime +
                ", updateId='" + updateId + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}