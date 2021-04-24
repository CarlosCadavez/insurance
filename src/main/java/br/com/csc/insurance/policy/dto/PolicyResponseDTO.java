package br.com.csc.insurance.policy.dto;

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

    private String policyNumber;
    private LocalDate startPolicyPeriod;
    private LocalDate endPolicyPeriod;
    private String licensePlate;
    private BigDecimal amount;
    private boolean overduePolicy;
    private long daysOverdue;
    private long daysForPayment;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }

}
