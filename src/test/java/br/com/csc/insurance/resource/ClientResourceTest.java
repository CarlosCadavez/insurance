package br.com.csc.insurance.resource;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.service.ClientService;
import br.com.csc.insurance.resource.helper.ConvertJson;
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
    private static final String AN_NAME = "Name to test";
    private static final String A_CITY = "City to test";
    private static final String A_CPF = "61106143000";
    private static final String A_FEDERATION_UNIT = "SC";

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientResource clientResource;

    private MockMvc mockMvc;
    private ConvertJson convertJson;

    @BeforeEach
    void setUp() {
        this.convertJson = new ConvertJson();
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
                .andExpect(content().json(convertJson.clientResponseDtoListAsJsonString(buildAListOfClients())));
    }

    @Test
    void should_create_user_and_return_status_code_created() throws Exception {
        ClientDTO clientDTO = ClientDTO.builder()
                .cpf(A_CPF)
                .federationUnity(A_FEDERATION_UNIT)
                .city(A_CITY)
                .name(AN_NAME)
                .build();
        ClientResponseDTO clientResponseDTO = ClientResponseDTO.builder()
                .cpf(A_CPF)
                .federationUnity(A_FEDERATION_UNIT)
                .city(A_CITY)
                .name(AN_NAME)
                .build();
        when(clientService.addClient(clientDTO)).thenReturn(clientResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.post(CLIENTS_URI)
                .contentType(APPLICATION_JSON)
                .content(convertJson.clientDtoAsJsonString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(convertJson.clientResponseDtoAsJsonString(clientResponseDTO)));
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