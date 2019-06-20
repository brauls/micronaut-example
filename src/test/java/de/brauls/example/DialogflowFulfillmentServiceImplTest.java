package de.brauls.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DialogflowFulfillmentServiceImplTest {
    private static final List<String> INGREDIENTS = Arrays.asList("apple", "banana");

    private DialogflowFulfillmentServiceImpl serviceUnderTest;

    private IngredientsClient ingredientsClient;

    @BeforeEach
    void setUp() {
        ingredientsClient = mock(IngredientsClient.class);
        serviceUnderTest = new DialogflowFulfillmentServiceImpl(ingredientsClient);
    }

    @Test
    void getIngredients_callApi_andReturnsApiResult() {
        when(ingredientsClient.fetchIngredients()).thenReturn(ingredients());

        final var actualIngredients = serviceUnderTest.getIngredients();

        assertEquals(INGREDIENTS, actualIngredients);
    }

    private List<Ingredient> ingredients() {
        return INGREDIENTS.stream()
            .map(Ingredient::new)
            .collect(Collectors.toList());
    }
}
