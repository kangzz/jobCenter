package com.jobCenter.interceptor;

import com.jobCenter.comm.CommonException;
import com.jobCenter.comm.NeedWarningException;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.service.JobService;
import com.jobCenter.util.NotifyWarningUtil;
import com.jobCenter.util.SpringTool;
import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 描述：异常统一处理
 * 作者 ：kangzz
 * 日期 ：2016-03-24 09:42:01
 */
public class ExceptionLog implements ThrowsAdvice {

    private static final Logger logger = Logger.getLogger(ExceptionLog.class);
    //JobService jobService = (JobService) SpringTool.getBean("jobService");
    /**
     * 描述：
     * @param method 执行的方法
     * @param args 方法参数
     * @param target 代理的目标对象
     * @param throwable 产生的异常
     * @return
     * 作者 ：kangzz
     * 日期 ：2016-03-24 09:40:56
     */
    public void afterThrowing(Method method, Object[] args, Object target,
                              RuntimeException  throwable) {

        logger.error("产生异常的方法名称：  " + method.getName());
        for(Object o:args){
            logger.error("方法的参数：   " + o.toString());
        }
        logger.error("代理对象：   " + target.getClass().getName());
        logger.error("抛出的异常:    " + throwable.getMessage()+">>>>>>>"
                + throwable.getCause());
        logger.error("异常详细信息：　　　"+throwable.fillInStackTrace());

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = throwable.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "/n");
        }
        logger.error("异常日志::::::"+sb.toString());

        if(throwable instanceof NeedWarningException){
            logger.error(NotifyWarningUtil.getStackMsg(throwable.getCause()));
            JobWarningModel jobWarningModel = new JobWarningModel();
            jobWarningModel.setWarningTitle("定时任务报警");
            jobWarningModel.setWarningContent(NotifyWarningUtil.getStackMsg(throwable.getCause()));
            //jobService.notifyJobCenterManger(jobWarningModel);
            NotifyWarningUtil.notifyJobWarningMessage(null,jobWarningModel);
        }


    }
}