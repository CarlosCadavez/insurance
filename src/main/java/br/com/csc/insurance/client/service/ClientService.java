package br.com.csc.insurance.client.service;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.client.exception.ClientAlreadyExistsException;
import br.com.csc.insurance.client.exception.ClientNotFoundException;
import br.com.csc.insurance.client.repository.ClientRepository;
import br.com.csc.insurance.client.validator.ClientValidator;
import br.com.csc.insurance.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(this::buildClient)
                .collect(Collectors.toList());
    }

    public ClientResponseDTO addClient(ClientDTO clientDTO) throws ClientAlreadyExistsException {
        validator.canBeAdded(clientDTO);
        Client client = clientRepository.save(convertToSave(clientDTO));
        return buildClient(client);
    }

    public void delete(String clientId) {
        clientRepository.delete(Client.builder().id(clientId).build());
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

    public ClientResponseDTO update(String clientId, ClientDTO clientDTO) {
        Optional.ofNullable(clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new));
        Client clientToSave = convertToSave(clientDTO);
        clientToSave.setId(clientId);
        return buildClient(clientRepository.save(clientToSave));
    }
}
