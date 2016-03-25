package com.jobCenter.comm;


import com.jobCenter.util.ErrorCodeMsgUtil;

/**
 * 描述：需要报警的
 * 作者 ：kangzz
 * 日期 ：2016-03-24 19:27:44
 */
public class NeedWarningException extends RuntimeException {

    private int code;

    private String message;

    private boolean flag = false;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public NeedWarningException(int code) {
        super(ErrorCodeMsgUtil.getMessage(code));
        this.code = code;
        String message = ErrorCodeMsgUtil.getMessage(code);
        if (message == null || "".equals(message.trim())) {
            this.message = message;
        }

    }
    public NeedWarningException(int code,Throwable e) {
        super(ErrorCodeMsgUtil.getMessage(code),e);
        this.code = code;
        String message = ErrorCodeMsgUtil.getMessage(code);
        if (message == null || "".equals(message.trim())) {
            this.message = message;
        }

    }
    public NeedWarningException(int code, Object[] args) {
        super(ErrorCodeMsgUtil.getMessage(code,args));
        this.code = code;
        this.flag = true;
        String message = ErrorCodeMsgUtil.getMessage(code,args);
        if (message == null || "".equals(message.trim())) {
            this.message = message;
        }

    }
    public NeedWarningException(int code, Object[] args,Throwable e) {
        super(ErrorCodeMsgUtil.getMessage(code,args),e);
        this.code = code;
        this.flag = true;
        String message = ErrorCodeMsgUtil.getMessage(code,args);
        if (message == null || "".equals(message.trim())) {
            this.message = message;
        }

    }
}