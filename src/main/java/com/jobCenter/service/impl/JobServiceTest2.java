package com.jobCenter.service.impl;

import com.jobCenter.util.client.AbstractService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：业务系统配置serviceName即可实现测试
 * 作者 ：kangzz
 * 日期 ：2016-03-21 01:22:59
 */
@Service(value = "jobServiceTest2")
@Transactional
public class JobServiceTest2 extends AbstractService {

    private final static Logger logger = Logger.getLogger(JobServiceTest2.class);

    public void run() {
        //业务

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doSomeThing();

        String uuid = getUuid();
        //回调
        //callback(jobId,uuid,"true");
        successCallBackToJobCenter();

    }
    public void doSomeThing(){
        logger.info("jobServiceTest2获取到的uuid========"+getUuid());
    }
}
