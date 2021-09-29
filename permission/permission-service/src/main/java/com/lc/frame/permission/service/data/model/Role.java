package com.lc.frame.permission.service.data.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private String id;
    private String name;
    private String remark;
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
}
