package com.jobCenter.web.job.manager;

import com.jobCenter.enums.IsType;
import com.jobCenter.model.param.HeartBeatInfoParam;
import com.jobCenter.model.param.JobInfoSearchParam;
import com.jobCenter.service.HeartBeatService;
import com.jobCenter.service.JobInfoService;
import com.jobCenter.service.JobService;
import com.jobCenter.util.StringUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * 描述：定时任务报表
 * 作者 ：kangzz
 * 日期 ：2016-11-27 22:18:50
 */
@Controller
@RequestMapping(value="/job")
public class JobController {
	private final static Logger logger = Logger.getLogger(JobController.class);
	@Autowired
	private JobInfoService jobInfoService;
	@Autowired
	private HeartBeatService heartBeatService;


	/**
	 * 描述：定时任务整体情况
	 * 作者 ：kangzz
	 * 日期 ：2016-11-27 22:16:11
	 */
	@RequestMapping(value = "/jobList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView jobList(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("IsMap", IsType.lookup);
		return new ModelAndView("/job/manager/jobList", modelMap);
	}
	/**
	 * 描述：获取定时任务列表
	 * 作者 ：kangzz
	 * 日期 ：2016-11-28 22:58:16
	 */
	@ResponseBody
	@RequestMapping(value = "/queryJobList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> getProjectList(HttpServletRequest request,
			@Valid JobInfoSearchParam jobInfoSearchParam,
			Errors errors){
		if(errors.hasErrors()){
			String errorMessage=errors.getFieldError().getDefaultMessage();
			logger.info(errorMessage);
		}
		return jobInfoService.queryJobListByJobInfoSearchParam(jobInfoSearchParam);
	}

	/**
	 * 描述：查询心跳数据
	 * 作者 ：kangzz
	 * 日期 ：2016-11-30 21:10:23
	 */
	@RequestMapping(value = "/heartBeatList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView heartBeatList(HttpServletRequest request, ModelMap modelMap) {
		return new ModelAndView("/job/manager/heartBeatList", modelMap);
	}
	/**
	 * 描述：获取定时任务列表
	 * 作者 ：kangzz
	 * 日期 ：2016-11-28 22:58:16
	 */
	@ResponseBody
	@RequestMapping(value = "/queryHeartBeatList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> queryHeartBeatList(HttpServletRequest request, HeartBeatInfoParam heartBeatInfoParam){
		try{
			return heartBeatService.queryHeartBeatServiceToPage(heartBeatInfoParam);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
