package de.brauls.example;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogflowResponsePayload {
    private final String fulfillmentText;
    private final String source;

    @JsonCreator
    public DialogflowResponsePayload(
        @JsonProperty("fulfillmentText") final String fulfillmentText,
        @JsonProperty("source") final String source) {

        this.fulfillmentText = fulfillmentText;
        this.source = source;
    }

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public String getSource() {
        return source;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DialogflowResponsePayload that = (DialogflowResponsePayload) o;
        return Objects.equals(fulfillmentText, that.fulfillmentText) &&
               Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fulfillmentText, source);
    }
}
