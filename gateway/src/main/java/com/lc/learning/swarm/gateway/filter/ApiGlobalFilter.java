package com.lc.learning.swarm.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/10
 */
@Component
public class ApiGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("====== 全局过滤器 pre =======");
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    System.out.println("====== 全局过滤器 post =======");
                }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
