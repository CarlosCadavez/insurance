package br.com.csc.insurance.policy.service;

import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.client.exception.ClientNotFoundException;
import br.com.csc.insurance.client.repository.ClientRepository;
import br.com.csc.insurance.policy.dto.PolicyDTO;
import br.com.csc.insurance.policy.dto.PolicyResponseDTO;
import br.com.csc.insurance.policy.entity.Policy;
import br.com.csc.insurance.policy.exception.PolicyNotFoundException;
import br.com.csc.insurance.policy.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final ClientRepository clientRepository;

    public List<PolicyResponseDTO> policies() {
        return policyRepository
                .findAll()
                .stream()
                .map(this::buildPolicy)
                .collect(Collectors.toList());
    }

    public PolicyResponseDTO addPolicy(PolicyDTO policyDTO) {
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(policyDTO.getClientId()).orElseThrow(ClientNotFoundException::new));
        Policy policyToSave = convertToSave(policyDTO);
        policyToSave.setClient(client.get());
        Policy policy = policyRepository.save(policyToSave);
        return buildPolicy(policy);
    }

    public PolicyResponseDTO update(String policyNumber, PolicyDTO policyDTO) {
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(policyDTO.getClientId()).orElseThrow(ClientNotFoundException::new));
        Optional<Policy> policySaved = Optional.ofNullable(policyRepository.findByPolicyNumber(policyNumber).orElseThrow(PolicyNotFoundException::new));
        Policy policyToSave = convertToSave(policyDTO);
        policyToSave.setPolicyNumber(policySaved.get().getPolicyNumber());
        policyToSave.setId(policySaved.get().getId());
        policyToSave.setClient(client.get());
        return buildPolicy(policyRepository.save(policyToSave));
    }

    public void delete(String policyNumber) {
        Optional.ofNullable(policyRepository.findByPolicyNumber(policyNumber).orElseThrow(PolicyNotFoundException::new));
        policyRepository.deleteByPolicyNumber(policyNumber);
    }

    public PolicyResponseDTO policyByNumber(String policyNumber) {
        Optional<Policy> policy = Optional.ofNullable(policyRepository.findByPolicyNumber(policyNumber).orElseThrow(PolicyNotFoundException::new));
        return buildPolicy(policy.get());
    }

    private PolicyResponseDTO buildPolicy(Policy policy) {
        return PolicyResponseDTO
                .builder()
                .licensePlate(policy.getLicensePlate())
                .policyNumber(policy.getPolicyNumber())
                .endPolicyPeriod(policy.getEndPolicyPeriod())
                .startPolicyPeriod(policy.getStartPolicyPeriod())
                .amount(policy.getAmount())
                .overduePolicy(policy.overduePolicy())
                .daysOverdue(policy.daysOverdue())
                .daysForPayment(policy.daysForPayment())
                .build();
    }

    private Policy convertToSave(PolicyDTO policyDTO) {
        return Policy
                .builder()
                .licensePlate(policyDTO.getLicensePlate())
                .policyNumber(UUID.randomUUID().toString())
                .endPolicyPeriod(policyDTO.getEndPolicyPeriod())
                .startPolicyPeriod(policyDTO.getStartPolicyPeriod())
                .amount(policyDTO.getAmount())
                .build();
    }

}