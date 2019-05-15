package com.acmegames.game.rockpaperscissors.command;

import com.acmegames.game.rockpaperscissors.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StatsCommand {
    private final StatisticsService statisticsService;

    public StatsCommand(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @ShellMethod("Get statistics about played games")
    public String stats() {
        var buffer = new StringBuffer();
        var stats = statisticsService.getStatistics();
        var wins = stats.getWins();
        var loses = stats.getLoses();
        var ties = stats.getTies();
        var games = stats.getGames();

        if (games > 0) {
            buffer.append(String.format("Games played: %d%n", games));
            buffer.append(String.format("Wins: %d (%.2f%%)%n", wins, 100.0 * wins / (double) games));
            buffer.append(String.format("Loses: %d (%.2f%%)%n", loses, 100.0 * loses / (double) games));
            buffer.append(String.format("Ties: %d (%.2f%%)%n", ties, 100.0 * ties / (double) games));
        } else {
            buffer.append("No games played");
            buffer.append(System.lineSeparator());
        }
        return buffer.toString();
    }
}
