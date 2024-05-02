package com.logitech.controller;

import com.logitech.model.Game;
import com.logitech.service.GameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameService gameService;

    @GetMapping("/game")
    public ResponseEntity<Game> getGame() {
        LOGGER.info("Request received to get a new game");
        Game game = gameService.getGame();
        LOGGER.info("New game created with ID: {}", game.getGameId());
        return ResponseEntity.ok(game);
    }

    @PostMapping("/game/{gameId}/lifeline/use")
    public ResponseEntity<Game> useLifelines(@PathVariable UUID gameId,
                                             @RequestParam String lifeline,
                                             @RequestParam Long questionId) {
        LOGGER.info("Request received to use lifeline for game ID: {}", gameId);
        Game game = gameService.useLifeline(gameId, lifeline, questionId);
        LOGGER.info("Lifeline used successfully for game ID: {}", gameId);
        return ResponseEntity.ok(game);
    }
}
