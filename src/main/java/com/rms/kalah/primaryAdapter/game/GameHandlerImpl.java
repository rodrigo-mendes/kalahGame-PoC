package com.rms.kalah.primaryAdapter.game;

import com.rms.kalah.primaryAdapter.game.dto.GameDTO;
import com.rms.kalah.rules.KalahGameMotor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

@Component
public final class GameHandlerImpl implements GameHandler {

    @Autowired
    private transient KalahGameMotor gameMotor;

    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
        GameDTO gameDto = new GameDTO(
                gameMotor.createGame(),
                InetAddress.getLoopbackAddress().getHostAddress(),
                InetAddress.getLoopbackAddress().getHostName());

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(gameDto);
    }
}
