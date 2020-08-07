package com.lc.learning.swarm.customer.controller;

import com.lc.learning.swarm.customer.Feign.DemoServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/7
 */
@Slf4j
@RestController
@RequestMapping("/v1/customer/")
public class CustomerController {

    @Autowired
    private DemoServiceFeign demoServiceFeign;

    @GetMapping(value = "/index")
    public String index() {
        String say = demoServiceFeign.index();
        return "hello customer, and demo say：" + say;
    }

}
