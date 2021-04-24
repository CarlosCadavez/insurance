package br.com.csc.insurance.policy.dto;

import br.com.csc.insurance.client.dto.ClientResponseDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@With
@JsonDeserialize(builder = PolicyResponseDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PolicyResponseDTO {

    private String Id;
    private String policyNumber;
    private LocalDate startPolicyPeriod;
    private LocalDate endPolicyPeriod;
    private String licensePlate;
    private BigDecimal amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }

    public int days() {
        return 8;
    }

}
