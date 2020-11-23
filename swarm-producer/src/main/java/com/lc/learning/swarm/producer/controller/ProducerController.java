package com.lc.learning.swarm.producer.controller;

import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/v1/producer/")
public class ProducerController {

    @GetMapping(value = "/index")
    public String index() {
        return "hello producer";
    }

    @GetMapping(value = "/fusing")
    public String fusing() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        return "当你看到这段字表示熔断失败了";
    }
}
