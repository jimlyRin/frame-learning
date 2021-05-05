package com.lc.frame.quartz.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * LcQuartzProperties
 *
 * @author jimlyRin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "lc.job")
public class LcQuartzProperties {

    Boolean enable = false;
}
