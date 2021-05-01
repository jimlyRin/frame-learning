package com.lc.frame.swarm.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.frame.swarm.gateway.data.dto.ResultDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 认证过滤器
 *
 * @author ljl
 * @version v1.0
 * @date 2020/8/7
 */
public class ApiAuthFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("=  =  =  =  =  = 执行了AuthFilter =  =  =  =  =  =  =  =");
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if ("token".equals(token)) {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("=  =  =  =  =  = 执行了 post =  =  =  =  =  =  =  =");
            }));
        }
        ServerHttpResponse response = exchange.getResponse();
        ResultDto resultDto = ResultDto.builder().code(401).desc("非法请求").build();
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(resultDto);
        } catch (Exception ex) {
        }
        byte[] datas = res.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
