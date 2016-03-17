package com.jobCenter.service;


import com.jobCenter.model.JobInfoModel;

import java.util.List;

/**
 * 描述：操作任务信息接口
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:42:45
 */
public interface IJobService {
	//获取所有的定时任务信息
	public List<JobInfoModel> getAllJobInfo();
}
