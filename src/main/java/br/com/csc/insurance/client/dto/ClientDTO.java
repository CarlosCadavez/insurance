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
@JsonDeserialize(builder = ClientDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ClientDTO {

    @NotNull
    private String name;
    @CPF
    private String cpf;
    @NotNull
    private String city;
    @NotNull
    @Size(min = 2, max = 2)
    private String federationUnity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }

}
