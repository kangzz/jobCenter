package com.jobCenter.domain;

import java.util.Date;

/**
 * 描述：心跳信息实体
 * 作者 ：kangzz
 * 日期 ：2016-03-21 20:21:27
 */
public class HeartBeatInfo {
    private Integer id;//心跳id

    private String masterIdentity;//心跳主机标志

    private String heartType;//心跳类型

    private Date heartBeatTime;//最后修改时间

    private Integer heartMaxVal;//最大间隔时间

    private Integer isDel;//是否删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterIdentity() {
        return masterIdentity;
    }

    public void setMasterIdentity(String masterIdentity) {
        this.masterIdentity = masterIdentity == null ? null : masterIdentity.trim();
    }

    public String getHeartType() {
        return heartType;
    }

    public void setHeartType(String heartType) {
        this.heartType = heartType == null ? null : heartType.trim();
    }

    public Date getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(Date heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }

    public Integer getHeartMaxVal() {
        return heartMaxVal;
    }

    public void setHeartMaxVal(Integer heartMaxVal) {
        this.heartMaxVal = heartMaxVal;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}