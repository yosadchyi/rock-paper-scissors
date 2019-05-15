package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.Play;
import org.springframework.stereotype.Service;

@Service
public class PlayService {
    private final ItemSelectionService itemSelectionService;
    private final PredictionService predictionService;
    private final StatisticsService statisticsService;

    public PlayService(final ItemSelectionService itemSelectionService,
                       final PredictionService predictionService,
                       final StatisticsService statisticsService) {
        this.itemSelectionService = itemSelectionService;
        this.predictionService = predictionService;
        this.statisticsService = statisticsService;
    }

    public Play play(final Item userSelectedItem) {
        var computerSelectedItem = itemSelectionService.selectItem();
        var outcome = computeOutcome(userSelectedItem, computerSelectedItem);

        statisticsService.recordMove(outcome);
        predictionService.recordMove(userSelectedItem);
        return new Play(userSelectedItem, computerSelectedItem, outcome);
    }

    private Outcome computeOutcome(final Item userSelectedItem, final Item computerSelectedItem) {
        if (userSelectedItem == computerSelectedItem) {
            return Outcome.TIE;
        }
        return computerSelectedItem.losesTo(userSelectedItem) ? Outcome.WIN : Outcome.LOSE;
    }
}
