package br.com.csc.insurance.resource.helper;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertJson {

    public String clientDtoAsJsonString(ClientDTO clientDTO) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(clientDTO);
    }

    public String clientResponseDtoListAsJsonString(List<ClientResponseDTO> clients) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(clients);
    }

    public String clientResponseDtoAsJsonString(ClientResponseDTO clientResponseDTO) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(clientResponseDTO);
    }
}
