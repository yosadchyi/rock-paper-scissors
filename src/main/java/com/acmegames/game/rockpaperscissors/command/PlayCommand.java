package com.acmegames.game.rockpaperscissors.command;

import com.acmegames.game.rockpaperscissors.model.Item;
import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.PlayInput;
import com.acmegames.game.rockpaperscissors.service.PlayService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Map;
import java.util.Objects;

@ShellComponent
public class PlayCommand {
    private static final Map<Outcome, String> OUTCOME_TO_MESSAGE = Map.of(
            Outcome.TIE, "Tie.",
            Outcome.WIN, "You win!",
            Outcome.LOSE, "You lose :("
    );

    private final PlayService playService;

    public PlayCommand(final PlayService playService) {
        this.playService = playService;
    }

    @ShellMethod("Play with computer, requires item as input {ROCK|PAPER|SCISSORS}")
    public String play(final Item userSelectedItem) {
        Objects.requireNonNull(userSelectedItem);

        var play = playService.play(new PlayInput(userSelectedItem));
        var buffer = new StringBuffer();

        buffer.append(String.format("You selected %s%n", play.getUserSelectedItem()));
        buffer.append(String.format("Computer selected %s%n", play.getComputerSelectedItem()));
        buffer.append(OUTCOME_TO_MESSAGE.get(play.getOutcome()));
        buffer.append(System.lineSeparator());
        return buffer.toString();
    }
}
