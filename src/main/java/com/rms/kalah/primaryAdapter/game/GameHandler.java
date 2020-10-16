package com.rms.kalah.primaryAdapter.game;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface GameHandler {
    Mono<ServerResponse> create(ServerRequest request);
}
