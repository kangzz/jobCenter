package com.jobCenter.service.impl;

import com.jobCenter.mapper.HeartBeatInfoMapper;
import com.jobCenter.model.dto.HeartBeatInfoDto;
import com.jobCenter.model.param.HeartBeatInfoParam;
import com.jobCenter.service.HeartBeatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(value = "heartBeatService")
@Transactional
public class HeartBeatServiceImpl implements HeartBeatService {

    @Autowired
    private HeartBeatInfoMapper heartBeatInfoMapper;
    private final static Logger logger = Logger.getLogger(HeartBeatServiceImpl.class);
    /**
     * 描述：查询心跳数据
     * 作者 ：kangzz
     * 日期 ：2016-11-30 21:14:13
     */
    public Map<String,Object> queryHeartBeatServiceToPage(HeartBeatInfoParam heartBeatInfoParam){
        List<HeartBeatInfoDto> list = heartBeatInfoMapper.queryHeartBeatServiceToPage(heartBeatInfoParam);
        Map<String, Object> result = new HashMap<String, Object>();
        long totalCount = heartBeatInfoMapper.queryTotalHeartBeatServiceToPage(heartBeatInfoParam);
        result.put("rows", list);
        result.put("total", totalCount);
        return result;
    }

}
