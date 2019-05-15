package com.acmegames.game.rockpaperscissors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Play {
    private final int id;
    private final Item userSelectedItem;
    private final Item computerSelectedItem;
    private final Outcome outcome;
}

