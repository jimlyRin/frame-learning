package com.lc.frame.permission.service.data.bean;

import lombok.Data;

@Data
public class ResultDto<T> {

    private Integer code = 0;
    private String msg = "执行成功";
    private T data;

    public static ResultDto success() {
        return new ResultDto();
    }

    public static <T> ResultDto success(T data) {
        ResultDto dto = new ResultDto();
        dto.setData(data);
        return dto;
    }

    public static ResultDto error(Integer code, String msg) {
        ResultDto dto = new ResultDto();
        dto.setCode(code);
        dto.setMsg(msg);
        return dto;
    }

    public static <T> ResultDto error(Integer code, String msg, T data) {
        ResultDto dto = new ResultDto();
        dto.setCode(code);
        dto.setMsg(msg);
        dto.setData(data);
        return dto;
    }
}
