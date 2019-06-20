package de.brauls.example;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogflowQueryResult {
    private final @NotBlank String queryText;
    private final @NotBlank String fulfillmentText;

    @JsonCreator(mode = Mode.PROPERTIES)
    public DialogflowQueryResult(
        @JsonProperty("queryText") final String queryText,
        @JsonProperty("fulfillmentText") final String fulfillmentText) {

        this.queryText = queryText;
        this.fulfillmentText = fulfillmentText;
    }

    public String getQueryText() {
        return queryText;
    }

    public String getFulfillmentText() {
        return fulfillmentText;
    }
}
