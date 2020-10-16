package com.rms.kalah.primaryAdapter.movement;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface MovementHandler {
    Mono<ServerResponse> move(ServerRequest request);
}
