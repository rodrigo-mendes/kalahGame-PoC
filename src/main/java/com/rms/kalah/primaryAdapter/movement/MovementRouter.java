package com.rms.kalah.primaryAdapter.movement;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface MovementRouter {
    @Bean("movementRoute")
    RouterFunction<ServerResponse> route(MovementHandler handler);
}
