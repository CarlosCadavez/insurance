package br.com.csc.insurance.client.service;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.client.exception.ClientAlreadyExistsException;
import br.com.csc.insurance.client.repository.ClientRepository;
import br.com.csc.insurance.client.validator.ClientValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientValidator validator;
    private final ClientRepository clientRepository;

    public List<ClientResponseDTO> clients() {
        return clientRepository
                .findAll()
                .stream()
                .map(client -> buildClient(client))
                .collect(Collectors.toList());
    }

    public ClientResponseDTO addClient(ClientDTO clientDTO) throws ClientAlreadyExistsException {
        validator.canBeAdded(clientDTO);
        Client client = clientRepository.save(convertToSave(clientDTO));
        return buildClient(client);
    }

    private Client convertToSave(ClientDTO clientDTO) {
        return new ModelMapper().map(clientDTO, Client.class);
    }

    private ClientResponseDTO buildClient(Client client) {
        return ClientResponseDTO.builder()
                .name(client.getName())
                .city(client.getCity())
                .cpf(client.getCpf())
                .federationUnity(client.getFederationUnity())
                .id(client.getId())
                .build();
    }
}
