package com.jobCenter.util;

import com.jobCenter.enums.HeartType;
import com.xiaoleilu.hutool.system.SystemUtil;

/**
 * 描述：系统常量
 * 作者 ：kangzz
 * 日期 ：2016-03-19 01:48:39
 */
public class SystemConstant {
    //主机标志
    public static final String MASTER_IDENTITY = HeartType.JOB_CENTER.getValue() + ":"
            + SystemUtil.getHostInfo().getName() + ":" + SystemUtil.getHostInfo().getAddress();
    //定时任务心跳最大间隔时间
    public static final Integer HEAR_MAX_VAL = 10;
    //AES加密key
    public static final String AES_KEY = "JOB_CENTER_AES_KEY";
    //AES加密截取字符占比
    public static  final int AES_RATIO = 2;

}
