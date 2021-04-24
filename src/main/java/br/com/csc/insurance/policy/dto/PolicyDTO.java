package br.com.csc.insurance.policy.dto;

import br.com.csc.insurance.client.dto.ClientDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@With
@JsonDeserialize(builder = PolicyDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PolicyDTO {

    @NotNull
    private String clientId;
    @NotNull
    private LocalDate startPolicyPeriod;
    @NotNull
    private LocalDate endPolicyPeriod;
    @NotNull
    private String licensePlate;
    @NotNull
    private BigDecimal amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }

}
