package de.brauls.example;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogflowQueryResult {
    private final @NotBlank String queryText;
    private final @NotNull Boolean allRequiredParamsPresent;

    @JsonCreator(mode = Mode.PROPERTIES)
    public DialogflowQueryResult(
        @JsonProperty("queryText") final String queryText,
        @JsonProperty("allRequiredParamsPresent") final Boolean allRequiredParamsPresent) {

        this.queryText = queryText;
        this.allRequiredParamsPresent = allRequiredParamsPresent;
    }

    public String getQueryText() {
        return queryText;
    }

    public Boolean getAllRequiredParamsPresent() {
        return allRequiredParamsPresent;
    }
}
