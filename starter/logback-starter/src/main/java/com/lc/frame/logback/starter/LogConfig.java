package com.lc.frame.logback.starter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:logback-config.xml", "classpath:logback.component.properties"})
public class LogConfig {
}
