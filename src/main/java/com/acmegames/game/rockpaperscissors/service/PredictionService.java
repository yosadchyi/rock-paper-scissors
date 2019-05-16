package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PredictionService {
    private int moves;
    private Item lastItem;
    private final int[][] markovChain = new int[Item.size()][Item.size()];

    public void recordMove(final Item item) {
        Objects.requireNonNull(item);

        if (moves > 0) {
            markovChain[lastItem.ordinal()][item.ordinal()]++;
        }
        lastItem = item;
        moves++;
    }

    public boolean canPredictMove() {
        return moves > 0;
    }

    public Item predictNextMove() {
        if (!canPredictMove()) {
            throw new IllegalStateException("No records to predict move.");
        }

        var nextIndex = 0;
        var previousIndex = lastItem.ordinal();
        var chain = markovChain[previousIndex];

        for (var i = 0; i < Item.size(); i++) {
            if (chain[i] > chain[nextIndex]) {
                nextIndex = i;
            }
        }

        return Item.values()[nextIndex];
    }
}
