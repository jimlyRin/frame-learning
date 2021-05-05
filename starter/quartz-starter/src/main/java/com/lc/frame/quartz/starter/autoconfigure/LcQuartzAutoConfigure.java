package com.lc.frame.quartz.starter.autoconfigure;

import com.lc.frame.quartz.starter.runner.LcQuartzRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * LcQuartzAutoConfigure
 *
 * @author jimlyRin
 */
@Configuration
public class LcQuartzAutoConfigure {

    @Bean
    @ConditionalOnProperty(value = {"lc.job.enable"}, havingValue = "true", matchIfMissing = true)
    public LcQuartzRunner lcQuartzRunner() {
        return new LcQuartzRunner();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public SchedulerFactoryBean quartzScheduler() {
        return null;
    }
}
