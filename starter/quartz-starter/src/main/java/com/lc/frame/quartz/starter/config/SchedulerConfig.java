package com.lc.frame.quartz.starter.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * SchedulerConfig
 *
 * @author jimlyRin
 */
//@Component
public class SchedulerConfig implements SchedulerFactoryBeanCustomizer {

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        // quartz不自动运行
        schedulerFactoryBean.setAutoStartup(false);
//        schedulerFactoryBean.setStartupDelay(10);
//        schedulerFactoryBean.setOverwriteExistingJobs(true);
    }
}
