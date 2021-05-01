package com.lc.frame.swarm.gateway.filterfactory;

import com.lc.frame.swarm.gateway.filter.ApiAuthFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author ljl
 * @version v1.0
 * @date 2020/8/7
 */
@Component
public class ApiAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<ApiAuthGatewayFilterFactory.Config> {

    public ApiAuthGatewayFilterFactory() {
        super(ApiAuthGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new ApiAuthFilter();
//        return (exchange, chain) -> {
//            System.out.println("=  =  =  =  =  = 执行了 pre =  =  =  =  =  =  =  =");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                System.out.println("=  =  =  =  =  = 执行了 post =  =  =  =  =  =  =  =");
//            }));
//        };
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
