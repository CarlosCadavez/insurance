package br.com.csc.insurance.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Value
@With
@JsonDeserialize(builder = ErrorResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ErrorResponse {

    private int code;
    private String status;
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }

}
