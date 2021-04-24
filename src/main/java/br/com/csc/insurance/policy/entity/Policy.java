package br.com.csc.insurance.policy.entity;

import br.com.csc.insurance.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "policy")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id
    private String Id;
    private String policyNumber;
    private LocalDate startPolicyPeriod;
    private LocalDate endPolicyPeriod;
    @DBRef(lazy = true)
    private Client client;
    private String licensePlate;
    private BigDecimal amount;

}
