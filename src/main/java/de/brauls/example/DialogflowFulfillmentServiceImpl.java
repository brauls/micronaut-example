package de.brauls.example;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

@Singleton
public class DialogflowFulfillmentServiceImpl implements DialogflowFulfillmentService {

    private final IngredientsClient ingredientsClient;

    public DialogflowFulfillmentServiceImpl(final IngredientsClient ingredientsClient) {
        this.ingredientsClient = ingredientsClient;
    }

    @Override
    public List<String> getIngredients() {
        final var ingredientsFromApi = ingredientsClient.fetchIngredients();
        return ingredientsFromApi.stream()
            .map(Ingredient::getName)
            .collect(Collectors.toList());
    }
}
