package com.acmegames.game.rockpaperscissors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Statistics {
    private int games;
    private int loses;
    private int wins;
    private int ties;
}
