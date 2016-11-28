package com.jobCenter.web.job.manager;

import com.jobCenter.model.JobInfoSearchParam;
import com.jobCenter.service.JobInfoService;
import com.jobCenter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：定时任务报表
 * 作者 ：kangzz
 * 日期 ：2016-11-27 22:18:50
 */
@Controller
@RequestMapping(value="/job")
public class JobController {
	@Autowired
	private JobInfoService jobInfoService;
	/**
	 * 描述：定时任务整体情况
	 * 作者 ：kangzz
	 * 日期 ：2016-11-27 22:16:11
	 */
	@RequestMapping(value = "jobList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView jobList(JobInfoSearchParam jobInfoSearchParam, HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("list",jobInfoService.queryJobListByJobInfoSearchParam(jobInfoSearchParam));
		return new ModelAndView("/job/manager/jobList", modelMap);
	}
}
