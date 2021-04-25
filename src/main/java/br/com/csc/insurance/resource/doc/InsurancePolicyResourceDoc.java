package br.com.csc.insurance.resource.doc;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.policy.dto.PolicyDTO;
import br.com.csc.insurance.policy.dto.PolicyResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("Policies management")
public interface InsurancePolicyResourceDoc {

    @ApiOperation(value = "Return a list of policies")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success method return") })
    List<PolicyResponseDTO> policies();

    @ApiOperation(value = "Return a policy by number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success method return"),
            @ApiResponse(code = 404, message = "Policy not found")
    })
    PolicyResponseDTO policieByNumber(String policyNumber);

    @ApiOperation(value = "Create a new policy")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success client created"),
            @ApiResponse(code = 400, message = "Missing fields, wrong fields")
    })
    PolicyResponseDTO addPolicy(PolicyDTO policyDTO);

    @ApiOperation(value = "Update a policy")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success policy updated"),
            @ApiResponse(code = 400, message = "Missing fields, wrong fields or policy not found"),
            @ApiResponse(code = 404, message = "Policy not found")
    })
    PolicyResponseDTO updatePolicy(String policyNumber, PolicyDTO policyDTO);

    @ApiOperation(value = "Delete a policy")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success policy deleted"),
            @ApiResponse(code = 404, message = "Policy not found")
    })
    void deletePolicy(String policyNumber);

}
