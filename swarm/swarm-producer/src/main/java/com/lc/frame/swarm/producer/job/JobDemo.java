package com.lc.frame.swarm.producer.job;

import com.lc.frame.quartz.starter.annotation.LcJobComponent;
import com.lc.frame.quartz.starter.job.LcJobBean;
import com.lc.frame.swarm.producer.config.JobConfig;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * JobDemo
 *
 * @author jimlyRin
 */
@LcJobComponent(jobId = "demoJOB", autoStart = true)
@Slf4j
public class JobDemo extends LcJobBean {

    @Resource
    private JobConfig jobConfig;

    @Override
    public String getCron() {
        return jobConfig.getDemo();
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时任务demo....");
    }
}
