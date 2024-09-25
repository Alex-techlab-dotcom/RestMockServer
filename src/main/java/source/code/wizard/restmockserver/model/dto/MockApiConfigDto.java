package source.code.wizard.restmockserver.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public record MockApiConfigDto(
        @JsonProperty(access = READ_ONLY)
        long id,
        String requestUrl,
        @Pattern(regexp = "GET|POST|PUT|DELETE")
        String method,
        Object response
) { }
