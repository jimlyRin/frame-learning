package com.lc.frame.permission.service.controller;

import com.lc.frame.permission.service.data.bean.ResultDto;
import com.lc.frame.permission.service.data.dto.UserLoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @PostMapping("/login")
    public ResultDto login(@RequestBody UserLoginDto loginDto) {
        return ResultDto.success();
    }

    @PostMapping("/logout")
    public ResultDto logout() {
        return ResultDto.success();
    }
}
