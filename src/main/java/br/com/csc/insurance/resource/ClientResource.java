package br.com.csc.insurance.resource;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.exception.ClientAlreadyExistsException;
import br.com.csc.insurance.client.service.ClientService;
import br.com.csc.insurance.resource.doc.ClientResourceDoc;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientResource implements ClientResourceDoc {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponseDTO> clients() {
        return clientService.clients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO addClient(@Valid @RequestBody ClientDTO clientDTO) throws ClientAlreadyExistsException {
        return clientService.addClient(clientDTO);
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponseDTO updateClient(@PathVariable String clientId, @Valid @RequestBody ClientDTO clientDTO) {
        return clientService.update(clientId, clientDTO);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable String clientId) {
        clientService.delete(clientId);
    }

}
