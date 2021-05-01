package com.lc.frame.docker.dockerbuild.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/11/26
 */
@RestController
public class HealthController {

    @GetMapping("/heath")
    @ResponseBody
    public String heath() {
        return "ok";
    }
}
