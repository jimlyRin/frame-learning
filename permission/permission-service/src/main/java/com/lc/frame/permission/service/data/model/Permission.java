package com.lc.frame.permission.service.data.model;

import lombok.Data;

@Data
public class Permission {
    private Long id;
    private String name;
    private String code;
    private String remark;
    private Long parentId;
}
