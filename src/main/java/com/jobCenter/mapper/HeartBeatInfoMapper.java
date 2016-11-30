package com.jobCenter.mapper;

import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.model.dto.HeartBeatInfoDto;
import com.jobCenter.model.param.HeartBeatInfoParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartBeatInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HeartBeatInfo record);

    int insertSelective(HeartBeatInfo record);

    HeartBeatInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeartBeatInfo record);

    int updateByPrimaryKey(HeartBeatInfo record);

    /**
     * 描述：查询心跳数据
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:35:12
     */
    List<HeartBeatInfo> selectByRecord(HeartBeatInfo record);
    /**
     * 描述：根据主机标志修改最后修改时间
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:15:12
     */
    int updateByMasterIdentity(HeartBeatInfo record);

    /**
     * 描述：检测主机是否已失效 本机是否可以接管 超时修改当前机器为主机
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:17:04
     */
    int updateByOutTime(HeartBeatInfo record);

    List<HeartBeatInfoDto> queryHeartBeatServiceToPage(HeartBeatInfoParam heartBeatInfoParam);
    long queryTotalHeartBeatServiceToPage(HeartBeatInfoParam heartBeatInfoParam);
}