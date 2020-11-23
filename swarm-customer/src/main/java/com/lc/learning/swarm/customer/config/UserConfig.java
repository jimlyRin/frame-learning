package com.lc.learning.swarm.customer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/9/25
 */
@Data
@Component
@ConfigurationProperties(prefix = "user")
public class UserConfig {
    private String username;
    private String password;
}
