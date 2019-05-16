package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.Play;
import com.acmegames.game.rockpaperscissors.model.PlayInput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayService {
    private final ItemSelectionService itemSelectionService;
    private final PredictionService predictionService;
    private final StatisticsService statisticsService;
    private final List<Play> plays = new ArrayList<>();

    public PlayService(final ItemSelectionService itemSelectionService,
                       final PredictionService predictionService,
                       final StatisticsService statisticsService) {
        this.itemSelectionService = itemSelectionService;
        this.predictionService = predictionService;
        this.statisticsService = statisticsService;
    }

    public List<Play> getPlays() {
        return List.copyOf(plays);
    }

    public Optional<Play> getPlay(final int id) {
        if (id < 0 || id >= plays.size()) {
            return Optional.empty();
        }
        return Optional.of(plays.get(id));
    }

    public Play play(final PlayInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.selectedItem);

        var userSelectedItem = input.selectedItem;
        var computerSelectedItem = itemSelectionService.selectItem();
        var outcome = computeOutcome(userSelectedItem, computerSelectedItem);

        statisticsService.recordMove(outcome);
        predictionService.recordMove(userSelectedItem);

        var play = new Play(plays.size(), userSelectedItem, computerSelectedItem, outcome);
        plays.add(play);
        return play;
    }

    private Outcome computeOutcome(final Item userSelectedItem, final Item computerSelectedItem) {
        if (userSelectedItem == computerSelectedItem) {
            return Outcome.TIE;
        }
        return computerSelectedItem.losesTo(userSelectedItem) ? Outcome.WIN : Outcome.LOSE;
    }
}
