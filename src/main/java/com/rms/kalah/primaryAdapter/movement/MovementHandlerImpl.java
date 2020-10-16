package com.rms.kalah.primaryAdapter.movement;

import com.rms.kalah.error.ErrorDTO;
import com.rms.kalah.error.exception.InvalidGameIdException;
import com.rms.kalah.error.exception.IsKalahPitException;
import com.rms.kalah.error.exception.PitEmptyException;
import com.rms.kalah.error.exception.PitOutOfRangeException;
import com.rms.kalah.primaryAdapter.game.dto.GameDTO;
import com.rms.kalah.primaryAdapter.movement.dto.BoardStatusDTO;
import com.rms.kalah.rules.KalahGameMotor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

@Component
public final class MovementHandlerImpl implements MovementHandler {

    @Autowired
    private transient KalahGameMotor gameMotor;

    @Override
    public Mono<ServerResponse> move(ServerRequest request) {

        Integer gameId = Integer.valueOf(request.pathVariable("gameId"));
        Integer pitId = Integer.valueOf(request.pathVariable("pitId"));

        GameDTO gameDto = new GameDTO(
                gameId,
                InetAddress.getLoopbackAddress().getHostAddress(),
                InetAddress.getLoopbackAddress().getHostName());
        try {
            BoardStatusDTO gameBoard = new BoardStatusDTO(gameDto, gameMotor.makeMovement(gameId, pitId));
            return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(gameBoard);
        } catch (PitOutOfRangeException  e ) {
            ErrorDTO dto = new ErrorDTO(e.getMessage());
            return ServerResponse
                    .status(461)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dto);
        }catch (IsKalahPitException  e ) {
            ErrorDTO dto = new ErrorDTO(e.getMessage());
            return ServerResponse
                    .status(462)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dto);
        }catch (PitEmptyException  e ) {
            ErrorDTO dto = new ErrorDTO(e.getMessage());
            return ServerResponse
                    .status(463)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dto);
        }catch (InvalidGameIdException e ) {
            ErrorDTO dto = new ErrorDTO(e.getMessage());
            return ServerResponse
                    .status(464)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dto);
        }
    }
}
