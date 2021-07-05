package com.lc.frame.docker.dockerbuild.controller;

import com.lc.frame.docker.dockerbuild.data.AesContent;
import com.lc.frame.docker.dockerbuild.utils.AESUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/aes256")
    @SneakyThrows(Exception.class)
    public String aesEncrypt(@RequestBody AesContent body) {
        if (body.getType() == 1) {
            return AESUtils.decodeStr(body.getContent(), KEY);
        }
        return AESUtils.encryptStr(body.getContent(), KEY);
    }
}
