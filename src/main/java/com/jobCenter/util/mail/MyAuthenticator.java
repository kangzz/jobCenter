package com.jobCenter.util.mail;
import javax.mail.*;
/**
 * 描述：邮箱用户信息
 * 作者 ：kangzz
 * 日期 ：2016-03-23 13:58:41
 */
public class MyAuthenticator extends Authenticator{
    String userName=null;
    String password=null;

    public MyAuthenticator(){
    }
    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }
}   

