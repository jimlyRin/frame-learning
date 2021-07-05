package com.lc.frame.docker.dockerbuild.controller;

import com.lc.frame.docker.dockerbuild.utils.AESUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private static final String KEY = "3776d97bbdfb4cec8aac8d4fbecf381e";

    @GetMapping(value = "/index")
    public String index() {
        return "hello docker";
    }

    @GetMapping(value = "/encrypt")
    public String aesEncrypt(@RequestParam("content") String content) {
        return AESUtils.encryptStr(content, KEY);
    }

    @GetMapping(value = "/decode")
    @SneakyThrows(Exception.class)
    public String aesDecode(@RequestParam("content") String content) {
        return AESUtils.decodeStr(content, KEY);
    }
}
