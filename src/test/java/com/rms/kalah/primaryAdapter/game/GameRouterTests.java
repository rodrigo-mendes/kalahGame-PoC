package com.rms.kalah.primaryAdapter.game;

import com.rms.kalah.primaryAdapter.game.dto.GameDTO;
import com.rms.kalah.rules.KalahGameMotorImpl;
import com.rms.kalah.secondaryAdapter.GameStatusInMemoryImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.InetAddress;

/**
 * APIs testing, with remote server running. Test all REST endpoints Expose
 */
@ContextConfiguration(classes = {GameRouterImpl.class, GameHandlerImpl.class, KalahGameMotorImpl.class, GameStatusInMemoryImpl.class})
@WebFluxTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameRouterTests {

    @Autowired
    private ApplicationContext context;

    @Test
    @Order(1)
    @DisplayName("Create a Game")
    @Tag("whiteBox")
    void testCreateGameContext() {
        Flux<GameDTO> msg = WebTestClient
                .bindToApplicationContext(context)
                .build()
                .post()
                .uri("/games")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(GameDTO.class).getResponseBody()
                .log();

        GameDTO dto = new GameDTO(1, InetAddress.getLoopbackAddress().getHostAddress(),
                InetAddress.getLoopbackAddress().getHostName());

        StepVerifier.create(msg)
                .expectNext(dto)
                .verifyComplete();
    }
}