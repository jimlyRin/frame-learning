package com.lc.frame.permission.service.data.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Integer status;
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
}
