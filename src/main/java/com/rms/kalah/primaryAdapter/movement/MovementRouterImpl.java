package com.rms.kalah.primaryAdapter.movement;

import com.rms.kalah.error.ErrorDTO;
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
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class MovementRouterImpl implements MovementRouter {

    @Override
    @Bean("movementRoute")
    @RouterOperation(path = "/games/{gameId}/pits/{pitId}", method= RequestMethod.PUT, operation = @Operation(operationId = "move", summary = "Making a move in a game", tags = { "MyGame" },
            parameters = { @Parameter(in = ParameterIn.PATH, name = "gameId", description = "Game Id"),
                           @Parameter(in = ParameterIn.PATH, name = "pitId", description = "Pit Id")},
            responses = { @ApiResponse(responseCode = "200", description = "Move made",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BoardStatusDTO.class)) }),
                    @ApiResponse(responseCode = "400", description = "Invalid Parameters: gameId or pitId it's not a number.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
                    @ApiResponse(responseCode = "461", description = "Pit out of Range", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
                    @ApiResponse(responseCode = "462", description = "Pit is a Kalah", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
                    @ApiResponse(responseCode = "463", description = "Pit Empty", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
                    @ApiResponse(responseCode = "464", description = "Invalid GameId", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) })  }))
    public RouterFunction<ServerResponse> route(MovementHandler handler) {

        return RouterFunctions
                .route(RequestPredicates //validation if gameId and pitId are numbers
                        .PUT("/games/{gameId:[0-9]+}/pits/{pitId:[0-9]+}") // validation Composition and Pattern Matching
                        .and(RequestPredicates.accept(MediaType.ALL)), handler::move)
                .andRoute(RequestPredicates
                        .PUT("/games/{gameId}/pits/{pitId}")
                        .and(RequestPredicates.accept(MediaType.ALL)), this::badRequest);
    }

    private Mono<ServerResponse> badRequest(ServerRequest request) {
        return ServerResponse.badRequest().bodyValue(new ErrorDTO("Invalid Parameters: gameId or pitId it's not a number."));
    }
}
