package br.com.csc.insurance.resource;

import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
class ClientResourceTest {

    private static final String CLIENTS_URI = "/api/v1/clients";

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientResource clientResource;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) ->  new MappingJackson2JsonView())
                .build();
    }

    @Test
    void should_return_a_list_of_clients_with_status_code_ok() throws Exception {
        when(clientService.clients()).thenReturn(buildAListOfClients());
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENTS_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(listAsJsonString(buildAListOfClients())));
    }

    private String listAsJsonString(List<ClientResponseDTO> clients) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(clients);
    }

    private List<ClientResponseDTO> buildAListOfClients() {
        return Arrays.asList(
                ClientResponseDTO.builder()
                        .city("São José")
                        .cpf("93693095000")
                        .federationUnity("SC")
                        .name("Jorge Mello").build(),
                ClientResponseDTO.builder()
                        .city("São João")
                        .cpf("02388204089")
                        .federationUnity("PR")
                        .name("Maurício Antunes").build()
        );
    }

}