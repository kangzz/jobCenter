package com.jobCenter.service;


import com.jobCenter.model.param.HeartBeatInfoParam;

import java.util.Map;

/**
 * 描述：心跳数据检查
 * 作者 ：kangzz
 * 日期 ：2016-11-30 21:13:30
 */
public interface HeartBeatService {
    /**
     * 描述：查询心跳数据
     * 作者 ：kangzz
     * 日期 ：2016-11-30 21:14:13
     */
    Map<String,Object> queryHeartBeatServiceToPage(HeartBeatInfoParam heartBeatInfoParam);
}

