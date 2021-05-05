package com.lc.frame.quartz.starter.runner;

import com.lc.frame.quartz.starter.annotation.LcJobComponent;
import com.lc.frame.quartz.starter.constant.LcJobConstant;
import com.lc.frame.quartz.starter.job.LcJobBean;
import com.lc.frame.quartz.starter.job.LcJobFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import java.util.Map;
import java.util.TimeZone;

/**
 * LcQuartzRunner
 *
 * @author jimlyRin
 */
@Slf4j
public class LcQuartzRunner implements ApplicationRunner {

    @Resource
    private ConfigurableApplicationContext context;

    @Resource
    private LcJobFactory lcJobFactory;

    private int count = 0;
    private int startQty = 0;

    @Override
    public void run(ApplicationArguments args) {
        log.info("定时JOB开始");
        Map<String, Object> jobListMap = this.context.getBeansWithAnnotation(LcJobComponent.class);
        if (jobListMap.isEmpty()) {
            log.info("无定时任务");
            return;
        }
        jobListMap.entrySet().forEach(this::builder);
        count = jobListMap.size();
        log.info("定时JOB启动完毕，任务数:{}, 开始执行:{}", count, startQty);
    }

    @SneakyThrows(SchedulerException.class)
    private void builder(Map.Entry<String, Object> set) {
        LcJobComponent jobComponent = set.getValue().getClass().getAnnotation(LcJobComponent.class);
        LcJobBean jobBean = (LcJobBean)set.getValue();
        Job jobClazz = (Job)set.getValue();

        String jobId = jobComponent.jobId();
        String groupKey = jobId;
        String cron = jobBean.getCron();

        //创建一个trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobId + LcJobConstant.TRIGGER_NAME, groupKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).inTimeZone(TimeZone.getTimeZone(LcJobConstant.TIME_ZONE)).withMisfireHandlingInstructionDoNothing())
                .build();

        //创建一个job
        JobDetail job = JobBuilder.newJob(jobClazz.getClass())
                .withIdentity(jobId + LcJobConstant.JOB_NAME, groupKey)
                .build();

        Scheduler scheduler = lcJobFactory.buildScheduler(job, trigger);

        jobBean.setScheduler(scheduler);
        jobBean.setJobId(jobId);

        if (jobComponent.autoStart()) {
            scheduler.start();
            this.startQty++;
        }
    }
}
