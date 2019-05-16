package com.acmegames.game.rockpaperscissors.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Statistics {
    private int games;
    private int loses;
    private int wins;
    private int ties;
}
