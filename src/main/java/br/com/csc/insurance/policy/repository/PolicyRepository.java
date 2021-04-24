package br.com.csc.insurance.policy.repository;

import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.policy.entity.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends MongoRepository<Policy, String> {
    Optional<Policy> findByPolicyNumber(String policyNumber);

    void deleteByPolicyNumber(String policyNumber);

    List<Policy> findByClient(Client client);
}
