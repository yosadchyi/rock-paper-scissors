package com.acmegames.game.rockpaperscissors.model;

import java.util.Set;

public enum Item {
    ROCK,
    PAPER,
    SCISSORS;

    public boolean losesTo(final Item other) {
        return losesTo.contains(other);
    }

    public Set<Item> losesTo() {
        return losesTo;
    }

    private Set<Item> losesTo;

    static {
        ROCK.losesTo = Set.of(PAPER);
        PAPER.losesTo = Set.of(SCISSORS);
        SCISSORS.losesTo = Set.of(ROCK);
    }

    public static int size() {
        return values().length;
    }
}
