package com.lc.learning.swarm.gateway.filterfactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

/**
 * @author linjingliang
 * @version v1.0
 * @date 2020/8/7
 */
public class ApiAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<ApiAuthGatewayFilterFactory.Config> {

    public ApiAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("=  =  =  =  =  = 执行了AuthGatewayFilterFactory =  =  =  =  =  =  =  =");
//        return new ApiAuthFilter();
        return (exchange, chain) -> {
            System.out.println("=  =  =  =  =  = 执行了 pre =  =  =  =  =  =  =  =");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("=  =  =  =  =  = 执行了 post =  =  =  =  =  =  =  =");
            }));
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
