package app;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;


public class AppConfiguration extends io.dropwizard.Configuration {
    @NotEmpty
    private String defaultFunction = "annotate";

    @JsonProperty
    public String getDefaultFunction() {
        return defaultFunction;
    }

    @JsonProperty
    public void setDefaultNameString(String function) {
        this.defaultFunction = function;
    }

}