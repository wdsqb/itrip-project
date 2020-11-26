package com.hzl.itripGateWay.filter;


import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 * @author hzl
 * @Description: 响应body拦截处理接口
 * @date 2020-09-23 12:38
 */
    public interface BodyHandlerFunction extends BiFunction<ServerHttpResponse, Publisher<? extends DataBuffer>, Mono<Void>> {
}