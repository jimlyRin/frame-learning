package com.lc.frame.quartz.starter.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * LcJobFactory
 *
 * @author jimlyRin
 */
@Component
public class LcJobFactory extends SpringBeanJobFactory {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

    public Scheduler buildScheduler(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        // SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.setJobFactory(this);

        scheduler.scheduleJob(jobDetail, trigger);
        return scheduler;
    }
}
