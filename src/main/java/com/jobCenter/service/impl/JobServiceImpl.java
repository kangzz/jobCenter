package com.jobCenter.service.impl;
import com.jobCenter.domain.JobInfo;
import com.jobCenter.domain.JobLinkInfo;
import com.jobCenter.enums.IsType;
import com.jobCenter.mapper.JobInfoMapper;
import com.jobCenter.mapper.JobLinkInfoMapper;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobLinkInfoModel;
import com.jobCenter.service.IJobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "jobService")
@Transactional
public class JobServiceImpl implements IJobService {
	@Autowired
	private JobInfoMapper jobInfoMapper;
	@Autowired
	private JobLinkInfoMapper jobLinkInfoMapper;

	private final static Logger logger = Logger.getLogger(JobServiceImpl.class);
	/**
	 * 描述：获取所有的任务信息
	 * 作者 ：kangzz
	 * 日期 ：2016-03-18 00:48:44
	 */
	public List<JobInfoModel> getAllJobInfo() {

		List<JobInfoModel> jobInfoModes = new ArrayList<JobInfoModel>();

		try {

			logger.info("查询全部定时任务信息开始!");
			Long startTime = System.currentTimeMillis();
			//封装查询所有定时任务数据条件
			JobInfo jobInfo = new JobInfo();
			jobInfo.setIsDel(IsType.NO.getValue());
			jobInfo.setIsValid(IsType.YES.getValue());
			//查询所有定时任务数据
			List<JobInfo> jobInfolList = jobInfoMapper.selectByJobInfo(jobInfo);
			//遍历所有任务数据 进而查询每个任务下的链接请求信息
			if(jobInfolList!=null && !jobInfolList.isEmpty()){
				int size = jobInfolList.size();
				JobInfo jobInfoDb = null;
				//定义查询任务下请求信息的条件
				JobLinkInfo searchJobLinkInfo = new JobLinkInfo();
				for (int i = 0; i < size; i++) {
					//最终封装返回的数据
					JobInfoModel jobInfoModel = new JobInfoModel();

					jobInfoDb = jobInfolList.get(i);
					searchJobLinkInfo.setJobId(jobInfoDb.getJobId());
					searchJobLinkInfo.setIsDel(IsType.NO.getValue());
					searchJobLinkInfo.setIsValid(IsType.YES.getValue());
					List<JobLinkInfo> jobLinkInfoList =
							jobLinkInfoMapper.selectByJobLinkInfo(searchJobLinkInfo);
					if(jobLinkInfoList == null || jobLinkInfoList.isEmpty()){
						continue;
					}
					int linkSize = jobLinkInfoList.size();
					JobLinkInfo jobLinkInfoDb = null;
					List<JobLinkInfoModel> jobLinkInfoModels = new ArrayList<JobLinkInfoModel>();
					for (int j = 0; j < linkSize; j++) {
						JobLinkInfoModel jobLinkModel = new JobLinkInfoModel();
						jobLinkInfoDb = jobLinkInfoList.get(j);
						jobLinkModel.setJobId(jobLinkInfoDb.getJobId());
						jobLinkModel.setJobLink(jobLinkInfoDb.getJobLink());
						jobLinkModel.setJobLinkId(jobLinkInfoDb.getJobLinkId());
						jobLinkInfoModels.add(jobLinkModel);
					}
					jobInfoModel.setJobLinkInfoModels(jobLinkInfoModels);
					jobInfoModel.setJobName(jobInfoDb.getJobName());
					jobInfoModel.setJobId(jobInfoDb.getJobId());
					jobInfoModel.setJobExecuteRule(jobInfoDb.getJobExecuteRule());
					jobInfoModel.setJobExecuteType(jobInfoDb.getJobExecuteType());
					jobInfoModel.setJobNotifySucc(jobInfoDb.getJobNotifySucc());

					jobInfoModes.add(jobInfoModel);

				}
			}
			Long endTime = System.currentTimeMillis();
			logger.info("查询全部定时任务信息结束!");
			logger.info("耗时:"+(endTime - startTime));
		} catch (Exception e) {
			logger.error("查询任务集合信息为空!",e);
		}
		return jobInfoModes;
	}
}
