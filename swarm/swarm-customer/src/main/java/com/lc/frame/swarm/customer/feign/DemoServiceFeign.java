package com.lc.frame.swarm.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/8/7
 */
@FeignClient("api-producer")
public interface DemoServiceFeign {

    @GetMapping(value = "/v1/producer/index")
    String index();
}
