package com.jobCenter.web.manger;

import com.alibaba.fastjson.JSONObject;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.enums.JobStatus;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.service.JobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 描述：定时任务服务器提供给业务系统的回调方法
 * 作者 ：kangzz
 * 日期 ：2016-03-22 23:23:44
 */
@Controller
@RequestMapping(value="/callBackToJobCenter")
public class CallBackController {

	private static final Logger logger = Logger.getLogger(CallBackController.class);
	@Autowired
	private JobService jobService;
	/**
	 * 描述：业务系统回调方法 告知定时任务服务器任务执行结果
	 * 作者 ：kangzz
	 * 日期 ：2016-03-22 23:22:12
	 */
	@RequestMapping(value="callBack.do",method={RequestMethod.GET,RequestMethod.POST})
	public void callBack(HttpServletRequest request, HttpServletResponse response) {
		try {
			String uuid = request.getParameter("uuid");
			String jobId = request.getParameter("jobId");
			String jobName = request.getParameter("jobName");
			String status = request.getParameter("status");
			String code = request.getParameter("code");
			String message = request.getParameter("message");
			JobExecuteResult record = new JobExecuteResult();
			record.setJobUuid(uuid);
			record.setJobEndTime(new Date());
			record.setResultCode(Integer.valueOf(code));
			record.setResultStatus(status);
			record.setResultMessage(message);
			jobService.updateJobExecuteResultByUuid(record);
			//如果执行失败 那么报警
			if(JobStatus.ZXCG.getValue() == Integer.valueOf(code)){
				JobWarningModel jobWarningModel = new JobWarningModel();
				jobWarningModel.setJobId(Long.valueOf(jobId));
				jobWarningModel.setWarningTitle("定时任务["+jobName+"]执行失败");
				jobWarningModel.setWarningContent(jobName+"..jobId:"+"["+jobId+"],失败原因:"+message+"</br>" +
						"UUID:"+uuid);
				jobService.notifyJobOwner(jobWarningModel);
			}
		}catch (Exception e){
			logger.error("回调异常,",e);
		}
		printResult(response, "success", 0, "成功");
	}
	//封装返回数据
	private synchronized void  printResult(HttpServletResponse response , String status,int code,String message) {
		try {
			JSONObject errorJson = new JSONObject();
			errorJson.put("status", status);
			errorJson.put("code", code);
			errorJson.put("message", message);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONObject.toJSONString(errorJson));
		} catch (IOException e) {
			logger.error("封装回调返回值错误",e);
		}
	}
}
