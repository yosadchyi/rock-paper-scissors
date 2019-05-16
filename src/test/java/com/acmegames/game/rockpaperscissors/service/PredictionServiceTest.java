package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PredictionServiceTest {
    @Test
    public void shouldNotBeAbleToPredictBeforeAnyMovesDone() {
        var service = new PredictionService();

        assertThat(service.canPredictMove()).isFalse();
        assertThatThrownBy(service::predictNextMove).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void shouldBeAbleToPredictAfterAnyMovesDone() {
        var service = new PredictionService();

        service.recordMove(Item.ROCK);
        assertThat(service.canPredictMove()).isTrue();
        assertThat(service.predictNextMove()).isEqualTo(Item.ROCK);
    }

    @Test
    public void shouldBeAbleToPredictRepeatingSequence() {
        var service = new PredictionService();

        service.recordMove(Item.ROCK);
        service.recordMove(Item.PAPER);
        service.recordMove(Item.SCISSORS);

        assertThat(service.predictNextMove()).isEqualTo(Item.ROCK);
        service.recordMove(Item.ROCK);
        assertThat(service.predictNextMove()).isEqualTo(Item.PAPER);
        service.recordMove(Item.PAPER);
        assertThat(service.predictNextMove()).isEqualTo(Item.SCISSORS);
    }
}