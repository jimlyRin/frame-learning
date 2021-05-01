package com.lc.frame.swarm.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/8/10
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
     * 代码配置路由，同配置文件配置路由功能一致，供学习备用，当前使用配置文件路由，这里注释
     */
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/api-producer/**")
//                        .filters(f -> {
//                            f.stripPrefix(1);
//                            f.hystrix(h -> {
//                                h.setName("fallbackcmd");
//                                h.setFallbackUri("forward:/fallback");
//                            });
//                            return f;
//                        })
//                        .uri("lb://api-producer")
//                        .filter(new ApiAuthFilter())
//                        .id("producer_route")
//                )
//                .route(r -> r.path("/api-customer/actuator/refresh")
//                        .filters(f -> {
//                            f.stripPrefix(1);
//                            f.hystrix(h -> {
//                                h.setName("refreshconfig");
//                                h.setFallbackUri("forward:/fallback");
//                            });
//                            return f;
//                        })
//                        .uri("lb://api-customer")
//                        .id("customer_refresh_config")
//                )
//                .route(r -> r.path("/api-customer/**")
//                        .filters(f -> {
//                            f.stripPrefix(1);
//                            f.hystrix(h -> {
//                                h.setName("fallbackcmd");
//                                h.setFallbackUri("forward:/fallback");
//                            });
//                            return f;
//                        })
//                        .uri("lb://api-customer")
//                        .id("customer_route")
//                )
//                .build();
//    }
}
