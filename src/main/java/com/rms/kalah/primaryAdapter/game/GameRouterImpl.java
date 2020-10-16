package com.rms.kalah.primaryAdapter.game;

import com.rms.kalah.primaryAdapter.game.dto.GameDTO;
import com.rms.kalah.primaryAdapter.movement.dto.BoardStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GameRouterImpl implements GameRouter {

    @Bean("gameRoute")
    @Override
    @Operation(summary = "Create a game")
    @RouterOperation(path = "/games", operation = @Operation(operationId = "gameCreated", summary = "Create a new game", tags = { "MyGame" },
            responses = { @ApiResponse(responseCode = "200", description = "Game Created ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GameDTO.class)) })}))
    public RouterFunction<ServerResponse> route(GameHandler handler) {

        return RouterFunctions
                .route(RequestPredicates
                        .POST("/games")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::create);
    }
}
