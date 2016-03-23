package com.jobCenter.util;


import com.jobCenter.model.JobWarningModel;
import com.jobCenter.model.JobWarningPersonModel;
import com.jobCenter.util.mail.MailUtils;

import java.util.List;
import java.util.Map;

/**
 * 描述：报警信息工具类
 * 作者 ：kangzz
 * 日期 ：2016-03-23 19:35:35
 */
public class NotifyWarningUtil {

    /**
     * 描述：发送给定时任务管理员报警信息
     * 作者 ：kangzz
     * 日期 ：2016-03-23 19:37:22
     */
    public static Boolean notifyJobWarningMessage(List<JobWarningPersonModel> personModelList, JobWarningModel jobWarningModel) {
        //String[] toAddress,String subject,String contact,String[] receiveAddress,String[] fileNames
        String[] toAddress = new String[1];
        toAddress[0] = personModelList.get(0).getPersonEmail();
        MailUtils.sendHtmlEmail(toAddress, jobWarningModel.getWarningTitle(), jobWarningModel.getWarningContent(), null, null);
        return false;
    }

    /**
     * 描述：获取异常信息
     * 作者 ：kangzz
     * 日期 ：2016-03-24 00:53:36
     */
    public static String getStackMsg(Throwable e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "</br>");
        }
        return sb.toString();
    }
}
