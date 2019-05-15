package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Outcome;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class StatisticsService {
    private int games;
    private int loses;
    private int wins;
    private int ties;

    public void recordMove(Outcome outcome) {
        games++;
        switch (outcome) {
            case WIN:
                wins++;
                break;
            case LOSE:
                loses++;
                break;
            case TIE:
                ties++;
                break;
        }
    }
}

