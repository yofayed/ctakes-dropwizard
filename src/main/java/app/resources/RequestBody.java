package app.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {

    @JsonProperty
    private String text;

    public String getText() {
        return text;
    }
}
