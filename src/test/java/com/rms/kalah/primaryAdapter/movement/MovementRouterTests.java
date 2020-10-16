package com.rms.kalah.primaryAdapter.movement;

import com.rms.kalah.domain.GameBoard;
import com.rms.kalah.domain.Pit;
import com.rms.kalah.error.ErrorDTO;
import com.rms.kalah.primaryAdapter.movement.dto.BoardStatusDTO;
import com.rms.kalah.rules.KalahGameMotorImpl;
import com.rms.kalah.secondaryAdapter.GameStatusInMemoryImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * APIs testing, with remote server running. Test all REST endpoints Expose
 */
@ContextConfiguration(classes = {MovementRouterImpl.class, MovementHandlerImpl.class, KalahGameMotorImpl.class, GameStatusInMemoryImpl.class})
@WebFluxTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovementRouterTests {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private GameStatusInMemoryImpl repo;

    @Test
    @Order(1)
    @DisplayName("execute movement")
    @Tag("whiteBox")
    void testMovement() {
        repo.createGame();

        Flux<BoardStatusDTO> msg = WebTestClient
                .bindToApplicationContext(context)
                .build()
                .put()
                .uri("/games/1/pits/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(BoardStatusDTO.class).getResponseBody()
                .log();

        GameBoard boardExpected = new GameBoard();

        HashMap<Integer, Pit> board = createExpectedDataMovePit1();

        boardExpected.setBoard(board);

        BoardStatusDTO dto = new BoardStatusDTO(1, InetAddress.getLoopbackAddress().getHostAddress(),
                InetAddress.getLoopbackAddress().getHostName(), boardExpected );

        StepVerifier.create(msg)
                .expectNext(dto)
                .verifyComplete();
    }

    private HashMap<Integer, Pit> createExpectedDataMovePit1() {
        HashMap<Integer, Pit> board = new HashMap<>();
        Pit pit1 = new Pit(1,0);
        Pit pit2 = new Pit(2,7);
        Pit pit3 = new Pit(3,7);
        Pit pit4 = new Pit(4,7);
        Pit pit5 = new Pit(5,7);
        Pit pit6 = new Pit(6,7);
        Pit pit7 = new Pit(7,1);
        Pit pit8 = new Pit(8,6);
        Pit pit9 = new Pit(9,6);
        Pit pit10 = new Pit(10,6);
        Pit pit11 = new Pit(11,6);
        Pit pit12 = new Pit(12,6);
        Pit pit13 = new Pit(13,6);
        Pit pit14 = new Pit(14,0);
        board.put(1, pit1);
        board.put(2, pit2);
        board.put(3, pit3);
        board.put(4, pit4);
        board.put(5, pit5);
        board.put(6, pit6);
        board.put(7, pit7);
        board.put(8, pit8);
        board.put(9, pit9);
        board.put(10, pit10);
        board.put(11, pit11);
        board.put(12, pit12);
        board.put(13, pit13);
        board.put(14, pit14);
        return board;
    }

    @ParameterizedTest
    @CsvSource({
            "/games/1/pits/34, Pit out of Range",
            "/games/1/pits/14, Pit is a Kalah",
            "/games/1/pits/7, Pit is a Kalah",
            "/games/9/pits/1, Invalid GameId",
            "/games/9/pits/a, Invalid Parameters: gameId or pitId it's not a number.",
            "/games/a/pits/1, Invalid Parameters: gameId or pitId it's not a number.",
            "/games/a/pits/a, Invalid Parameters: gameId or pitId it's not a number.",
    })
    @Order(5)
    @DisplayName("Errors Messages")
    @Tag("whiteBox")
    void testMovementWithPitIdOutOfRange(String url, String message) {

        repo.createGame();

        Flux<ErrorDTO> msg = WebTestClient
                .bindToApplicationContext(context)
                .build()
                .put()
                .uri(url)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(ErrorDTO.class).getResponseBody()
                .log();

        ErrorDTO dto = new ErrorDTO(message);

        StepVerifier.create(msg)
                .expectNext(dto)
                .verifyComplete();
    }
}