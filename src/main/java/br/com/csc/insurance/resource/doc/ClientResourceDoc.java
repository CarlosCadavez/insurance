package br.com.csc.insurance.resource.doc;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api("Clients management")
public interface ClientResourceDoc {

    @ApiOperation(value = "Return a list of clients")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success method return") })
    List<ClientResponseDTO> clients();

    @ApiOperation(value = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success client created"),
            @ApiResponse(code = 400, message = "Missing fields, wrong fields or cpf already registered")
    })
    ClientResponseDTO addClient(ClientDTO clientDTO);

    @ApiOperation(value = "Update a client")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success client updated"),
            @ApiResponse(code = 400, message = "Missing fields, wrong fields or client not found"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    ClientResponseDTO updateClient(String clientId, ClientDTO clientDTO);

    @ApiOperation(value = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success client deleted"),
            @ApiResponse(code = 400, message = "Client with policies associated"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    void deleteClient(String clientId);
}
