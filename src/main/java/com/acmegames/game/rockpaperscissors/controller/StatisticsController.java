package com.acmegames.game.rockpaperscissors.controller;

import com.acmegames.game.rockpaperscissors.model.Statistics;
import com.acmegames.game.rockpaperscissors.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/stats")
    public Statistics getStats() {
        return statisticsService.getStatistics();
    }
}
