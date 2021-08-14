package com.alibaba.gateWay.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author recheard
 * @description: 全局过滤测试
 * @date 2021/8/1317:53
 */
@Component
public class TestGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求头
        HttpHeaders headers = exchange.getRequest().getHeaders();
        //获取请求参数
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();

        //校验token
        String token = queryParams.getFirst("token");
        if (StringUtils.isEmpty(token)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            String msg = "token can't be null";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        System.out.println("custom global filter");
        return chain.filter(exchange);
    }

    /**
     * 设置filter优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
/*
    @Bean
    public GlobalFilter testFilter() {
        return new TestGlobalFilter();
    }*/
}
