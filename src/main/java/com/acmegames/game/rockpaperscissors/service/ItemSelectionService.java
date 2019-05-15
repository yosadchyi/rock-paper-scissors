package com.acmegames.game.rockpaperscissors.service;

import com.acmegames.game.rockpaperscissors.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemSelectionService {
    private final PredictionService predictionService;
    private final Random rng = new Random();
    private final Map<Item, List<Item>> itemToLoses;

    public ItemSelectionService(final PredictionService predictionService) {
        this.predictionService = predictionService;
        this.itemToLoses = Stream.of(Item.values())
                .collect(Collectors.toMap(Function.identity(), i -> List.copyOf(i.losesTo())));
    }

    public Item selectItem() {
        if (predictionService.canPredictMove()) {
            var predictedItem = predictionService.predictNextMove();
            var losesTo = itemToLoses.get(predictedItem);

            return losesTo.get(rng.nextInt(losesTo.size()));
        }
        return randomMove();
    }

    private Item randomMove() {
        var values = Item.values();

        return values[rng.nextInt(values.length)];
    }
}
