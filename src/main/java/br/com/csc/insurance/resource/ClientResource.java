package br.com.csc.insurance.resource;

import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientResource {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponseDTO> clients() {
        return clientService.clients();
    }
}
