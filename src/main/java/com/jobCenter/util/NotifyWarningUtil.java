package com.jobCenter.util;


import com.jobCenter.enums.JobWarningPersonType;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.model.JobWarningPersonModel;
import com.jobCenter.util.mail.MailUtils;

import java.util.ArrayList;
import java.util.List;

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

        List<String> toAddressList = new ArrayList<String>();
        List<String> receiveAddress = new ArrayList<String>();
        if (personModelList!=null && !personModelList.isEmpty()) {
            for (int i = 0; i <personModelList.size(); i++) {
                JobWarningPersonModel jobWarningPersonModel = personModelList.get(i);
                String personEmail = jobWarningPersonModel.getPersonEmail();
                if(JobWarningPersonType.ZYRY.getValue() == jobWarningPersonModel.getPersonType()){
                    toAddressList.add(personEmail);
                }else{
                    receiveAddress.add(personEmail);
                }
            }
        }
        MailUtils.sendHtmlEmail(toAddressList, jobWarningModel.getWarningTitle(), jobWarningModel.getWarningContent(), receiveAddress, null);
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
