package com.jobCenter.comm;

import com.jobCenter.enums.HeartType;
import com.xiaoleilu.hutool.system.SystemUtil;

/**
 * 描述：系统常量
 * 作者 ：kangzz
 * 日期 ：2016-03-19 01:48:39
 */
public class SystemConstant {
    //主机标志
    public static final String MASTER_IDENTITY = "slave__"+HeartType.JOB_CENTER.getValue() + ":"
            + SystemUtil.getHostInfo().getName() + ":" + SystemUtil.getHostInfo().getAddress();
    //本机是否为主机 用于添加修改任务时校验 若非主机 给予提示
    public static Boolean localIsMaster = false;
    //定时任务心跳最大间隔时间 若未更新则认为超时 可已切换为主机 单位:秒
    public static final Integer HEAR_MAX_VAL = 10;
    //定时任务心跳频率 单位:毫秒
    public static final Integer HEAR_RATE = 5000;
    //抢占监听器等候启动时间 单位:毫秒
    public static final Integer GRAD_TO_MASTER_LISTENER_WAIT_TIME = 10000;


    //主机监听器等候启动时间 单位:毫秒
    public static final Integer MASTER_LISTENER_WAIT_TIME = 10000;
    //备机切换主机监听器等候启动时间 单位:毫秒
    public static final Integer SLAVE_TO_MASTER_WAIT_TIME = 20000;
    //MD5加密key
    public static final String MD5_KEY = "748d5cb7c2c6824b";
    //MD5加密截取字符占比
    public static  final int MD5_RATIO = 2;

}
