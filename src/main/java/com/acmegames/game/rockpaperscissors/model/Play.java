package com.acmegames.game.rockpaperscissors.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Play {
    private final int id;
    private final Item userSelectedItem;
    private final Item computerSelectedItem;
    private final Outcome outcome;
}
