package de.brauls.example;

import java.util.List;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("${ingredients.base-url}")
public interface IngredientsClient {

    @Get("/api/v1/ingredients")
    List<Ingredient> fetchIngredients();
}
