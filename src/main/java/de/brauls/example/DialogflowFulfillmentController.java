package de.brauls.example;

import java.util.Collection;

import javax.validation.Valid;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;

@Validated
@Controller("/dialogflow")
public class DialogflowFulfillmentController {
    private static final String FULFILLMENT_TEXT_FORMAT = "Available ingredients are: %s";
    private static final String FULFILLMENT_SOURCE = "micronaut-example";
    private static final String INGREDIENT_SEPARATION_FORMAT = "%s, %s";

    private final DialogflowFulfillmentService fulfillmentService;

    public DialogflowFulfillmentController(final DialogflowFulfillmentService fulfillmentService) {
        this.fulfillmentService = fulfillmentService;
    }

    @Post("/")
    @Consumes("application/json")
    @Produces("application/json")
    public DialogflowResponsePayload dialogflowEntryPoint(@Body final @Valid DialogflowRequestPayload payload) {
        final var ingredients = fulfillmentService.getIngredients();
        return responseFromIngredientList(ingredients);
    }

    static DialogflowResponsePayload responseFromIngredientList(final Collection<String> ingredients) {
        final var ingredientsListing = ingredients.stream()
            .reduce("", (intermediateResult, ingredient) -> {
                if (intermediateResult.isEmpty()) {
                    return ingredient;
                }
                return String.format(INGREDIENT_SEPARATION_FORMAT, intermediateResult, ingredient);
            });

        return new DialogflowResponsePayload(
            String.format(FULFILLMENT_TEXT_FORMAT, ingredientsListing),
            FULFILLMENT_SOURCE
        );
    }
}
