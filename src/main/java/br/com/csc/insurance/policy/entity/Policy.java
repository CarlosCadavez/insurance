package br.com.csc.insurance.policy.entity;

import br.com.csc.insurance.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Document(collection = "policy")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id
    private String id;
    @Field("policy_number")
    private String policyNumber;
    @Field("start_policy_period")
    private LocalDate startPolicyPeriod;
    @Field("end_policy_period")
    private LocalDate endPolicyPeriod;
    @DBRef(lazy = true)
    private Client client;
    @Field("license_plate")
    private String licensePlate;
    private BigDecimal amount;

    public boolean overduePolicy() {
        return endPolicyPeriod.isBefore(LocalDate.now());
    }

    public long daysOverdue() {
        boolean before = endPolicyPeriod.isBefore(LocalDate.now());
        return before ? Duration.between(endPolicyPeriod.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() : 0l;
    }

    public long daysForPayment() {
        boolean after = endPolicyPeriod.isAfter(LocalDate.now());
        return after ? Duration.between(LocalDate.now().atStartOfDay(), endPolicyPeriod.atStartOfDay()).toDays() : 0l;
    }

}
