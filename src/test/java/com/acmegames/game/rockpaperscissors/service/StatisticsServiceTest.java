package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.Statistics;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StatisticsServiceTest {

    @Test
    public void winOutcomeShouldIncreaseNumberOfWins() {
        var service = new StatisticsService();
        var expected = new Statistics(1, 0, 1, 0);

        service.recordMove(Outcome.WIN);
        assertThat(service.getStatistics()).isEqualTo(expected);
    }

    @Test
    public void loseOutcomeShouldIncreaseNumberOfLoses() {
        var service = new StatisticsService();
        var expected = new Statistics(1, 1, 0, 0);

        service.recordMove(Outcome.LOSE);
        assertThat(service.getStatistics()).isEqualTo(expected);
    }

    @Test
    public void tieOutcomeShouldIncreaseNumberOfTies() {
        var service = new StatisticsService();
        var expected = new Statistics(1, 0, 0, 1);

        service.recordMove(Outcome.TIE);
        assertThat(service.getStatistics()).isEqualTo(expected);
    }

    @Test
    public void nullOutcomeShouldThrowNullPointerException() {
        var service = new StatisticsService();

        assertThatNullPointerException().isThrownBy(() -> service.recordMove(null));
    }
}