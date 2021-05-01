package com.lc.frame.swarm.gateway.controller;

import com.lc.frame.swarm.gateway.data.dto.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/8/7
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public ResultDto fallback() {
        return ResultDto.builder().code(100).desc("熔断:服务暂时不可用").build();
    }
}
