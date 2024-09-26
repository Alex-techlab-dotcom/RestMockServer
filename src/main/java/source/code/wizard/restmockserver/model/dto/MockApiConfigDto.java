package source.code.wizard.restmockserver.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Builder
public record MockApiConfigDto(
        @JsonProperty(access = READ_ONLY)
        Long id,
        Integer statusCode,
        String requestUrl,
        @Pattern(regexp = "GET|POST|PUT|DELETE")
        String method,
        Object response
) {}
