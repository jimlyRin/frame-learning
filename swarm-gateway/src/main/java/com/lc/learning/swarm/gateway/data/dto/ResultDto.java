package com.lc.learning.swarm.gateway.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto<T> {
    @Builder.Default
    private Integer code = 0;

    @Builder.Default
    private String desc = "";

    private T data;
}
