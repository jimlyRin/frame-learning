package com.lc.frame.swarm.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("lc.job")
public class JobConfig {

    private String demo;
    private String demo2;
}
