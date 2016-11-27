package com.jobCenter.web.job.report;

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
@RequestMapping(value="/jobReport")
public class JobReportController{
	/**
	 * 描述：定时任务整体情况
	 * 作者 ：kangzz
	 * 日期 ：2016-11-27 22:16:11
	 */
	@RequestMapping(value = "queryJobTotalInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryJobTotalInfo(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("list",null);
		return new ModelAndView("/job/report/jobTotalInfo", modelMap);
	}
}
