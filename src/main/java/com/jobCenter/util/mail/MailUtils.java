package com.jobCenter.util.mail;

import com.jobCenter.comm.GlobalVariable;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 描述：添加发送邮件工具类
 * 作者 ：kangzz
 * 日期 ：2016-03-23 13:58:17
 */
public class MailUtils {

    private static final Logger logger = Logger.getLogger(MailUtils.class);

    /**
     * 描述：发送邮件 无附件信息
     * 作者 ：kangzz
     * 日期 ：2016-03-24 08:49:28
     */
    public static boolean sendHtmlEmail(List<String> toAddress, String subject, String contact, List<String> receiveAddress) {
        return sendHtmlEmail(toAddress, subject, contact, receiveAddress, null);
    }

    /**
     * 描述：发送邮件方法
     * 作者 ：kangzz
     * 日期 ：2016-03-23 12:55:21
     */
    public static boolean sendHtmlEmail(List<String> toAddress, String subject, String contact, List<String> receiveAddress, List<String> fileNames) {
        MailSenderInfo mailInfo = new MailSenderInfo();

        mailInfo.setMailServerHost(GlobalVariable.Email_Host);
        //mailInfo.setMailServerPort(GlobalVariable.Email_Port);
        mailInfo.setValidate(true);
        mailInfo.setUserName(GlobalVariable.Email_User);
        mailInfo.setPassword(GlobalVariable.Email_Password);//您的邮箱密码
        mailInfo.setFromAddress(GlobalVariable.Email_User);
        try {
            mailInfo.setFromName(new String((GlobalVariable.Email_FromName).getBytes("iso-8859-1"), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailInfo.setToAddress(toAddress);
        if (receiveAddress != null && !receiveAddress.isEmpty()) {
            mailInfo.setReceiveAddress(receiveAddress);
        }
        mailInfo.setAttachFileNames(fileNames);
        mailInfo.setSubject(subject);
        mailInfo.setContent(contact);
        //这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        return sms.sendHtmlMail(mailInfo);
    }

    public static void main(String[] args) {
        MailUtils UseEmail = new MailUtils();

        List<String> toAddress = new ArrayList<String>();
        toAddress.add("515294820@qq.com");
        List<String> re = new ArrayList<String>();
        re.add("85138124@qq.com");

        logger.info(UseEmail.sendHtmlEmail(toAddress, "标题2333", "!210:51:167:79</br>如下:</br>JobServiceImpl.javaloadAllJobListForMaster136<br>NativeMethodAccessorImpl.javainvoke0-2<br>NativeMethodAccessorImpl.javainvoke57<br>DelegatingMethodAccessorImpl.javainvoke43<br>Method.javainvoke606<br>AopUtils.javainvokeJoinpointUsingReflection317<br>ReflectiveMethodInvocation.javainvokeJoinpoint183<br>ReflectiveMethodInvocation.javaproceed150<br>ThrowsAdviceInterceptor.javainvoke124<br>ReflectiveMethodInvocation.javaproceed172<br>JdkDynamicAopProxy.javainvoke204<br>nullloadAllJobListForMaster-1<br>GrabToMasterListener.javarun98<br>", re));
    }
}
