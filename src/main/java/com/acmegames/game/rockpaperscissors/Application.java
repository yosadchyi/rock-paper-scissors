package com.acmegames.game.rockpaperscissors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class, GameConfiguration.class).build(args).run();
    }

}
