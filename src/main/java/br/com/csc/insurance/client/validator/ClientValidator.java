package br.com.csc.insurance.client.validator;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.client.exception.ClientAlreadyExistsException;
import br.com.csc.insurance.client.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@AllArgsConstructor
public class ClientValidator {

    private final ClientRepository repository;

    public void canBeAdded(ClientDTO clientDTO) throws ClientAlreadyExistsException {
        List<Client> listClient = repository.findByCpf(clientDTO.getCpf());
        if (!CollectionUtils.isEmpty(listClient)) {
            throw new ClientAlreadyExistsException(String.format("The client with cpf: %s already exists on database", clientDTO.getCpf()));
        }
    }

}
