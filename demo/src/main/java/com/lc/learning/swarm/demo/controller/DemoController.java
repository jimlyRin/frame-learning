package com.lc.learning.swarm.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/7
 */
@RestController
@RequestMapping("/v1/demo/")
public class DemoController {

    @GetMapping(value = "/index")
    public String index() {
        return "hello demo";
    }
}
