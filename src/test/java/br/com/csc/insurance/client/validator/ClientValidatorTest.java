package br.com.csc.insurance.client.validator;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.entity.Client;
import br.com.csc.insurance.client.exception.ClientAlreadyExistsException;
import br.com.csc.insurance.client.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class ClientValidatorTest {

    @Mock
    private ClientRepository repository;
    private ClientValidator clientValidator;

    @BeforeEach
    void setUp() {
        clientValidator = new ClientValidator(repository);
    }

    @Test
    void should_throw_exception_if_client_already_exists_on_database() {
        when(repository.findByCpf(anyString())).thenReturn(buildAListOfClients());
        ClientDTO clientDTO = ClientDTO.builder()
                .name("User name")
                .city("São José")
                .cpf("93693095000")
                .federationUnity("SC")
                .build();

        Assertions.assertThrows(ClientAlreadyExistsException.class,
                () -> clientValidator.canBeAdded(clientDTO));
    }

    private List<Client> buildAListOfClients() {
        return Arrays.asList(
                Client.builder()
                        .city("São José")
                        .cpf("93693095000")
                        .federationUnity("SC")
                        .name("Jorge Mello").build(),
                Client.builder()
                        .city("São João")
                        .cpf("02388204089")
                        .federationUnity("PR")
                        .name("Maurício Antunes").build()
        );
    }

}