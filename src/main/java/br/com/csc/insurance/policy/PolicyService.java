package br.com.csc.insurance.policy;

import br.com.csc.insurance.policy.dto.PolicyResponseDTO;
import br.com.csc.insurance.policy.entity.Policy;
import br.com.csc.insurance.policy.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PolicyService {

    private final PolicyRepository repository;

    public List<PolicyResponseDTO> policies() {
        return repository
                .findAll()
                .stream()
                .map(this::buildPolicy)
                .collect(Collectors.toList());
    }

    private PolicyResponseDTO buildPolicy(Policy policy) {
        return PolicyResponseDTO.builder()
                .endPolicyPeriod(policy.getEndPolicyPeriod())
                .Id(policy.getId())
                .amount(policy.getAmount())
                .policyNumber(policy.getPolicyNumber())
                .startPolicyPeriod(policy.getStartPolicyPeriod())
                .licensePlate(policy.getLicensePlate())
                .build();
    }
}