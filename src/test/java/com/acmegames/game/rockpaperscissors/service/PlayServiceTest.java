package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.Play;
import com.acmegames.game.rockpaperscissors.model.PlayInput;
import com.acmegames.game.rockpaperscissors.model.Statistics;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayServiceTest {
    @Test
    public void rockShouldBeatPaper() {
        var predictionService = new PredictionService();
        var itemSelectionService = mock(ItemSelectionService.class);
        var statisticsService = new StatisticsService();
        var playService = new PlayService(itemSelectionService, predictionService, statisticsService);

        when(itemSelectionService.selectItem()).thenReturn(Item.ROCK);
        assertThat(playService.play(new PlayInput(Item.PAPER)))
                .isEqualTo(new Play(0, Item.PAPER, Item.ROCK, Outcome.WIN));
        assertThat(statisticsService.getStatistics())
                .isEqualTo(new Statistics(1, 0, 1, 0));
        assertThat(predictionService.canPredictMove()).isTrue();
    }

    @Test
    public void scissorsShouldBeatPaper() {
        var predictionService = new PredictionService();
        var itemSelectionService = mock(ItemSelectionService.class);
        var statisticsService = new StatisticsService();
        var playService = new PlayService(itemSelectionService, predictionService, statisticsService);

        when(itemSelectionService.selectItem()).thenReturn(Item.SCISSORS);
        assertThat(playService.play(new PlayInput(Item.PAPER)))
                .isEqualTo(new Play(0, Item.PAPER, Item.SCISSORS, Outcome.LOSE));
        assertThat(statisticsService.getStatistics())
                .isEqualTo(new Statistics(1, 1, 0, 0));
        assertThat(predictionService.canPredictMove()).isTrue();
    }

    @Test
    public void sameItemsShouldGiveTie() {
        var predictionService = new PredictionService();
        var itemSelectionService = mock(ItemSelectionService.class);
        var statisticsService = new StatisticsService();
        var playService = new PlayService(itemSelectionService, predictionService, statisticsService);

        when(itemSelectionService.selectItem()).thenReturn(Item.SCISSORS);
        assertThat(playService.play(new PlayInput(Item.SCISSORS)))
                .isEqualTo(new Play(0, Item.SCISSORS, Item.SCISSORS, Outcome.TIE));
        assertThat(statisticsService.getStatistics())
                .isEqualTo(new Statistics(1, 0, 0, 1));
        assertThat(predictionService.canPredictMove()).isTrue();
    }

    @Test
    public void playCanBeFoundById() {
        var predictionService = new PredictionService();
        var itemSelectionService = mock(ItemSelectionService.class);
        var statisticsService = new StatisticsService();
        var playService = new PlayService(itemSelectionService, predictionService, statisticsService);

        when(itemSelectionService.selectItem()).thenReturn(Item.SCISSORS);
        playService.play(new PlayInput(Item.SCISSORS));

        assertThat(playService.getPlay(0))
                .isEqualTo(Optional.of(new Play(0, Item.SCISSORS, Item.SCISSORS, Outcome.TIE)));
    }

    @Test
    public void emptyOptionalShouldBeReturnedIfNoPlayExists() {
        var predictionService = new PredictionService();
        var itemSelectionService = mock(ItemSelectionService.class);
        var statisticsService = new StatisticsService();
        var playService = new PlayService(itemSelectionService, predictionService, statisticsService);

        assertThat(playService.getPlay(0)).isEqualTo(Optional.empty());
    }

    @Test
    public void listOfPlaysShouldBeReturned() {
        var predictionService = new PredictionService();
        var itemSelectionService = mock(ItemSelectionService.class);
        var statisticsService = new StatisticsService();
        var playService = new PlayService(itemSelectionService, predictionService, statisticsService);

        when(itemSelectionService.selectItem()).thenReturn(Item.SCISSORS);
        playService.play(new PlayInput(Item.SCISSORS));
        playService.play(new PlayInput(Item.ROCK));
        playService.play(new PlayInput(Item.PAPER));

        final Play play1 = new Play(0, Item.SCISSORS, Item.SCISSORS, Outcome.TIE);
        final Play play2 = new Play(1, Item.ROCK, Item.SCISSORS, Outcome.WIN);
        final Play play3 = new Play(2, Item.PAPER, Item.SCISSORS, Outcome.LOSE);
        assertThat(playService.getPlays()).isEqualTo(List.of(play1, play2, play3));
    }
}