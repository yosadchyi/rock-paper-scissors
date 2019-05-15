package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.Statistics;
import org.springframework.stereotype.Component;

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

    public Statistics getStatistics() {
        return new Statistics(games, loses, wins, ties);
    }
}

