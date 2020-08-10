package com.lc.learning.swarm.gateway.filterfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.learning.swarm.gateway.data.dto.ResultDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/7
 */
public class RateLimiterGatewayFilterFactory extends AbstractGatewayFilterFactory<RateLimiterGatewayFilterFactory.Config> {

    public static final String KEY_RESOLVER_KEY = "keyResolver";

    private final RateLimiter defaultRateLimiter;
    private final KeyResolver defaultKeyResolver;

    public RateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter,
                                           KeyResolver defaultKeyResolver) {
        super(Config.class);
        this.defaultRateLimiter = defaultRateLimiter;
        this.defaultKeyResolver = defaultKeyResolver;
    }

    public KeyResolver getDefaultKeyResolver() {
        return defaultKeyResolver;
    }

    public RateLimiter getDefaultRateLimiter() {
        return defaultRateLimiter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = (config.keyResolver == null) ? defaultKeyResolver : config.keyResolver;
        RateLimiter<Object> limiter = (config.rateLimiter == null) ? defaultRateLimiter : config.rateLimiter;

        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

            return resolver.resolve(exchange).flatMap(key ->
                    // TODO: if key is empty?
                    limiter.isAllowed(route.getId(), key).flatMap(response -> {

                        for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                            exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                        }

                        if (response.isAllowed()) {
                            return chain.filter(exchange);
                        }
                        ServerHttpResponse rs = exchange.getResponse();
                        ResultDto data = ResultDto.builder().code(101).desc("访问过快").build();
                        String res = "";
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            res = mapper.writeValueAsString(data);
                        } catch (Exception ex) {
                        }
                        byte[] datas = res.getBytes(StandardCharsets.UTF_8);
                        DataBuffer buffer = rs.bufferFactory().wrap(datas);
                        rs.setStatusCode(HttpStatus.UNAUTHORIZED);
                        rs.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                        return rs.writeWith(Mono.just(buffer));
                    }));
        };
    }

    public static class Config {
        private KeyResolver keyResolver;
        private RateLimiter rateLimiter;
        private HttpStatus statusCode = HttpStatus.TOO_MANY_REQUESTS;

        public KeyResolver getKeyResolver() {
            return keyResolver;
        }

        public Config setKeyResolver(KeyResolver keyResolver) {
            this.keyResolver = keyResolver;
            return this;
        }
        public RateLimiter getRateLimiter() {
            return rateLimiter;
        }

        public Config setRateLimiter(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
            return this;
        }

        public HttpStatus getStatusCode() {
            return statusCode;
        }

        public Config setStatusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }
    }
}
