package com.jobCenter.util.client;

import com.alibaba.fastjson.JSONObject;
import com.jobCenter.util.MD5Util;
import com.jobCenter.util.SpringTool;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JobCenterCommonController {

	private static final Logger logger = Logger.getLogger(JobCenterCommonController.class);
	//设置当前任务是否已经在执行
	private static Map<String, Thread> cache = new HashMap<String,Thread>();
	/**
	 * 描述：执行任务
	 * 作者 ：kangzz
	 * 日期 ：2016-03-22 22:40:25
	 */
	public synchronized void notify(HttpServletRequest request, HttpServletResponse response) {
		String serviceName = request.getParameter("serviceName");
		String uuid = request.getParameter("uuid");
		String jobId = request.getParameter("jobId");
		String securityCode = request.getParameter("securityCode");
		String linkId = request.getParameter("linkId");
		Thread.currentThread().setName(uuid);
		//安全码校验
		if(!checkIsValidVisit(uuid,linkId,securityCode)){
			logger.info("安全码校验失败!");
			printResult(response,uuid,"fail",2,"安全码校验失败");
			return;
		}else{
			logger.info("安全码校验成功!");
		}
		//校验房前serviceName是否有县城正在执行 如果正在执行 那么返回错误
		if(cache.containsKey(serviceName) && cache.get(serviceName).isAlive()) {
			printResult(response,uuid,"fail",-100,"当前任务正在执行!");
			return;
		}
		AbstractService abstractService = (AbstractService) SpringTool.getBean(serviceName);
		abstractService.setJobId(jobId);
		abstractService.setUuid(uuid);
		Thread t = new Thread(abstractService);
		t.setName(uuid);
		cache.put(serviceName,t);
		t.start();
		printResult(response,uuid,"success",0,"成功");
	}

	//封装返回数据
	private void  printResult(HttpServletResponse response,String uuid, String status,int code,String message) {
		try {
			JSONObject errorJson = new JSONObject();
			errorJson.put("uuid",uuid);
			errorJson.put("status", status);
			errorJson.put("code", code);
			errorJson.put("message", message);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONObject.toJSONString(errorJson));
		} catch (IOException e) {
			logger.error("调用成功,返回数据异常!",e);
		}
	}

	/**
	 * 描述：校验安全码 是否是有效调用
	 * 作者 ：kangzz
	 * 日期 ：2016-03-21 13:10:25
	 */
	private Boolean checkIsValidVisit(String uuid, String jobLinkId, String securityCode){
		//jobCenter的加密规则是uuid和linkId各取一半后进行md5
		int jobLinkIdSubCount = jobLinkId.length() / ClientSystemConstant.MD5_RATIO;
		jobLinkIdSubCount = jobLinkIdSubCount == 0 ? jobLinkId.length() : jobLinkIdSubCount;
		int uuidSubCount = uuid.length() / ClientSystemConstant.MD5_RATIO;
		uuidSubCount = uuidSubCount == 0 ? uuid.length() : uuidSubCount;
		//业务系统根据加密规则生成的安全码
		String securityStr = jobLinkId.substring(0, jobLinkIdSubCount) + uuid.substring(0, uuidSubCount);
		return securityCode.equals(MD5Util.encodeMD5(securityStr,ClientSystemConstant.MD5_KEY));
	}

}
