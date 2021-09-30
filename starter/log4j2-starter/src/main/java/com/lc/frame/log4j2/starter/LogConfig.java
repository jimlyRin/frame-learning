package com.lc.frame.log4j2.starter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:log4j2-config.xml", "classpath:log4j2.component.properties"})
public class LogConfig {
}
