package com.lc.learning.swarm.customer.controller;

import com.lc.learning.swarm.customer.config.UserConfig;
import com.lc.learning.swarm.customer.feign.DemoServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/8/7
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/customer/")
public class CustomerController {

    @Autowired
    private DemoServiceFeign demoServiceFeign;

    @Autowired
    private UserConfig userConfig;

    @GetMapping(value = "/index")
    public String index() {
        String say = demoServiceFeign.index();
        return "hello customer, and producer say：" + say;
    }

    @GetMapping(value = "/username")
    public String getUsername() {
        return "config username: " + userConfig.getUsername();
    }

}
