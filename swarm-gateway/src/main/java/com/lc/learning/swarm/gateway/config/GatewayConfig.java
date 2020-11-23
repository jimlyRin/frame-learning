package com.lc.learning.swarm.gateway.config;

import com.lc.learning.swarm.gateway.filter.ApiAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/10
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        // 试过用配置文件配置路由，所有过滤器都不生效
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
