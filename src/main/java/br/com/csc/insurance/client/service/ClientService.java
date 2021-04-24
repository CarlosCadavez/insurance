package br.com.csc.insurance.client.service;

import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.client.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponseDTO> clients() {
        return clientRepository
                .findAll()
                .stream()
                .map(client -> buildClient(client))
                .collect(Collectors.toList());
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
