package com.jobCenter.comm;

import com.jobCenter.util.SysPropUtil;

/**
 * Created by kangzz on 16/3/23.
 */
public class GlobalVariable {

    public  static String Email_Host = "";
    public  static String Email_Port ="";
    public  static String Email_User ="";
    public  static String Email_Password ="";
    public  static String Email_FromAddress ="";
    public  static String Email_FromName ="";
    static {
        try {
            Email_Host= SysPropUtil.getSystemConstant("Email_Host");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Email_Port=SysPropUtil.getSystemConstant("Email_Port");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Email_User=SysPropUtil.getSystemConstant("Email_User");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Email_Password=SysPropUtil.getSystemConstant("Email_Password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Email_FromAddress=SysPropUtil.getSystemConstant("Email_FromAddress");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Email_FromName=SysPropUtil.getSystemConstant("Email_FromName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
