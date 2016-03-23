package com.jobCenter.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


/**
 * 描述：发送邮件基础工具
 * 作者 ：kangzz
 * 日期 ：2016-03-23 13:59:00
 */
public class SimpleMailSender {
/** */
    /**
     * 以文本格式发送邮件
     *
     * @param mailInfo 待发送的邮件的信息
     */
    public boolean sendTextMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证


        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            System.out.println(sendMailSession.toString());
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            String nick = "";
            try {
                nick = MimeUtility.encodeText(mailInfo.getFromName());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mailMessage.setFrom(new InternetAddress(nick + " <" + from + ">"));
            // 创建邮件的接收者地址，并设置到邮件消息中
            List<String> toAddress = mailInfo.getToAddress();
            if (toAddress != null && !toAddress.isEmpty()) {
                Address[] to = new InternetAddress[toAddress.size()];
                for (int i = 0; i < toAddress.size(); i++) {
                    to[i] = new InternetAddress(toAddress.get(i));
                }
                mailMessage.setRecipients(Message.RecipientType.TO, to);
            }
            // 获取抄送者信息
            //String[] receiveAddress = mailInfo.getReceiveAddress()!=null && !mailInfo.getReceiveAddress().isEmpty() ?(String[]) mailInfo.getReceiveAddress().toArray() : null;
            List<String> receiveAddress = mailInfo.getReceiveAddress();
            if (receiveAddress != null && !receiveAddress.isEmpty()) {
                // 为每个邮件接收者创建一个地址
                Address[] ccAddress = new InternetAddress[receiveAddress.size()];
                for (int i = 0; i < receiveAddress.size(); i++) {
                    ccAddress[i] = new InternetAddress(receiveAddress.get(i));
                }
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                mailMessage.setRecipients(Message.RecipientType.CC, ccAddress);
            }
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /** */
    /**
     * 以HTML格式发送邮件
     *
     * @param mailInfo 待发送的邮件信息
     */
    public boolean sendHtmlMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            String nick = "";
            try {
                nick = MimeUtility.encodeText(mailInfo.getFromName());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mailMessage.setFrom(new InternetAddress(nick + " <" + from + ">"));
            // 创建邮件的接收者地址，并设置到邮件消息中
            List<String> toAddress = mailInfo.getToAddress();
            if (toAddress != null && !toAddress.isEmpty()) {
                Address[] to = new InternetAddress[toAddress.size()];
                for (int i = 0; i < toAddress.size(); i++) {
                    to[i] = new InternetAddress(toAddress.get(i));
                }
                mailMessage.setRecipients(Message.RecipientType.TO, to);
            }
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 获取抄送者信息
            //String[] receiveAddress = mailInfo.getReceiveAddress()!=null && !mailInfo.getReceiveAddress().isEmpty() ?(String[]) mailInfo.getReceiveAddress().toArray() : null;
            List<String> receiveAddress = mailInfo.getReceiveAddress();
            if (receiveAddress != null && !receiveAddress.isEmpty()) {
                // 为每个邮件接收者创建一个地址
                Address[] ccAdresses = new InternetAddress[receiveAddress.size()];
                for (int i = 0; i < receiveAddress.size(); i++) {
                    ccAdresses[i] = new InternetAddress(receiveAddress.get(i));
                }
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                mailMessage.setRecipients(Message.RecipientType.CC, ccAdresses);
            }
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            List<String> fileNames = mailInfo.getAttachFileNames();
            if (fileNames != null && !fileNames.isEmpty()) {
                for (int i = 0; i < fileNames.size(); i++) {
                    String fileName = fileNames.get(i);
                    mbp = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileName); //得到数据源
                    mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart
                    try {
                        //处理附件中文乱码问题
                        mbp.setFileName(MimeUtility.encodeText("手机险自助化报案流程.pdf"));
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }   //得到文件名同样至入BodyPart
                    mainPart.addBodyPart(mbp);
                }
            }
            System.out.println("用的是html这个方法");
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}   

