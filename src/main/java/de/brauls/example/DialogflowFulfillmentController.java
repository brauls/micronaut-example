package de.brauls.example;

import java.util.List;

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

    private final DialogflowFulfillmentService fulfillmentService;

    public DialogflowFulfillmentController(final DialogflowFulfillmentService fulfillmentService) {
        this.fulfillmentService = fulfillmentService;
    }

    @Post("/")
    @Consumes("application/json")
    @Produces("application/json")
    public List<String> dialogflowEntryPoint(@Body final @Valid DialogflowRequestPayload payload) {
        final var ingredients = fulfillmentService.getIngredients();
        return ingredients;
    }
}
