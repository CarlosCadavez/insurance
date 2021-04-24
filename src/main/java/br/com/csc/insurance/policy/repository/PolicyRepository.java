package br.com.csc.insurance.policy.repository;

import br.com.csc.insurance.policy.entity.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PolicyRepository extends MongoRepository<Policy, String> {
}
