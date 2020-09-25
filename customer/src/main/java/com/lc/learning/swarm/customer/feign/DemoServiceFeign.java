package com.lc.learning.swarm.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/7
 */
@FeignClient("api-demo")
public interface DemoServiceFeign {

    @GetMapping(value = "/v1/demo/index")
    String index();
}
