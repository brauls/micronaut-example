package de.brauls.example;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogflowRequestPayload {
    private final @NotBlank String responseId;
    private final @NotBlank String session;
    private final @Valid @NotNull DialogflowQueryResult queryResult;

    @JsonCreator(mode = Mode.PROPERTIES)
    public DialogflowRequestPayload(
        @JsonProperty("responseId") final String responseId,
        @JsonProperty("session") final String session,
        @JsonProperty("queryResult") final DialogflowQueryResult queryResult) {

        this.responseId = responseId;
        this.session = session;
        this.queryResult = queryResult;
    }

    public String getResponseId() {
        return responseId;
    }

    public String getSession() {
        return session;
    }

    public DialogflowQueryResult getQueryResult() {
        return queryResult;
    }
}
