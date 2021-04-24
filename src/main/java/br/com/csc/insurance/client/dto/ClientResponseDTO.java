package br.com.csc.insurance.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@With
@JsonDeserialize(builder = ClientResponseDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ClientResponseDTO {

    private String id;
    private String name;
    private String cpf;
    private String city;
    private String federationUnity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }

}
