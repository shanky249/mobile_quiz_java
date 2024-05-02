package com.logitech.controller;

import com.logitech.model.Stats;
import com.logitech.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsController.class);

    private final StatsService statsService;

    @PostMapping("/stats/save")
    public ResponseEntity<String> saveStats(@RequestBody Stats stats) {
        LOGGER.info("Received request to save stats for game ID: {}", stats.getGameId());
        statsService.saveStats(stats);
        LOGGER.info("Stats saved successfully for game ID: {}", stats.getGameId());
        return ResponseEntity.ok("Stats saved successfully");
    }
}
