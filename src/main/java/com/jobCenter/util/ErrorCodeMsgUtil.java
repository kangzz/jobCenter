package com.jobCenter.util;

import com.jobCenter.util.SysPropUtil;

import java.text.MessageFormat;

/**
 * 描述：获取错误编码
 * 作者 ：kangzz
 * 日期 ：2016-03-24 09:33:37
 */
public class ErrorCodeMsgUtil {

    private final static String ERROR_CODE_MSG_CONFIG_PATH = "error_code_msg_zh.properties";

    /**
     * 获取code 对应
     * @param code
     * @return
     */
    public static String getMessage(int code) {
        String template = SysPropUtil.getErrorMessageConstant("api.errorCode." + String.valueOf(code));
        return template;
    }
    
    /**
     * 描述：获取code对应错误码 并替换占位符
     * 作者 ：kangzz
     * 日期 ：2016-03-24 09:33:54
     */
    public static String getMessage(int code, Object[] args) {
        String template = SysPropUtil.getErrorMessageConstant("api.errorCode." + String.valueOf(code));
        template = MessageFormat.format(template, args);
        return template;
    }
}
