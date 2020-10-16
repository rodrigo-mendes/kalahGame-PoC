package com.rms.kalah.primaryAdapter.game;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface GameRouter {
    RouterFunction<ServerResponse> route(GameHandler handler);
}
