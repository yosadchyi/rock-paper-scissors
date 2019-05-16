package com.acmegames.game.rockpaperscissors.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PlayInput {
    public final Item selectedItem;

    @JsonCreator
    public PlayInput(final Item selectedItem) {
        this.selectedItem = selectedItem;
    }
}
