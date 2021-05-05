package com.lc.frame.quartz.starter.job;

import com.lc.frame.quartz.starter.constant.LcJobConstant;
import lombok.Data;
import lombok.SneakyThrows;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * LcJobBean
 *
 * @author jimlyRin
 */
@Data
public abstract class LcJobBean extends QuartzJobBean implements ILcScheduilerControl {

    private String jobId;
    private Scheduler scheduler;

    public abstract String getCron();

    @Override
    @SneakyThrows(Exception.class)
    public void start() {
        if (!this.scheduler.isStarted()) {
            this.scheduler.start();
        }
    }

    @Override
    @SneakyThrows(Exception.class)
    public void shutdown() {
        if (this.scheduler.isStarted()) {
            this.scheduler.shutdown();
        }
    }

    @Override
    @SneakyThrows(Exception.class)
    public void pause() {
        TriggerKey triggerKey = getTriggerKey();
        scheduler.pauseTrigger(triggerKey);
    }

    @Override
    @SneakyThrows(Exception.class)
    public void resume() {
        TriggerKey triggerKey = getTriggerKey();
        scheduler.resumeTrigger(triggerKey);
    }

    private TriggerKey getTriggerKey() {
        return TriggerKey.triggerKey(this.jobId + LcJobConstant.JOB_NAME, this.jobId);
    }
}
