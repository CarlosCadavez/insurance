package br.com.csc.insurance.client.repository;

import br.com.csc.insurance.client.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    List<Client> findByCpf(String cpf);
}

