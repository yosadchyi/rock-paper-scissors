package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemSelectionServiceTest {
    @Test
    public void shouldGenerateRandomResultWhenPredictionCanNotBeDone() {
        var predictionService = mock(PredictionService.class);
        var random = mock(Random.class);
        var selectionService = new ItemSelectionService(predictionService, random);

        when(predictionService.canPredictMove()).thenReturn(false);
        when(random.nextInt(anyInt())).thenReturn(1);
        assertThat(selectionService.selectItem()).isEqualTo(Item.values()[1]);
    }

    @Test
    public void shouldReturnItemBeatingPredictedWhenPredictionCanBeDone() {
        var predictionService = mock(PredictionService.class);
        var random = mock(Random.class);
        var selectionService = new ItemSelectionService(predictionService, random);

        when(predictionService.canPredictMove()).thenReturn(true);
        when(predictionService.predictNextMove()).thenReturn(Item.SCISSORS);
        when(random.nextInt(anyInt())).thenReturn(0);
        assertThat(selectionService.selectItem()).isEqualTo(Item.ROCK);
    }
}