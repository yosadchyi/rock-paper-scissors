package com.acmegames.game.rockpaperscissors.controller;

import com.acmegames.game.rockpaperscissors.model.Play;
import com.acmegames.game.rockpaperscissors.model.PlayInput;
import com.acmegames.game.rockpaperscissors.service.PlayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayController {
    private final PlayService playService;

    public PlayController(final PlayService playService) {
        this.playService = playService;
    }

    @PostMapping("/play")
    public Play createPlay(final @RequestBody PlayInput item) {
        return playService.play(item);
    }

    @GetMapping("/play")
    public List<Play> getPlays() {
        return playService.getPlays();
    }

    @GetMapping("/play/{id}")
    public ResponseEntity<Play> getPlay(final @PathVariable int id) {
        return playService.getPlay(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
