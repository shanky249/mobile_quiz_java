package com.logitech.service;

import com.logitech.model.Stats;
import com.logitech.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsService.class);

    private final StatsRepository statsRepository;

    public void saveStats(Stats stats) {
        LOGGER.info("Saving statistics for game with ID: {}", stats.getGameId());
        statsRepository.save(stats);
        LOGGER.info("Statistics saved successfully for game with ID: {}", stats.getGameId());
    }
}
