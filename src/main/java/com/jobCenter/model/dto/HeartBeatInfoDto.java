package com.jobCenter.model.dto;

import com.jobCenter.util.StringUtil;
import com.kangzz.mtool.date.DateUtil;

import java.util.Date;

/**
 * 描述：心跳信息实体
 * 作者 ：kangzz
 * 日期 ：2016-03-21 20:21:27
 */
public class HeartBeatInfoDto {
    private Integer id;//心跳id

    private String masterIdentity;//心跳主机标志

    private String heartType;//心跳类型

    private String heartBeatTime;//最后修改时间

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
        if(StringUtil.isBlank(masterIdentity)){
            masterIdentity = null;
        }
        this.masterIdentity = masterIdentity;
    }

    public String getHeartType() {
        return heartType;
    }

    public void setHeartType(String heartType) {
        if(StringUtil.isBlank(heartType)){
            heartType = null;
        }
        this.heartType = heartType;
    }

    public String getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(Date heartBeatTime) {
        if (heartBeatTime != null) {
            this.heartBeatTime = DateUtil.format(heartBeatTime, DateUtil.NORM_DATETIME_PATTERN);
        } else {
            this.heartBeatTime = null;
        }
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

    @Override
    public String toString() {
        return "HeartBeatInfo{" +
                "id=" + id +
                ", masterIdentity='" + masterIdentity + '\'' +
                ", heartType='" + heartType + '\'' +
                ", heartBeatTime=" + heartBeatTime +
                ", heartMaxVal=" + heartMaxVal +
                ", isDel=" + isDel +
                '}';
    }
}