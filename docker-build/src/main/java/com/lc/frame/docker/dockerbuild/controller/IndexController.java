package com.lc.frame.docker.dockerbuild.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/11/26
 */
@Slf4j
@RestController
@RequestMapping("/v1/docker-file-build/")
public class IndexController {

    @GetMapping(value = "/index")
    public String index() {
        return "hello docker";
    }
}
