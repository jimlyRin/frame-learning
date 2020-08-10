package com.lc.learning.swarm.gateway.config;

import com.lc.learning.swarm.gateway.filter.ApiAuthFilter;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/10
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api-demo/**")
                        .filters(f -> {
                            f.stripPrefix(1);
                            f.hystrix(h -> {
                                h.setName("fallbackcmd");
                                h.setFallbackUri("forward:/fallback");
                            });
                            return f;
                        })
                        .uri("lb://api-demo")
                        .filter(new ApiAuthFilter())
                        .id("demo_route")
                )
                .build();
    }
}
