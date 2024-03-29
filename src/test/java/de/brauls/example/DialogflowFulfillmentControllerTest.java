package de.brauls.example;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import static de.brauls.example.DialogflowFulfillmentController.responseFromIngredientList;

@MicronautTest
class DialogflowFulfillmentControllerTest {
    private static final String DIALOGFLOW_URI = "/dialogflow";

    private static final String QUERY_TEXT = "queryText";
    private static final Boolean REQUIRED_PARAMS_PRESENT = true;
    private static final String RESPONSE_ID = "response-1";
    private static final String SESSION_ID = "session-1";

    private static final List<String> INGREDIENTS = Arrays.asList("apple", "banana");

    @Inject
    @Client("/")
    private RxHttpClient client;

    @Inject
    private DialogflowFulfillmentService fulfillmentService;

    @Test
    void requestToFulfillment_withValidPayload_callsFulfillmentService() {
        when(fulfillmentService.getIngredients()).thenReturn(INGREDIENTS);

        final var payload = validPayload();
        final var request = HttpRequest.POST(DIALOGFLOW_URI, payload);
        final var response = client.toBlocking().exchange(request, DialogflowResponsePayload.class);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertTrue(response.getBody().isPresent());
        assertEquals(responseFromIngredientList(INGREDIENTS), response.getBody().get());
    }

    @Test
    void requestToFulfillment_withInvalidPayload_statusBadRequest() {
        final var payload = inValidPayload();
        final var request = HttpRequest.POST(DIALOGFLOW_URI, payload);

        assertThrows(HttpClientResponseException.class, () -> client.toBlocking().exchange(request, String.class));
    }

    @MockBean(DialogflowFulfillmentServiceImpl.class)
    DialogflowFulfillmentService fulfillmentService() {
        return mock(DialogflowFulfillmentService.class);
    }

    private DialogflowRequestPayload validPayload() {
        final var queryResult = new DialogflowQueryResult(QUERY_TEXT, REQUIRED_PARAMS_PRESENT);
        return new DialogflowRequestPayload(RESPONSE_ID, SESSION_ID, queryResult);
    }

    private DialogflowRequestPayload inValidPayload() {
        final var queryResult = new DialogflowQueryResult(QUERY_TEXT, REQUIRED_PARAMS_PRESENT);
        return new DialogflowRequestPayload(null, SESSION_ID, queryResult);
    }
}
