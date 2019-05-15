package com.acmegames.game.rockpaperscissors;

import com.acmegames.game.rockpaperscissors.command.PlayCommand;
import com.acmegames.game.rockpaperscissors.command.StatsCommand;
import com.acmegames.game.rockpaperscissors.controller.PlayController;
import com.acmegames.game.rockpaperscissors.controller.StatisticsController;
import com.acmegames.game.rockpaperscissors.service.ItemSelectionService;
import com.acmegames.game.rockpaperscissors.service.PlayService;
import com.acmegames.game.rockpaperscissors.service.PredictionService;
import com.acmegames.game.rockpaperscissors.service.StatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public PredictionService predictionService() {
        return new PredictionService();
    }

    @Bean
    public StatisticsService statisticsService() {
        return new StatisticsService();
    }

    @Bean
    public ItemSelectionService itemSelectionService() {
        return new ItemSelectionService(predictionService());
    }

    @Bean
    public PlayService playService() {
        return new PlayService(itemSelectionService(), predictionService(), statisticsService());
    }

    @Bean
    public PlayCommand playCommand() {
        return new PlayCommand(playService());
    }

    @Bean
    public StatsCommand statsCommand() {
        return new StatsCommand(statisticsService());
    }

    @Bean
    public PlayController playController() {
        return new PlayController(playService());
    }

    @Bean
    public StatisticsController statisticsController() {
        return new StatisticsController(statisticsService());
    }
}
